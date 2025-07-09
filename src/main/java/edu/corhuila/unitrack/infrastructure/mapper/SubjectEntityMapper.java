package edu.corhuila.unitrack.infrastructure.mapper;

import edu.corhuila.unitrack.infrastructure.persistence.entity.StudentEntity;
import edu.corhuila.unitrack.infrastructure.persistence.entity.SubjectEntity;
import edu.corhuila.unitrack.domain.model.Subject;

public class SubjectEntityMapper {
    public static SubjectEntity toEntity(Subject subject) {
        if (subject == null) return null;

        SubjectEntity entity = new SubjectEntity();
        entity.setId(subject.getId());
        entity.setName(subject.getName());
        entity.setCredit(subject.getCredit());
        entity.setFinalGrade(subject.getFinalGrade());

        if (subject.getStudent() != null) {
            StudentEntity studentEntity = new StudentEntity();
            studentEntity.setId(subject.getStudent().getId());
            entity.setStudent(studentEntity);
        }

        return entity;
    }

    public static Subject toDomain(SubjectEntity entity) {
        return Subject.fromEntity(entity);
    }
}