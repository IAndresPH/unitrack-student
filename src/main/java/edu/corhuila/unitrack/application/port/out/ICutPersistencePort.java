package edu.corhuila.unitrack.application.port.out;

import edu.corhuila.unitrack.domain.model.Cut;

import java.util.List;

public interface ICutPersistencePort {
    Cut findById(Long id);
    Cut save(Cut cut);
    List<Cut> findAllBySubjectId(Long subjectId);
}