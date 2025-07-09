package edu.corhuila.unitrack.infrastructure.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "activities")
public class ActivityEntity extends BaseEntity{
    @Column(name = "name", nullable = false, length = 30)
    private String name;

    @Column(name = "percentage", nullable = false)
    private Double percentage;

    @Column(name = "grade")
    private Double grade = 0.0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cut_id", nullable = false)
    private CutEntity cut;
}