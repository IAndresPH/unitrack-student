package edu.corhuila.unitrack.application.mapper;

import edu.corhuila.unitrack.application.dto.request.ActivityRequest;
import edu.corhuila.unitrack.application.dto.response.ActivityResponse;
import edu.corhuila.unitrack.domain.model.Activity;
import edu.corhuila.unitrack.domain.model.Cut;
import edu.corhuila.unitrack.domain.model.Subject;
import org.springframework.stereotype.Component;

@Component
public class ActivityMapper {

    public Activity toEntity(ActivityRequest request) {
        Activity activity = new Activity();
        activity.setName(request.name());
        activity.setPercentage(request.percentage());

        Cut cut = new Cut();
        cut.setId(request.cutId());
        activity.setCut(cut);

        Subject subject = new Subject();
        subject.setId(request.subjectId());
        activity.setSubject(subject);

        return activity;
    }

    public ActivityResponse toResponseDto(Activity activity) {
        Long cutId = activity.getCut() != null ? activity.getCut().getId() : null;
        Long subjectId = activity.getSubject() != null ? activity.getSubject().getId() : null;

        return new ActivityResponse(
                activity.getId(),
                activity.getName(),
                activity.getPercentage(),
                cutId,
                subjectId
        );
    }
}