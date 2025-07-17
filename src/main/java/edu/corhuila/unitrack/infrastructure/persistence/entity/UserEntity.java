package edu.corhuila.unitrack.infrastructure.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

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
}