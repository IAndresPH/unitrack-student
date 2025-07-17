package edu.corhuila.unitrack.application.port.in;

import edu.corhuila.unitrack.application.dto.request.SubjectRequest;
import edu.corhuila.unitrack.application.dto.response.SubjectResponse;
import java.util.List;

public interface ISubjectService {
    void create(SubjectRequest request);
    void delete(Long id);
    List<SubjectResponse> getAllByStudentId(Long studentId);
}