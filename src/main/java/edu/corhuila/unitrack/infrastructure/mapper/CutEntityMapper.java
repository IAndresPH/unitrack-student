package edu.corhuila.unitrack.infrastructure.mapper;

import edu.corhuila.unitrack.domain.model.Cut;
import edu.corhuila.unitrack.infrastructure.persistence.entity.ActivityEntity;
import edu.corhuila.unitrack.infrastructure.persistence.entity.CutEntity;
import edu.corhuila.unitrack.infrastructure.persistence.entity.SubjectEntity;
import java.util.List;
import java.util.stream.Collectors;

public class CutEntityMapper {

    public static CutEntity toEntity(Cut cut) {
        if (cut == null) return null;

        CutEntity entity = new CutEntity();
        entity.setId(cut.getId());
        entity.setName(cut.getName());
        entity.setPercentage(cut.getPercentage());

        // Mapear las materias por ID
        if (cut.getSubject() != null) {
            List<SubjectEntity> subjects = cut.getSubject().stream()
                    .map(subject -> {
                        SubjectEntity subjectEntity = new SubjectEntity();
                        subjectEntity.setId(subject.getId());
                        return subjectEntity;
                    })
                    .collect(Collectors.toList());
            entity.setSubjects(subjects);
        }

        // Mapear las actividades por ID
        if (cut.getActivities() != null) {
            List<ActivityEntity> activities = cut.getActivities().stream()
                    .map(activity -> {
                        ActivityEntity activityEntity = new ActivityEntity();
                        activityEntity.setId(activity.getId());
                        return activityEntity;
                    })
                    .collect(Collectors.toList());
            entity.setActivities(activities);
        }

        return entity;
    }

    public static Cut toDomain(CutEntity entity) {
        return Cut.fromEntity(entity);
    }

    public static Cut toDomainWithoutRelations(CutEntity entity) {
        if (entity == null) return null;

        Cut cut = new Cut();
        cut.setId(entity.getId());
        cut.setName(entity.getName());
        cut.setPercentage(entity.getPercentage());
        // NO cargamos ni subjects ni activities
        return cut;
    }
}