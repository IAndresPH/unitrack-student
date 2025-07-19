package edu.corhuila.unitrack.application.mapper;

import edu.corhuila.unitrack.application.dto.response.UserResponse;
import edu.corhuila.unitrack.domain.model.User;
import edu.corhuila.unitrack.infrastructure.mapper.StudentEntityMapper;
import edu.corhuila.unitrack.infrastructure.persistence.entity.StudentEntity;
import edu.corhuila.unitrack.infrastructure.persistence.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toDomain(UserEntity entity) {
        if (entity == null) return null;

        User user = new User();
        user.setId(entity.getId());
        user.setUsername(entity.getUsername());
        user.setEmail(entity.getEmail());
        user.setPassword(entity.getPassword());

        if (entity.getStudentEntity() != null) {
            user.setStudent(StudentEntityMapper.toDomain(entity.getStudentEntity()));
        }

        return user;
    }

    public UserEntity toEntity(User domain) {
        if (domain == null) return null;

        UserEntity entity = new UserEntity();
        entity.setId(domain.getId());
        entity.setUsername(domain.getUsername());
        entity.setEmail(domain.getEmail());
        entity.setPassword(domain.getPassword());

        if (domain.getStudent() != null) {
            StudentEntity studentEntity = new StudentEntity();
            studentEntity.setId(domain.getStudent().getId());
            entity.setStudentEntity(studentEntity);
        }

        return entity;
    }


    public UserResponse toResponse(User domain) {
        if (domain == null) return null;

        return new UserResponse(
                domain.getId(),
                domain.getUsername(),
                domain.getEmail()
        );
    }
}