package edu.corhuila.unitrack.infrastructure.persistence.entity;

import edu.corhuila.unitrack.domain.model.Subject;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
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
    private Double averageGrade;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SubjectEntity> subjects;
}