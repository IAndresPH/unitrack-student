package edu.corhuila.unitrack.infrastructure.mapper;

import edu.corhuila.unitrack.domain.model.Subject;
import edu.corhuila.unitrack.infrastructure.persistence.entity.SubjectEntity;

public class SubjectEntityMapper {
    public static SubjectEntity toEntity(Subject subject) {
        if (subject == null) return null;

        SubjectEntity entity = new SubjectEntity();
        entity.setId(subject.getId());
        entity.setName(subject.getName());
        entity.setCredit(subject.getCredit());

        return entity;
    }

    public static Subject toDomain(SubjectEntity entity) {
        return Subject.fromEntity(entity);
    }
}