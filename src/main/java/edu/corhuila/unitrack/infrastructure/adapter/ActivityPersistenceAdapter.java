package edu.corhuila.unitrack.infrastructure.adapter;

import edu.corhuila.unitrack.application.port.out.IActivityPersistencePort;
import edu.corhuila.unitrack.domain.model.Activity;
import edu.corhuila.unitrack.infrastructure.mapper.ActivityEntityMapper;
import edu.corhuila.unitrack.infrastructure.persistence.entity.ActivityEntity;
import edu.corhuila.unitrack.infrastructure.persistence.repository.IActivityRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ActivityPersistenceAdapter implements IActivityPersistencePort {

    private final IActivityRepository activityRepository;

    public ActivityPersistenceAdapter(IActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    @Override
    public Activity save(Activity activity) {
        ActivityEntity entity = ActivityEntityMapper.toEntity(activity); // <-- este

        ActivityEntity saved = activityRepository.save(entity);
        return ActivityEntityMapper.toDomain(saved); // <-- este
    }

    @Override
    public void deleteById(Long id) {
        activityRepository.deleteById(id);
    }

    @Override
    public List<Activity> findAllBySubjectId(Long subjectId) {
        List<ActivityEntity> entities = activityRepository.findAllBySubject_Id(subjectId);
        return entities.stream()
                .map(ActivityEntityMapper::toDomain)
                .toList();
    }

    @Override
    public List<Activity> findAllBySubjectIdAndCutId(Long cutId, Long subjectId) {
        List<ActivityEntity> entities = activityRepository.findAllByCut_IdAndSubject_Id(cutId, subjectId);
        return entities.stream()
                .map(ActivityEntityMapper::toDomain)
                .toList();
    }

    @Override
    public List<Activity> findAllByStudentIdAndSubjectIdAndCutId(Long studentId, Long subjectId, Long cutId) {
        return activityRepository
                .findAllByStudentIdAndSubjectIdAndCutId(studentId, subjectId, cutId)
                .stream()
                .map(ActivityEntityMapper::toDomain)
                .toList();
    }
}