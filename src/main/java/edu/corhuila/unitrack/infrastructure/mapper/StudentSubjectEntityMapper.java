package edu.corhuila.unitrack.infrastructure.mapper;

import edu.corhuila.unitrack.domain.model.Student;
import edu.corhuila.unitrack.domain.model.StudentSubject;
import edu.corhuila.unitrack.domain.model.Subject;
import edu.corhuila.unitrack.infrastructure.persistence.entity.StudentEntity;
import edu.corhuila.unitrack.infrastructure.persistence.entity.StudentSubjectEntity;
import edu.corhuila.unitrack.infrastructure.persistence.entity.SubjectEntity;

public class StudentSubjectEntityMapper {
    public static StudentSubject toDomain(StudentSubjectEntity entity) {
        if (entity == null) return null;

        StudentSubject ss = new StudentSubject();

        Student student = new Student();
        student.setId(entity.getStudent().getId());

        Subject subject = new Subject();
        subject.setId(entity.getSubject().getId());
        subject.setName(entity.getSubject().getName());
        subject.setCredit(entity.getSubject().getCredit());

        ss.setStudent(student);
        ss.setSubject(subject);
        ss.setFinalGrade(entity.getFinalGrade());

        return ss;
    }

    public static StudentSubjectEntity toEntity(StudentSubject domain) {
        if (domain == null) return null;

        StudentSubjectEntity entity = new StudentSubjectEntity();

        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setId(domain.getStudent().getId());

        SubjectEntity subjectEntity = new SubjectEntity();
        subjectEntity.setId(domain.getSubject().getId());

        entity.setStudent(studentEntity);
        entity.setSubject(subjectEntity);
        entity.setFinalGrade(domain.getFinalGrade());

        return entity;
    }
}