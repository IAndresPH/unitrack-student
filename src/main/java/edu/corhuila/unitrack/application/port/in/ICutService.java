package edu.corhuila.unitrack.application.port.in;

import edu.corhuila.unitrack.application.dto.request.CutRequest;
import edu.corhuila.unitrack.application.dto.response.CutResponse;

import java.util.List;

public interface ICutService {
    CutResponse getById(Long id);
    CutResponse create(CutRequest request);
    CutResponse update(Long id, CutRequest request);
    Double calculateFinalGradeBySubjectId(Long subjectId);
    List<CutResponse> getAllBySubjectId(Long subjectId);
}