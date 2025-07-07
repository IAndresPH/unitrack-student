package edu.corhuila.unitrack.infrastructure.adapter;

import edu.corhuila.unitrack.application.port.out.ISubjectPersistencePort;
import edu.corhuila.unitrack.application.shared.exception.NotFoundException;
import edu.corhuila.unitrack.domain.model.Subject;
import edu.corhuila.unitrack.infrastructure.mapper.SubjectEntityMapper;
import edu.corhuila.unitrack.infrastructure.persistence.repository.ISubjectRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import static edu.corhuila.unitrack.application.shared.constants.ValidationMessages.SUBJECT_ID_INVALID;

@Service
public class SubjectPersistenceAdapter implements ISubjectPersistencePort {

    private final ISubjectRepository subjectRepository;
    private final SubjectEntityMapper subjectEntityMapper;

    public SubjectPersistenceAdapter(ISubjectRepository subjectRepository, SubjectEntityMapper subjectEntityMapper) {
        this.subjectRepository = subjectRepository;
        this.subjectEntityMapper = subjectEntityMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public Subject findById(Long id) {
        return subjectEntityMapper.toDomain(
                subjectRepository.findById(id)
                        .orElseThrow(() -> new NotFoundException(SUBJECT_ID_INVALID))
        );
    }

    @Override
    @Transactional(readOnly = true)
    public List<Subject> findAllByStudentId(Long studentId) {
        return subjectRepository.findAllByStudentId(studentId).stream()
                .map(subjectEntityMapper::toDomain)
                .toList();
    }

    @Override
    public Subject save(Subject subject) {
        var entity = subjectEntityMapper.toEntity(subject);
        var saved = subjectRepository.save(entity);
        return subjectEntityMapper.toDomain(saved);
    }

    @Override
    public void delete(Subject subject) {
        var entity = subjectEntityMapper.toEntity(subject);
        subjectRepository.delete(entity);
    }
}