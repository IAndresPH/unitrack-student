package edu.corhuila.unitrack.application.port.out;

import edu.corhuila.unitrack.domain.model.Student;

public interface IStudentPersistencePort {
    Student findById(Long id);
    boolean existsByEmail(String email);
    boolean existsByStudentCode(String code);
    Student save(Student student);
}