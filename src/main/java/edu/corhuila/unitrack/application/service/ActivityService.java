package edu.corhuila.unitrack.application.service;

import edu.corhuila.unitrack.application.dto.request.ActivityRequest;
import edu.corhuila.unitrack.application.dto.response.ActivityResponse;
import edu.corhuila.unitrack.application.mapper.ActivityMapper;
import edu.corhuila.unitrack.application.port.in.IActivityService;
import edu.corhuila.unitrack.application.port.out.IActivityPersistencePort;
import edu.corhuila.unitrack.domain.model.Activity;
import edu.corhuila.unitrack.domain.model.Cut;
import edu.corhuila.unitrack.domain.model.Subject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class ActivityService implements IActivityService {

    private final ActivityMapper activityMapper;
    private final IActivityPersistencePort activityPersistencePort;

    public ActivityService(ActivityMapper activityMapper, IActivityPersistencePort activityPersistencePort) {
        this.activityMapper = activityMapper;
        this.activityPersistencePort = activityPersistencePort;
    }

    @Override
    @Transactional(readOnly = true)
    public ActivityResponse getById(Long id) {
        Activity activity = activityPersistencePort.findById(id);
        return activityMapper.toResponseDto(activity);
    }

    @Override
    @Transactional
    public ActivityResponse create(ActivityRequest request) {
        Activity activity = activityMapper.toEntity(request);

        Cut cut = new Cut();
        cut.setId(request.cutId());
        activity.setCut(cut);

        Subject subject = new Subject();
        subject.setId(request.subjectId());
        activity.setSubject(subject);

        Activity saved = activityPersistencePort.save(activity);
        return activityMapper.toResponseDto(saved);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        activityPersistencePort.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Double calculateFinalGradeBySubjectId(Long subjectId) {
        List<Activity> activities = activityPersistencePort.findAllBySubjectId(subjectId);

        return activities.stream()
                .mapToDouble(a -> {
                    double grade = a.getGrade() != null ? a.getGrade() : 0.0;
                    double percentage = a.getPercentage() != null ? a.getPercentage() : 0.0;
                    return grade * (percentage / 100.0);
                })
                .sum();
    }
}