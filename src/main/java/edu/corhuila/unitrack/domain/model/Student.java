package edu.corhuila.unitrack.domain.model;

import edu.corhuila.unitrack.infrastructure.persistence.entity.StudentEntity;

import java.util.ArrayList;
import java.util.List;

public class Student extends Person {
    private String studentCode;
    private String program;
    private Integer semester;
    private Double averageGrade = 0.0;
    private List<Subject> subjects = new ArrayList<>();

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

    void setAverageGrade(Double averageGrade) {
        this.averageGrade = averageGrade;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public static Student fromEntity(StudentEntity entity) {
        Student student = new Student();
        student.setId(entity.getId());
        student.setFirstName(entity.getFirstName());
        student.setLastName(entity.getLastName());
        student.setEmail(entity.getEmail());
        student.setStudentCode(entity.getStudentCode());
        student.setProgram(entity.getProgram());
        student.setSemester(entity.getSemester());
        student.averageGrade = entity.getAverageGrade();

        return student;
    }
}