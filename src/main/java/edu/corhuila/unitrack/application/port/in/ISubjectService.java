package edu.corhuila.unitrack.application.port.in;

import edu.corhuila.unitrack.application.dto.request.SubjectRequest;
import edu.corhuila.unitrack.application.dto.response.SubjectResponse;

import java.util.List;

public interface ISubjectService {
    SubjectResponse create(SubjectRequest request);
    /**
     * Listar todas las materias asociadas a un estudiante.
     */
    List<SubjectResponse> getAllByStudentId(Long studentId);
    SubjectResponse update(Long id, SubjectRequest request);
    void delete(Long id);
}