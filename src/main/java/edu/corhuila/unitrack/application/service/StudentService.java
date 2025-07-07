package edu.corhuila.unitrack.application.service;

import edu.corhuila.unitrack.application.dto.request.StudentRequest;
import edu.corhuila.unitrack.application.dto.request.StudentUpdateRequest;
import edu.corhuila.unitrack.application.dto.response.StudentResponse;
import edu.corhuila.unitrack.application.dto.response.StudentUpdateResponse;
import edu.corhuila.unitrack.application.mapper.IStudentMapper;
import edu.corhuila.unitrack.application.port.in.IStudentService;
import edu.corhuila.unitrack.application.port.out.IStudentPersistencePort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class StudentService implements IStudentService {

    private final IStudentPersistencePort studentPersistencePort;
    private final IStudentMapper iStudentMapper;

    public StudentService(IStudentPersistencePort studentPersistencePort, IStudentMapper iStudentMapper) {
        this.studentPersistencePort = studentPersistencePort;
        this.iStudentMapper = iStudentMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public StudentResponse getById(Long id) {
        var student = studentPersistencePort.findById(id);
        return iStudentMapper.toResponseDto(student);
    }

    @Override
    @Transactional
    public StudentResponse create(StudentRequest request) {
        var student = iStudentMapper.toEntity(request);

        if(studentPersistencePort.existsByEmail(request.email())){
            throw new RuntimeException("Correo ya registrado");
        }

        if(studentPersistencePort.existsByStudentCode(request.studentCode())){
            throw new RuntimeException("Código de estudiante ya registrado");
        }

        student.setCreatedAt(LocalDateTime.now());
        student.setActive(true);

        var saved = studentPersistencePort.save(student);
        return iStudentMapper.toResponseDto(saved);
    }

    @Override
    @Transactional
    public StudentUpdateResponse update(Long id, StudentUpdateRequest request) {
        var student = studentPersistencePort.findById(id);

        student.setFirstName(request.firstName());
        student.setLastName(request.lastName());
        student.setProgram(request.program());
        student.setSemester(request.semester());
        student.setUpdatedAt(LocalDateTime.now());

        var updated = studentPersistencePort.save(student);
        return iStudentMapper.toUpdateResponseDto(updated);
    }
}