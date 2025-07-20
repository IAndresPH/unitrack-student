package edu.corhuila.unitrack.infrastructure.mapper;

import edu.corhuila.unitrack.domain.model.User;
import edu.corhuila.unitrack.infrastructure.persistence.entity.StudentEntity;
import edu.corhuila.unitrack.domain.model.Student;
import edu.corhuila.unitrack.infrastructure.persistence.entity.UserEntity;

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

        if (student.getUser() != null && student.getUser().getId() != null) {
            UserEntity userEntity = new UserEntity();
            userEntity.setId(student.getUser().getId());
            entity.setUserEntity(userEntity);
        }

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

        // ✅ Setear solo el ID del usuario, no toda la entidad
        if (entity.getUserEntity() != null && entity.getUserEntity().getId() != null) {
            User user = new User();
            user.setId(entity.getUserEntity().getId());
            student.setUser(user);
        }

        return student;
    }
}