package edu.corhuila.unitrack.application.port.in;

import edu.corhuila.unitrack.application.dto.request.StudentRequest;
import edu.corhuila.unitrack.application.dto.request.StudentUpdateRequest;
import edu.corhuila.unitrack.application.dto.response.StudentResponse;
import edu.corhuila.unitrack.application.dto.response.StudentUpdateResponse;

public interface IStudentService {
    StudentResponse getById(Long id);
    StudentResponse create(StudentRequest request);
    StudentUpdateResponse update(Long id, StudentUpdateRequest request);
}