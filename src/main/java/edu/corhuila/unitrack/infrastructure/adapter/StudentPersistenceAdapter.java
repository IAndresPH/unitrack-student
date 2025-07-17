package edu.corhuila.unitrack.infrastructure.adapter;

import edu.corhuila.unitrack.application.port.out.IStudentPersistencePort;
import edu.corhuila.unitrack.infrastructure.persistence.repository.IStudentRepository;
import edu.corhuila.unitrack.application.shared.exception.NotFoundException;
import edu.corhuila.unitrack.domain.model.Student;
import edu.corhuila.unitrack.infrastructure.mapper.StudentEntityMapper;
import edu.corhuila.unitrack.infrastructure.persistence.entity.StudentEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import static edu.corhuila.unitrack.application.shared.constants.ValidationMessages.STUDENT_ID_INVALID;

@Service
public class StudentPersistenceAdapter implements IStudentPersistencePort {

    private final IStudentRepository studentRepository;

    public StudentPersistenceAdapter(IStudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Student findById(Long id) {
        return StudentEntityMapper.toDomain(
                studentRepository.findById(id)
                        .orElseThrow(() -> new NotFoundException(STUDENT_ID_INVALID))
        );
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

        StudentEntity entity = StudentEntityMapper.toEntity(student, existing);

        StudentEntity saved = studentRepository.save(entity);
        return StudentEntityMapper.toDomain(saved);
    }
}