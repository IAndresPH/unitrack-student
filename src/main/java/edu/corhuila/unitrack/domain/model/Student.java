package edu.corhuila.unitrack.domain.model;

import edu.corhuila.unitrack.infrastructure.persistence.entity.StudentEntity;
import java.util.List;

public class Student extends Person {
    private String studentCode;
    private String program;
    private Integer semester;
    private Double averageGrade = 0.0;
    private List<Enrollment> enrollments;
    private List<StudentSubject> studentSubjects;
    private List<StudentActivity> studentActivities;

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    public Double getAverageGrade() {
        return averageGrade;
    }

    public void setAverageGrade(Double averageGrade) {
        this.averageGrade = averageGrade;
    }

    public List<Enrollment> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(List<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }

    public List<StudentSubject> getStudentSubjects() {
        return studentSubjects;
    }

    public void setStudentSubjects(List<StudentSubject> studentSubjects) {
        this.studentSubjects = studentSubjects;
    }

    public List<StudentActivity> getStudentActivities() {
        return studentActivities;
    }

    public void setStudentActivities(List<StudentActivity> studentActivities) {
        this.studentActivities = studentActivities;
    }

    public static Student fromEntity(StudentEntity entity) {
        if (entity == null) return null;

        Student student = new Student();
        student.setId(entity.getId());
        student.setFirstName(entity.getFirstName());
        student.setLastName(entity.getLastName());
        student.setEmail(entity.getEmail());
        student.setStudentCode(entity.getStudentCode());
        student.setProgram(entity.getProgram());
        student.setSemester(entity.getSemester());
        student.setAverageGrade(entity.getAverageGrade());
        student.setCreatedAt(entity.getCreatedAt());
        student.setUpdatedAt(entity.getUpdatedAt());
        student.setActive(entity.getActive());

        return student;
    }
}