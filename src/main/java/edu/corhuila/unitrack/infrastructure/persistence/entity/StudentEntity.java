package edu.corhuila.unitrack.infrastructure.persistence.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "students")
@Getter
@Setter
public class StudentEntity extends PersonEntity{
    @Column(name = "student_code", unique = true, length = 20)
    private String studentCode;

    @Column(name = "program", nullable = false, length = 30)
    private String program;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StudentSubjectEntity> studentSubjects = new ArrayList<>();

    @OneToOne(optional = false)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;
}