package edu.corhuila.unitrack.application.port.out;

import edu.corhuila.unitrack.domain.model.Cut;

import java.util.List;

public interface ICutPersistencePort {
    Cut save(Cut cut);
    int countCutsBySubjectId(Long subjectId);
    List<Cut> findAllByStudentIdAndSubjectId(Long studentId, Long subjectId);
}