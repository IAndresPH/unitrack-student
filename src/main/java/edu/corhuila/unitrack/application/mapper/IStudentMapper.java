package edu.corhuila.unitrack.application.mapper;

import edu.corhuila.unitrack.application.dto.request.StudentRequest;
import edu.corhuila.unitrack.application.dto.response.StudentResponse;
import edu.corhuila.unitrack.application.dto.response.StudentUpdateResponse;
import edu.corhuila.unitrack.domain.model.Student;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IStudentMapper {
    StudentResponse toResponseDto(Student student);
    StudentUpdateResponse toUpdateResponseDto(Student student);
    Student toEntity(StudentRequest studentRequest);
}