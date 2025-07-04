package edu.corhuila.unitrack.application.service;

import edu.corhuila.unitrack.application.dto.request.StudentRequest;
import edu.corhuila.unitrack.application.dto.response.StudentResponse;
import edu.corhuila.unitrack.application.mapper.IStudentMapper;
import edu.corhuila.unitrack.application.port.in.IStudentService;
import edu.corhuila.unitrack.application.port.out.IStudentPersistencePort;
import org.springframework.stereotype.Service;
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
    public StudentResponse getById(Long id) {
        var student = studentPersistencePort.findById(id);
        return iStudentMapper.toResponseDto(student);
    }

    @Override
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
    public StudentResponse update(Long id, StudentRequest request) {
        var student = studentPersistencePort.findById(id);

        student.setFirstName(request.firstName());
        student.setLastName(request.lastName());
        student.setEmail(request.email());
        student.setStudentCode(request.studentCode());
        student.setProgram(request.program());
        student.setSemester(request.semester());
        student.setUpdatedAt(LocalDateTime.now());

        var updated = studentPersistencePort.save(student);
        return iStudentMapper.toResponseDto(updated);
    }
}