package edu.corhuila.unitrack.application.service;

import edu.corhuila.unitrack.application.dto.request.SubjectRequest;
import edu.corhuila.unitrack.application.dto.response.SubjectResponse;
import edu.corhuila.unitrack.application.mapper.SubjectMapper;
import edu.corhuila.unitrack.application.port.in.IActivityService;
import edu.corhuila.unitrack.application.port.in.ISubjectService;
import edu.corhuila.unitrack.application.port.out.IStudentPersistencePort;
import edu.corhuila.unitrack.application.port.out.ISubjectPersistencePort;
import edu.corhuila.unitrack.domain.model.Subject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class SubjectService implements ISubjectService {

    private final ISubjectPersistencePort subjectPersistencePort;
    private final SubjectMapper subjectMapper;
    private final IActivityService activityService;

    public SubjectService(
            ISubjectPersistencePort subjectPersistencePort,
            SubjectMapper subjectMapper,
            IStudentPersistencePort studentPersistencePort,
            IActivityService activityService
    ) {
        this.subjectPersistencePort = subjectPersistencePort;
        this.subjectMapper = subjectMapper;
        this.activityService = activityService;
    }

    @Override
    @Transactional
    public SubjectResponse create(SubjectRequest request) {
        Subject subject = subjectMapper.toEntity(request);
        Subject saved = subjectPersistencePort.save(subject);
        return subjectMapper.toResponseDto(saved);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Subject subject = subjectPersistencePort.findById(id);
        subjectPersistencePort.delete(subject);
    }

    @Override
    public List<SubjectResponse> getAllByStudentId(Long studentId) {
        return subjectPersistencePort.findAllByStudentId(studentId)
                .stream()
                .map(subjectMapper::toResponseDto)
                .toList();
    }
}