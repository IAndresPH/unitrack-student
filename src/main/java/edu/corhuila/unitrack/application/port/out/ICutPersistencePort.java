package edu.corhuila.unitrack.application.port.out;

import edu.corhuila.unitrack.domain.model.Cut;

public interface ICutPersistencePort {
    Cut save(Cut cut);
    int countCutsBySubjectId(Long subjectId);
}