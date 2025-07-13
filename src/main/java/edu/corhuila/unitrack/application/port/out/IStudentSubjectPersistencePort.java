package edu.corhuila.unitrack.application.port.out;

import edu.corhuila.unitrack.domain.model.StudentSubject;

import java.util.List;

public interface IStudentSubjectPersistencePort {
    /**
     * Obtener todas las materias (relaciones) de un estudiante,
     * incluyendo su nota definitiva por materia.
     */
    List<StudentSubject> findAllStudentSubjectsByStudentId(Long studentId);

    /**
     * Calcular la nota definitiva de una materia para un estudiante.
     */
    Double calculateFinalGradeByStudentAndSubject(Long studentId, Long subjectId);

    /**
     * Guardar o actualizar la nota definitiva de una materia para un estudiante.
     */
    void saveFinalGrade(Long studentId, Long subjectId, Double finalGrade);
}