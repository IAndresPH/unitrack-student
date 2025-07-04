package edu.corhuila.unitrack.application.port.out;

import edu.corhuila.unitrack.domain.model.Subject;
import java.util.List;

public interface ISubjectPersistencePort {
    Subject findById(Long id);
    List<Subject> findAllByStudentId(Long studentId);
    Subject save(Subject subject);
    void delete(Subject subject);
}