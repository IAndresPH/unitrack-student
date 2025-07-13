package edu.corhuila.unitrack.application.port.in;

import edu.corhuila.unitrack.application.dto.request.CutRequest;
import edu.corhuila.unitrack.application.dto.response.CutResponse;
import java.util.List;

public interface ICutService {
    CutResponse create(CutRequest request);
    List<CutResponse> getAllByStudentIdAndSubjectId(Long studentId, Long subjectId);
}