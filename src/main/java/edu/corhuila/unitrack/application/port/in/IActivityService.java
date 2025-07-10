package edu.corhuila.unitrack.application.port.in;

import edu.corhuila.unitrack.application.dto.request.ActivityRequest;
import edu.corhuila.unitrack.application.dto.response.ActivityResponse;
import java.util.List;

public interface IActivityService {
    ActivityResponse getById(Long id);
    ActivityResponse create(ActivityRequest request);
    void delete(Long id);
    Double calculateFinalGradeBySubjectId(Long subjectId);
}