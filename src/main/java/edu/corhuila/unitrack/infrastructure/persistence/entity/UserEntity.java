package edu.corhuila.unitrack.infrastructure.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
public class UserEntity extends BaseEntity{
    @Column(name = "user_name", nullable = false, unique = true, length = 30)
    private String username;

    @Column(nullable = false, unique = true, length = 35)
    private String email;

    @Column(nullable = false)
    private String password;

    @OneToOne(optional = true)
    @JoinColumn(name = "student_id")
    private StudentEntity studentEntity;

    @OneToMany(mappedBy = "userEntity", fetch = FetchType.LAZY)
    private List<TokenEntity> tokens;
}