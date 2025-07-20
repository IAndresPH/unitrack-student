package edu.corhuila.unitrack.application.service;

import edu.corhuila.unitrack.application.dto.request.StudentRequest;
import edu.corhuila.unitrack.application.dto.request.StudentUpdateRequest;
import edu.corhuila.unitrack.application.dto.response.StudentResponse;
import edu.corhuila.unitrack.application.dto.response.StudentUpdateResponse;
import edu.corhuila.unitrack.application.mapper.StudentMapper;
import edu.corhuila.unitrack.application.port.in.IStudentService;
import edu.corhuila.unitrack.application.port.out.IStudentPersistencePort;
import edu.corhuila.unitrack.application.port.out.IUserPersistencePort;
import edu.corhuila.unitrack.domain.model.Student;
import edu.corhuila.unitrack.domain.model.User;
import edu.corhuila.unitrack.infrastructure.persistence.entity.UserEntity;
import edu.corhuila.unitrack.infrastructure.security.AuthenticatedUserProvider;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static edu.corhuila.unitrack.application.shared.constants.ValidationMessages.STUDENT_CODE_INVALID;

@Service
public class StudentService implements IStudentService {

    private final IStudentPersistencePort studentPersistencePort;
    private final StudentMapper studentMapper;
    private final IUserPersistencePort userPersistencePort;
    private final AuthenticatedUserProvider authenticatedUserProvider;

    public StudentService(IStudentPersistencePort studentPersistencePort, StudentMapper studentMapper, IUserPersistencePort userPersistencePort, AuthenticatedUserProvider authenticatedUserProvider) {
        this.studentPersistencePort = studentPersistencePort;
        this.studentMapper = studentMapper;
        this.userPersistencePort = userPersistencePort;
        this.authenticatedUserProvider = authenticatedUserProvider;
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
        if (studentPersistencePort.existsByStudentCode(request.studentCode())) {
            throw new RuntimeException(STUDENT_CODE_INVALID);
        }

        Student student = studentMapper.toEntity(request);
        student.setCreatedAt(LocalDateTime.now());
        student.setActive(true);

        Long userId = authenticatedUserProvider.getAuthenticatedUserId();

        User user = new User();
        user.setId(userId);
        student.setUser(user);

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

    public StudentResponse toResponse(Student student) {
        return studentMapper.toResponseDto(student);
    }
}