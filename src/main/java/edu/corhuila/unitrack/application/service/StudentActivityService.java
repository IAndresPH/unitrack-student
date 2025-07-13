package edu.corhuila.unitrack.application.service;

import edu.corhuila.unitrack.application.dto.request.StudentActivityRequest;
import edu.corhuila.unitrack.application.port.out.IStudentActivityPersistencePort;
import org.springframework.stereotype.Service;

@Service
public class StudentActivityService {

    private final IStudentActivityPersistencePort persistencePort;

    public StudentActivityService(IStudentActivityPersistencePort persistencePort) {
        this.persistencePort = persistencePort;
    }

    public void assignGrade(StudentActivityRequest request) {
        persistencePort.saveGrade(request.studentId(), request.activityId(), request.grade());
    }

    public Double getGrade(Long studentId, Long activityId) {
        return persistencePort.findGradeByStudentAndActivity(studentId, activityId);
    }
}