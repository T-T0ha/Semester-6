package com.example.usermanagement.infrastructure.persistence;

import com.example.usermanagement.application.interfaces.RoleRepository;
import com.example.usermanagement.domain.Role;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class RoleRepositoryImpl implements RoleRepository {
    private final RoleJpaRepository roleJpaRepository;

    public RoleRepositoryImpl(RoleJpaRepository roleJpaRepository) {
        this.roleJpaRepository = roleJpaRepository;
    }

    @Override
    public Role save(Role role) {
        RoleJpaEntity entity = new RoleJpaEntity();
        entity.setId(role.getId());
        entity.setRoleName(role.getRoleName());
        roleJpaRepository.save(entity);
        return role;
    }

    @Override
    public Optional<Role> findById(UUID id) {
        return roleJpaRepository.findById(id)
                .map(entity -> {
                    Role role = new Role(entity.getRoleName());
                    // Reflection to set ID since it's final in Role
                    try {
                        var idField = Role.class.getDeclaredField("id");
                        idField.setAccessible(true);
                        idField.set(role, entity.getId());
                    } catch (Exception e) {
                        throw new RuntimeException("Failed to set role ID", e);
                    }
                    return role;
                });
    }
}