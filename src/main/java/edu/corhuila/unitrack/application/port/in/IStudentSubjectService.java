package edu.corhuila.unitrack.application.port.in;

import edu.corhuila.unitrack.application.dto.response.SubjectResponse;
import java.util.List;

public interface IStudentSubjectService {
    /**
     * Retorna todas las materias de un estudiante con la nota definitiva ya calculada.
     */
    List<SubjectResponse> getAllSubjectsWithFinalGradeByStudentId(Long studentId);

    /**
     * Calcula la nota definitiva de una materia específica para un estudiante.
     */
    Double calculateFinalGradeByStudentAndSubject(Long studentId, Long subjectId);
}