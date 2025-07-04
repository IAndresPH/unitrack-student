package edu.corhuila.unitrack.infrastructure.persistence.mapper;

import edu.corhuila.unitrack.domain.model.Student;
import edu.corhuila.unitrack.infrastructure.persistence.entity.StudentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {SubjectEntityMapper.class})
public interface StudentEntityMapper {
    StudentEntity toEntity(Student student);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "studentCode", source = "studentCode")
    @Mapping(target = "program", source = "program")
    @Mapping(target = "semester", source = "semester")
    @Mapping(target = "averageGrade", source = "averageGrade")
    @Mapping(target = "subjects", ignore = true) // Para evitar ciclos
    Student toDomain(StudentEntity studentEntity);
}