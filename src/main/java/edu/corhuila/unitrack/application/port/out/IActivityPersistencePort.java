package edu.corhuila.unitrack.application.port.out;

import edu.corhuila.unitrack.domain.model.Activity;
import java.util.List;

public interface IActivityPersistencePort {
    Activity save(Activity activity);
    void deleteById(Long id);
    List<Activity> findAllBySubjectId(Long subjectId);
    List<Activity> findAllByCutIdAndSubjectId(Long cutId, Long subjectId);
    List<Activity> findAllByStudentIdAndSubjectIdAndCutId(Long studentId, Long subjectId, Long cutId);
}