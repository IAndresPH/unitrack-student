package edu.corhuila.unitrack.infrastructure.mapper;

import edu.corhuila.unitrack.infrastructure.persistence.entity.StudentEntity;
import edu.corhuila.unitrack.domain.model.Student;

public class StudentEntityMapper {

    public static StudentEntity toEntity(Student student, StudentEntity existing) {
        if (student == null) return null;

        StudentEntity entity = new StudentEntity();

        entity.setId(student.getId());
        entity.setFirstName(student.getFirstName());
        entity.setLastName(student.getLastName());
        entity.setEmail(student.getEmail());
        entity.setStudentCode(student.getStudentCode());
        entity.setProgram(student.getProgram());
        entity.setSemester(student.getSemester());
        entity.setAverageGrade(student.getAverageGrade());
        entity.setCreatedAt(student.getCreatedAt());

        entity.setActive(student.getActive() != null ? student.getActive()
                : (existing != null ? existing.getActive() : Boolean.TRUE));
        entity.setUpdatedAt(student.getUpdatedAt());

        if (existing != null && existing.getSubjects() != null) {
            entity.setSubjects(existing.getSubjects());
        }

        return entity;
    }

    public static Student toDomain(StudentEntity entity) {
        if (entity == null) return null;
        return Student.fromEntity(entity);
    }
}