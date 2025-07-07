package edu.corhuila.unitrack.infrastructure.adapter;

import edu.corhuila.unitrack.application.port.out.IStudentPersistencePort;
import edu.corhuila.unitrack.domain.model.Student;
import edu.corhuila.unitrack.infrastructure.mapper.StudentEntityMapper;
import edu.corhuila.unitrack.infrastructure.persistence.entity.StudentEntity;
import edu.corhuila.unitrack.infrastructure.persistence.repository.IStudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentPersistenceAdapter implements IStudentPersistencePort {

    private final IStudentRepository studentRepository;
    private final StudentEntityMapper studentEntityMapper;

    public StudentPersistenceAdapter(IStudentRepository studentRepository, StudentEntityMapper studentEntityMapper) {
        this.studentRepository = studentRepository;
        this.studentEntityMapper = studentEntityMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public Student findById(Long id) {
        return studentEntityMapper.toDomain(
                studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Estudiante no encontrado"))
        );
    }

    @Override
    public boolean existsByEmail(String email) {
        return studentRepository.existsByEmail(email);
    }

    @Override
    public boolean existsByStudentCode(String code) {
        return studentRepository.existsByStudentCode(code);
    }

    @Override
    @Transactional
    public Student save(Student student) {
        StudentEntity existing = student.getId() != null
                ? studentRepository.findById(student.getId()).orElse(null)
                : null;

        StudentEntity entity = studentEntityMapper.toEntity(student);

        if (existing != null) {
            entity.setSubjects(existing.getSubjects());
        }

        StudentEntity saved = studentRepository.save(entity);
        return studentEntityMapper.toDomain(saved);
    }
}