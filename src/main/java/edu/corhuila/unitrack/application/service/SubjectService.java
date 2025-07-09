package edu.corhuila.unitrack.application.service;

import edu.corhuila.unitrack.application.dto.request.SubjectRequest;
import edu.corhuila.unitrack.application.dto.request.SubjectUpdateRequest;
import edu.corhuila.unitrack.application.dto.response.SubjectResponse;
import edu.corhuila.unitrack.application.dto.response.SubjectUpdateResponse;
import edu.corhuila.unitrack.application.mapper.SubjectMapper;
import edu.corhuila.unitrack.application.port.in.ISubjectService;
import edu.corhuila.unitrack.application.port.out.IStudentPersistencePort;
import edu.corhuila.unitrack.application.port.out.ISubjectPersistencePort;
import edu.corhuila.unitrack.domain.model.Student;
import edu.corhuila.unitrack.domain.model.Subject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class SubjectService implements ISubjectService {

    private final ISubjectPersistencePort subjectPersistencePort;
    private final SubjectMapper subjectMapper;
    private final IStudentPersistencePort studentPersistencePort;

    public SubjectService(ISubjectPersistencePort subjectPersistencePort, SubjectMapper subjectMapper, IStudentPersistencePort studentPersistencePort) {
        this.subjectPersistencePort = subjectPersistencePort;
        this.subjectMapper = subjectMapper;
        this.studentPersistencePort = studentPersistencePort;
    }

    @Override
    @Transactional
    public SubjectResponse create(SubjectRequest request) {
        Student student = studentPersistencePort.findById(request.studentId());

        Subject subject = subjectMapper.toEntity(request);
        subject.setStudent(student);

        Subject saved = subjectPersistencePort.save(subject);
        return subjectMapper.toResponseDto(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SubjectResponse> getAllByStudentId(Long studentId) {
        List<Subject> subjects = subjectPersistencePort.findAllByStudentId(studentId);
        return subjectMapper.toResponseDtoList(subjects);
    }

    @Override
    @Transactional
    public SubjectUpdateResponse update(Long id, SubjectUpdateRequest request) {
        Subject subject = subjectPersistencePort.findById(id);

        subjectMapper.updateFromRequest(subject, request); // Usamos el mapper

        Subject updated = subjectPersistencePort.save(subject);
        return subjectMapper.toUpdateResponseDto(updated);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Subject subject = subjectPersistencePort.findById(id);
        subjectPersistencePort.delete(subject);
    }
}