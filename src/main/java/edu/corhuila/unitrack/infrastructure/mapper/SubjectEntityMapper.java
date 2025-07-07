package edu.corhuila.unitrack.infrastructure.mapper;

import edu.corhuila.unitrack.domain.model.Subject;
import edu.corhuila.unitrack.infrastructure.persistence.entity.SubjectEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {StudentEntityMapper.class})
public interface SubjectEntityMapper {
    SubjectEntity toEntity(Subject subject);
    Subject toDomain(SubjectEntity subjectEntity);
}