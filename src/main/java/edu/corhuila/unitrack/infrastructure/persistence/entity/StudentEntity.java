package edu.corhuila.unitrack.infrastructure.persistence.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "students")
public class StudentEntity extends PersonEntity{
    @Column(name = "student_code", unique = true, length = 20)
    private String studentCode;

    @Column(name = "program", nullable = false, length = 30)
    private String program;

    @Column(name = "semester", nullable = false)
    private Integer semester;

    @Column(name = "average_grade")
    private Double averageGrade = 0.0;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<SubjectEntity> subjects = new ArrayList<>();
}