package edu.corhuila.unitrack.application.port.in;

import edu.corhuila.unitrack.application.dto.request.ActivityRequest;
import edu.corhuila.unitrack.application.dto.response.ActivityResponse;
import java.util.List;

public interface IActivityService {
    ActivityResponse create(ActivityRequest request);
    void delete(Long id);
    Double calculateFinalGradeBySubjectId(Long subjectId);
    List<ActivityResponse> getAllByStudentSubjectAndCut(Long studentId, Long subjectId, Long cutId);
}