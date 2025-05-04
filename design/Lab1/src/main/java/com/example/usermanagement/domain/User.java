package com.example.usermanagement.domain;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class User {
    private final UUID id;
    private String name;
    private String email;
    private final Set<Role> roles = new HashSet<>();

    public User(String name, String email) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.email = email;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void assignRole(Role role) {
        this.roles.add(role);
    }

    public Set<Role> getRoles() {
        return Collections.unmodifiableSet(roles);
    }
}