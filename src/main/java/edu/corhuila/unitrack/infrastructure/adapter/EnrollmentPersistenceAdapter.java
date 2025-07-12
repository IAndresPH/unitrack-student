package edu.corhuila.unitrack.infrastructure.adapter;

import edu.corhuila.unitrack.application.port.out.IEnrollmentPersistentePort;
import edu.corhuila.unitrack.application.shared.exception.NotFoundException;
import edu.corhuila.unitrack.domain.model.Enrollment;
import edu.corhuila.unitrack.infrastructure.mapper.EnrollmentEntityMapper;
import edu.corhuila.unitrack.infrastructure.persistence.entity.EnrollmentEntity;
import edu.corhuila.unitrack.infrastructure.persistence.repository.IEnrollmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static edu.corhuila.unitrack.application.shared.constants.ValidationMessages.ENROLLMENT_ID_INVALID;

@Service
public class EnrollmentPersistenceAdapter implements IEnrollmentPersistentePort {

    private final IEnrollmentRepository enrollmentRepository;

    public EnrollmentPersistenceAdapter(IEnrollmentRepository enrollmentRepository) {
        this.enrollmentRepository = enrollmentRepository;
    }

    @Override
    @Transactional
    public Enrollment save(Enrollment enrollment) {
        EnrollmentEntity entity = EnrollmentEntityMapper.toEntity(enrollment);
        EnrollmentEntity saved = enrollmentRepository.save(entity);
        return EnrollmentEntityMapper.toDomain(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Enrollment> findAllByStudentId(Long studentId) {
        return enrollmentRepository.findAllByStudent_Id(studentId).stream()
                .map(EnrollmentEntityMapper::toDomain)
                .toList();
    }

    @Override
    @Transactional
    public void delete(Enrollment enrollment) {
        EnrollmentEntity entity = EnrollmentEntityMapper.toEntity(enrollment);
        enrollmentRepository.delete(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public Enrollment findById(Long id) {
        return EnrollmentEntityMapper.toDomain(
                enrollmentRepository.findById(id)
                        .orElseThrow(() -> new NotFoundException(ENROLLMENT_ID_INVALID))
        );
    }
}