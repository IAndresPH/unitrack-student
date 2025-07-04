package edu.corhuila.unitrack.application.service;

import edu.corhuila.unitrack.application.dto.request.SubjectRequest;
import edu.corhuila.unitrack.application.dto.response.SubjectResponse;
import edu.corhuila.unitrack.application.mapper.ISubjectMapper;
import edu.corhuila.unitrack.application.port.in.ISubjectService;
import edu.corhuila.unitrack.application.port.out.IStudentPersistencePort;
import edu.corhuila.unitrack.application.port.out.ISubjectPersistencePort;
import edu.corhuila.unitrack.domain.model.Student;
import edu.corhuila.unitrack.domain.model.Subject;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SubjectService implements ISubjectService {

    private final ISubjectPersistencePort subjectPersistencePort;
    private final ISubjectMapper iSubjectMapper;
    private final IStudentPersistencePort studentPersistencePort;

    public SubjectService(ISubjectPersistencePort subjectPersistencePort, ISubjectMapper iSubjectMapper, IStudentPersistencePort studentPersistencePort) {
        this.subjectPersistencePort = subjectPersistencePort;
        this.iSubjectMapper = iSubjectMapper;
        this.studentPersistencePort = studentPersistencePort;
    }

    @Override
    public SubjectResponse create(SubjectRequest request) {
        Student student = studentPersistencePort.findById(request.studentId());

        Subject subject = iSubjectMapper.toEntity(request);
        subject.setStudent(student);
        subject.setActive(true);
        subject.setCreatedAt(LocalDateTime.now());

        Subject saved = subjectPersistencePort.save(subject);
        return iSubjectMapper.toResponseDto(saved);
    }

    @Override
    public List<SubjectResponse> getAllByStudentId(Long studentId) {
        List<Subject> subjects = subjectPersistencePort.findAllByStudentId(studentId);
        return iSubjectMapper.toResponseDtoList(subjects);
    }

    @Override
    public SubjectResponse update(Long id, SubjectRequest request) {
        Subject subject = subjectPersistencePort.findById(id); // ← Aquí recuperas la materia

        Student student = studentPersistencePort.findById(request.studentId()); // ← Aquí el estudiante

        subject.setName(request.name());
        subject.setCredit(request.credit());
        subject.setStudent(student);
        subject.setUpdatedAt(LocalDateTime.now());

        Subject updated = subjectPersistencePort.save(subject);
        return iSubjectMapper.toResponseDto(updated);
    }

    @Override
    public void delete(Long id) {
        Subject subject = subjectPersistencePort.findById(id);
        subjectPersistencePort.delete(subject);
    }
}