package edu.corhuila.unitrack.application.port.out;

import edu.corhuila.unitrack.domain.model.Enrollment;
import java.util.List;

public interface IEnrollmentPersistentePort {
    Enrollment save(Enrollment enrollment);
    List<Enrollment> findAllByStudentId(Long studentId);
    void delete(Enrollment enrollment);
    Enrollment findById(Long id);
}