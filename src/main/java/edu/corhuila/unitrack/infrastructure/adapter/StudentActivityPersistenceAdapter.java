package edu.corhuila.unitrack.infrastructure.adapter;

import edu.corhuila.unitrack.application.port.out.IStudentActivityPersistencePort;
import edu.corhuila.unitrack.infrastructure.persistence.entity.ActivityEntity;
import edu.corhuila.unitrack.infrastructure.persistence.entity.StudentActivityEntity;
import edu.corhuila.unitrack.infrastructure.persistence.entity.StudentEntity;
import edu.corhuila.unitrack.infrastructure.persistence.repository.IStudentActivityRepository;
import org.springframework.stereotype.Service;

@Service
public class StudentActivityPersistenceAdapter implements IStudentActivityPersistencePort {

    private final IStudentActivityRepository repository;

    public StudentActivityPersistenceAdapter(IStudentActivityRepository repository) {
        this.repository = repository;
    }

    @Override
    public void saveGrade(Long studentId, Long activityId, Double grade) {
        var entity = repository.findByStudentIdAndActivityId(studentId, activityId)
                .orElseGet(() -> {
                    var newEntity = new StudentActivityEntity();

                    var student = new StudentEntity();
                    student.setId(studentId);

                    var activity = new ActivityEntity();
                    activity.setId(activityId);

                    newEntity.setStudent(student);
                    newEntity.setActivity(activity);
                    return newEntity;
                });

        entity.setGrade(grade);
        repository.save(entity);
    }

    @Override
    public Double findGradeByStudentAndActivity(Long studentId, Long activityId) {
        return repository.findByStudentIdAndActivityId(studentId, activityId)
                .map(entity -> entity.getGrade())
                .orElse(0.0); // o lanza excepción si prefieres
    }
}