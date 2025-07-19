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
        entity.setStudentCode(student.getStudentCode());
        entity.setProgram(student.getProgram());
        entity.setCreatedAt(student.getCreatedAt());
        entity.setUpdatedAt(student.getUpdatedAt());

        entity.setActive(student.getActive() != null
                ? student.getActive()
                : (existing != null ? existing.getActive() : Boolean.TRUE));

        return entity;
    }

    public static Student toDomain(StudentEntity entity) {
        if (entity == null) return null;

        Student student = new Student();
        student.setId(entity.getId());
        student.setFirstName(entity.getFirstName());
        student.setLastName(entity.getLastName());
        student.setStudentCode(entity.getStudentCode());
        student.setProgram(entity.getProgram());
        student.setCreatedAt(entity.getCreatedAt());
        student.setUpdatedAt(entity.getUpdatedAt());
        student.setActive(entity.getActive());

        return student;
    }
}