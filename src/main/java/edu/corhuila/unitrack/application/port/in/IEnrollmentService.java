package edu.corhuila.unitrack.application.port.in;

import edu.corhuila.unitrack.application.dto.request.EnrollmentRequest;
import edu.corhuila.unitrack.application.dto.response.EnrollmentGetResponse;
import edu.corhuila.unitrack.application.dto.response.EnrollmentPostResponse;

import java.util.List;

public interface IEnrollmentService {
    EnrollmentPostResponse create(EnrollmentRequest request);
    List<EnrollmentGetResponse> getAllByStudentId(Long studentId);
    void delete(Long id);
}