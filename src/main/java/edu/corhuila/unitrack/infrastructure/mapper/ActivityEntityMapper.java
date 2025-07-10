package edu.corhuila.unitrack.infrastructure.mapper;

import edu.corhuila.unitrack.domain.model.Activity;
import edu.corhuila.unitrack.infrastructure.persistence.entity.ActivityEntity;
import edu.corhuila.unitrack.infrastructure.persistence.entity.CutEntity;
import edu.corhuila.unitrack.infrastructure.persistence.entity.SubjectEntity;

public class ActivityEntityMapper {

    public static ActivityEntity toEntity(Activity activity) {
        if (activity == null) return null;

        ActivityEntity entity = new ActivityEntity();
        entity.setId(activity.getId());
        entity.setName(activity.getName());
        entity.setPercentage(activity.getPercentage());
        entity.setGrade(activity.getGrade());

        if (activity.getCut() != null) {
            CutEntity cutEntity = new CutEntity();
            cutEntity.setId(activity.getCut().getId());
            entity.setCut(cutEntity);
        }

        if (activity.getSubject() != null) {
            SubjectEntity subjectEntity = new SubjectEntity();
            subjectEntity.setId(activity.getSubject().getId());
            entity.setSubject(subjectEntity);
        }

        return entity;
    }

    public static Activity toDomain(ActivityEntity entity) {
        return Activity.fromEntity(entity);
    }
}