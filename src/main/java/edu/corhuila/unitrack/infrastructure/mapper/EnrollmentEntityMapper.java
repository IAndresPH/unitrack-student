package edu.corhuila.unitrack.infrastructure.mapper;

import edu.corhuila.unitrack.domain.model.Enrollment;
import edu.corhuila.unitrack.domain.model.Student;
import edu.corhuila.unitrack.domain.model.Subject;
import edu.corhuila.unitrack.infrastructure.persistence.entity.EnrollmentEntity;
import edu.corhuila.unitrack.infrastructure.persistence.entity.EnrollmentSubjectEntity;
import edu.corhuila.unitrack.infrastructure.persistence.entity.StudentEntity;
import edu.corhuila.unitrack.infrastructure.persistence.entity.SubjectEntity;
import java.util.List;

public class EnrollmentEntityMapper {
    public static EnrollmentEntity toEntity(Enrollment enrollment) {
        if (enrollment == null) return null;

        EnrollmentEntity entity = new EnrollmentEntity();
        entity.setId(enrollment.getId());
        entity.setSemester(enrollment.getSemester());
        entity.setEnrollmentDate(enrollment.getEnrollmentDate());

        // Asociar Student
        if (enrollment.getStudent() != null) {
            StudentEntity studentEntity = new StudentEntity();
            studentEntity.setId(enrollment.getStudent().getId());
            entity.setStudent(studentEntity);
        }

        // Asociar lista de Subjects mediante EnrollmentSubjectEntity
        if (enrollment.getSubjects() != null) {
            List<EnrollmentSubjectEntity> enrollmentSubjects = enrollment.getSubjects().stream()
                    .map(subject -> {
                        EnrollmentSubjectEntity ese = new EnrollmentSubjectEntity();
                        ese.setEnrollment(entity); // relación inversa
                        SubjectEntity se = new SubjectEntity();
                        se.setId(subject.getId());
                        ese.setSubject(se);
                        return ese;
                    })
                    .toList();
            entity.setEnrollmentSubjects(enrollmentSubjects);
        }

        return entity;
    }

    public static Enrollment toDomain(EnrollmentEntity entity) {
        if (entity == null) return null;

        Enrollment enrollment = new Enrollment();
        enrollment.setId(entity.getId());
        enrollment.setSemester(entity.getSemester());
        enrollment.setEnrollmentDate(entity.getEnrollmentDate());

        // Estudiante
        if (entity.getStudent() != null) {
            Student student = new Student();
            student.setId(entity.getStudent().getId());
            student.setFirstName(entity.getStudent().getFirstName());
            student.setLastName(entity.getStudent().getLastName());
            enrollment.setStudent(student);
        }

        // Subjects desde EnrollmentSubjectEntity
        if (entity.getEnrollmentSubjects() != null) {
            List<Subject> subjects = entity.getEnrollmentSubjects().stream()
                    .map(es -> {
                        Subject s = new Subject();
                        s.setId(es.getSubject().getId());
                        s.setName(es.getSubject().getName());
                        s.setCredit(es.getSubject().getCredit());
                        return s;
                    })
                    .toList();
            enrollment.setSubjects(subjects);
        }

        return enrollment;
    }
}