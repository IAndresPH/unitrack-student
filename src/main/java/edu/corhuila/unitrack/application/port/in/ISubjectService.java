package edu.corhuila.unitrack.application.port.in;

import edu.corhuila.unitrack.application.dto.request.SubjectRequest;
import edu.corhuila.unitrack.application.dto.request.SubjectUpdateRequest;
import edu.corhuila.unitrack.application.dto.response.SubjectResponse;
import edu.corhuila.unitrack.application.dto.response.SubjectUpdateResponse;
import java.util.List;

public interface ISubjectService {
    SubjectResponse create(SubjectRequest request);
    SubjectUpdateResponse update(Long id, SubjectUpdateRequest request);
    List<SubjectResponse> getAllByStudentId(Long studentId); // renombrado
    void delete(Long id);
}