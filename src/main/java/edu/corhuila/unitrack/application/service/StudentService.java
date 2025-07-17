package edu.corhuila.unitrack.application.service;

import edu.corhuila.unitrack.application.dto.request.StudentRequest;
import edu.corhuila.unitrack.application.dto.request.StudentUpdateRequest;
import edu.corhuila.unitrack.application.dto.response.StudentResponse;
import edu.corhuila.unitrack.application.dto.response.StudentUpdateResponse;
import edu.corhuila.unitrack.application.mapper.StudentMapper;
import edu.corhuila.unitrack.application.port.in.IStudentService;
import edu.corhuila.unitrack.application.port.out.IStudentPersistencePort;
import edu.corhuila.unitrack.domain.model.Student;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import static edu.corhuila.unitrack.application.shared.constants.ValidationMessages.STUDENT_EMAIL_INVALID;
import static edu.corhuila.unitrack.application.shared.constants.ValidationMessages.STUDENT_CODE_INVALID;

@Service
public class StudentService implements IStudentService {

    private final IStudentPersistencePort studentPersistencePort;
    private final StudentMapper studentMapper;

    public StudentService(IStudentPersistencePort studentPersistencePort, StudentMapper studentMapper) {
        this.studentPersistencePort = studentPersistencePort;
        this.studentMapper = studentMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public StudentResponse getById(Long id) {
        Student student = studentPersistencePort.findById(id);
        return studentMapper.toResponseDto(student);
    }

    @Override
    @Transactional
    public void create(StudentRequest request) {
        if (studentPersistencePort.existsByEmail(request.email())) {
            throw new RuntimeException(STUDENT_EMAIL_INVALID);
        }

        if (studentPersistencePort.existsByStudentCode(request.studentCode())) {
            throw new RuntimeException(STUDENT_CODE_INVALID);
        }

        Student student = studentMapper.toEntity(request);
        student.setCreatedAt(LocalDateTime.now());
        student.setActive(true);

        studentPersistencePort.save(student);
    }

    @Override
    @Transactional
    public StudentUpdateResponse update(Long id, StudentUpdateRequest request) {
        Student student = studentPersistencePort.findById(id);

        studentMapper.updateFromRequest(student, request);
        student.setUpdatedAt(LocalDateTime.now());

        Student updated = studentPersistencePort.save(student);
        return studentMapper.toUpdateResponseDto(updated);
    }
}