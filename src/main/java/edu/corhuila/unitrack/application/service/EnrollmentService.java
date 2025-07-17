package edu.corhuila.unitrack.application.service;

import edu.corhuila.unitrack.application.dto.request.EnrollmentRequest;
import edu.corhuila.unitrack.application.dto.response.EnrollmentResponse;
import edu.corhuila.unitrack.application.mapper.EnrollmentMapper;
import edu.corhuila.unitrack.application.port.in.IEnrollmentService;
import edu.corhuila.unitrack.application.port.out.IEnrollmentPersistentePort;
import edu.corhuila.unitrack.domain.model.Enrollment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class EnrollmentService implements IEnrollmentService {

    private final EnrollmentMapper enrollmentMapper;
    private final IEnrollmentPersistentePort enrollmentPersistencePort;

    public EnrollmentService(EnrollmentMapper enrollmentMapper, IEnrollmentPersistentePort enrollmentPersistencePort) {
        this.enrollmentMapper = enrollmentMapper;
        this.enrollmentPersistencePort = enrollmentPersistencePort;
    }

    @Override
    @Transactional
    public void create(EnrollmentRequest request) {
        Enrollment enrollment = enrollmentMapper.toEntity(request);
        enrollmentPersistencePort.save(enrollment);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EnrollmentResponse> getAllByStudentId(Long studentId) {
        return enrollmentPersistencePort.findAllByStudentId(studentId).stream()
                .map(enrollmentMapper::toGetResponseDto)
                .toList();
    }

    @Override
    public void delete(Long id) {
        Enrollment enrollment = enrollmentPersistencePort.findById(id);
        enrollmentPersistencePort.delete(enrollment);
    }
}
