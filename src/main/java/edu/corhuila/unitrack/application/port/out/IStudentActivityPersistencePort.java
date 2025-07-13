package edu.corhuila.unitrack.application.port.out;

public interface IStudentActivityPersistencePort {
    void saveGrade(Long studentId, Long activityId, Double grade);
    Double findGradeByStudentAndActivity(Long studentId, Long activityId);
}