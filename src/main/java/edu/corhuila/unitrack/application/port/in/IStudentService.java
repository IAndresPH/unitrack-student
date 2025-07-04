package edu.corhuila.unitrack.application.port.in;

import edu.corhuila.unitrack.application.dto.request.StudentRequest;
import edu.corhuila.unitrack.application.dto.response.StudentResponse;

public interface IStudentService {
    StudentResponse getById(Long id);
    StudentResponse create(StudentRequest request);
    StudentResponse update(Long id, StudentRequest request);
}