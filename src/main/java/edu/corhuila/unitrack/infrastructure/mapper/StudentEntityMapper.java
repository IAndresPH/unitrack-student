package edu.corhuila.unitrack.infrastructure.mapper;

import edu.corhuila.unitrack.domain.model.Student;
import edu.corhuila.unitrack.infrastructure.persistence.entity.StudentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StudentEntityMapper {
    StudentEntity toEntity(Student student);
    @Mapping(target = "subjects", ignore = true)
    Student toDomain(StudentEntity studentEntity);
}