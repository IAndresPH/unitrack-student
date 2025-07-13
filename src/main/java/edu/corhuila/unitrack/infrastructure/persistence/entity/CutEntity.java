package edu.corhuila.unitrack.infrastructure.persistence.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "cuts")
public class CutEntity extends BaseEntity{
    @Column(name = "name", nullable = false, length = 30)
    private String name;

    @Column(name = "percentage", nullable = false)
    private Double percentage;

    @ManyToMany
    @JoinTable(
            name = "cut_subject",
            joinColumns = @JoinColumn(name = "cut_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id")
    )
    private List<SubjectEntity> subjects = new ArrayList<>();

    @OneToMany(mappedBy = "cut", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ActivityEntity> activities = new ArrayList<>();
}