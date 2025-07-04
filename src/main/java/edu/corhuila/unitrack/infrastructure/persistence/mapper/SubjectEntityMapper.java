package edu.corhuila.unitrack.infrastructure.persistence.mapper;

import edu.corhuila.unitrack.domain.model.Subject;
import edu.corhuila.unitrack.infrastructure.persistence.entity.SubjectEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SubjectEntityMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "updatedAt", source = "updatedAt")
    @Mapping(target = "active", source = "isActive")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "credit", source = "credit")
    @Mapping(target = "finalGrade", source = "finalGrade")
    @Mapping(target = "student", expression = "java(mapStudent(subjectEntity.getStudent()))")
    Subject toDomain(SubjectEntity subjectEntity);

    SubjectEntity toEntity(Subject subject);

    default edu.corhuila.unitrack.domain.model.Student mapStudent(edu.corhuila.unitrack.infrastructure.persistence.entity.StudentEntity studentEntity) {
        if (studentEntity == null) return null;

        edu.corhuila.unitrack.domain.model.Student student = new edu.corhuila.unitrack.domain.model.Student();
        student.setId(studentEntity.getId());
        student.setFirstName(studentEntity.getFirstName());
        student.setLastName(studentEntity.getLastName());
        student.setEmail(studentEntity.getEmail());
        student.setStudentCode(studentEntity.getStudentCode());
        student.setProgram(studentEntity.getProgram());
        student.setSemester(studentEntity.getSemester());
        student.setAverageGrade(studentEntity.getAverageGrade());

        return student;
    }
}