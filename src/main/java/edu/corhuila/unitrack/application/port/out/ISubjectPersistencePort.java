package edu.corhuila.unitrack.application.port.out;

import edu.corhuila.unitrack.domain.model.Subject;
import java.util.List;

public interface ISubjectPersistencePort {
    Subject findById(Long id);
    Subject save(Subject subject);
    void delete(Subject subject);
    List<Subject> findAllByStudentId(Long studentId);
}