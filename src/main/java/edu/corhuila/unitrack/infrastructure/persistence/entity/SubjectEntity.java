package edu.corhuila.unitrack.infrastructure.persistence.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "subjects")
@Getter
@Setter
public class SubjectEntity extends BaseEntity{
    @Column(name = "name", nullable = false, length = 30)
    private String name;

    @Column(name = "credit", nullable = false)
    private Integer credit;

    @Column(name = "final_grade")
    private Double finalGrade = 0.0;

    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EnrollmentSubjectEntity> enrollmentSubjects = new ArrayList<>();
}