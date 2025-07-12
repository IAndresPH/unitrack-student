package edu.corhuila.unitrack.infrastructure.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "enrollment_subjects")
@Getter
@Setter
public class EnrollmentSubjectEntity extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "enrollment_id", nullable = false)
    private EnrollmentEntity enrollment;

    @ManyToOne
    @JoinColumn(name = "subject_id", nullable = false)
    private SubjectEntity subject;
}