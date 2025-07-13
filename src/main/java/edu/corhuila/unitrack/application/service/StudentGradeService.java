package edu.corhuila.unitrack.application.service;

import edu.corhuila.unitrack.application.port.out.IActivityPersistencePort;
import edu.corhuila.unitrack.application.port.out.IStudentActivityPersistencePort;
import org.springframework.stereotype.Service;

@Service
public class StudentGradeService {

    private final IStudentActivityPersistencePort studentActivityPort;
    private final IActivityPersistencePort activityPort;

    public StudentGradeService(IStudentActivityPersistencePort studentActivityPort,
                               IActivityPersistencePort activityPort) {
        this.studentActivityPort = studentActivityPort;
        this.activityPort = activityPort;
    }

    // Calcula la nota definitiva de una materia para un estudiante
    public Double calculateFinalGrade(Long studentId, Long subjectId) {
        var activities = activityPort.findAllBySubjectId(subjectId);

        double finalGrade = 0.0;

        for (var activity : activities) {
            double studentGrade = studentActivityPort
                    .findGradeByStudentAndActivity(studentId, activity.getId());
            finalGrade += (studentGrade * activity.getPercentage() / 100);
        }

        return finalGrade;
    }

    // Opcional: calcular por corte
    public Double calculateFinalGradeByCut(Long studentId, Long subjectId, Long cutId) {
        var activities = activityPort.findAllBySubjectIdAndCutId(subjectId, cutId);

        double finalGrade = 0.0;

        for (var activity : activities) {
            double studentGrade = studentActivityPort
                    .findGradeByStudentAndActivity(studentId, activity.getId());
            finalGrade += (studentGrade * activity.getPercentage() / 100);
        }

        return finalGrade;
    }
}