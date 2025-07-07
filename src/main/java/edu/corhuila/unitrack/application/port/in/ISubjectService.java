package edu.corhuila.unitrack.application.port.in;

import edu.corhuila.unitrack.application.dto.request.SubjectRequest;
import edu.corhuila.unitrack.application.dto.request.SubjectUpdateRequest;
import edu.corhuila.unitrack.application.dto.response.SubjectResponse;
import edu.corhuila.unitrack.application.dto.response.SubjectUpdateResponse;

import java.util.List;

public interface ISubjectService {
    SubjectResponse create(SubjectRequest request);
    List<SubjectResponse> getAllByStudentId(Long studentId);
    SubjectUpdateResponse update(Long id, SubjectUpdateRequest request);
    void delete(Long id);
}