package com.example.usermanagement.infrastructure.persistence;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "roles")
@Data
public class RoleJpaEntity {
    @Id
    private UUID id;

    @Column(nullable = false, unique = true)
    private String roleName;
}