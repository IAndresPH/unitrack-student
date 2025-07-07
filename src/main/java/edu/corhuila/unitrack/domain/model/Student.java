package edu.corhuila.unitrack.domain.model;

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
}