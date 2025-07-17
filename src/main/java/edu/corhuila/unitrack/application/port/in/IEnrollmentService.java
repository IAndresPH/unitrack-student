package edu.corhuila.unitrack.application.port.in;

import edu.corhuila.unitrack.application.dto.request.EnrollmentRequest;
import edu.corhuila.unitrack.application.dto.response.EnrollmentResponse;
import java.util.List;

public interface IEnrollmentService {
    void create(EnrollmentRequest request);
    List<EnrollmentResponse> getAllByStudentId(Long studentId);
    void delete(Long id);
}