package com.example.usermanagement.infrastructure.persistence;

import com.example.usermanagement.application.interfaces.UserRepository;
import com.example.usermanagement.domain.Role;
import com.example.usermanagement.domain.User;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class UserRepositoryImpl implements UserRepository {
    private final UserJpaRepository userJpaRepository;

    public UserRepositoryImpl(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }

    @Override
    public User save(User user) {
        UserJpaEntity entity = new UserJpaEntity();
        entity.setId(user.getId());
        entity.setName(user.getName());
        entity.setEmail(user.getEmail());

        // Convert domain roles to JPA entities
        entity.setRoles(user.getRoles().stream()
                .map(role -> {
                    RoleJpaEntity roleEntity = new RoleJpaEntity();
                    roleEntity.setId(role.getId());
                    roleEntity.setRoleName(role.getRoleName());
                    return roleEntity;
                })
                .collect(Collectors.toSet()));

        userJpaRepository.save(entity);
        return user;
    }

    @Override
    public Optional<User> findById(UUID id) {
        return userJpaRepository.findById(id)
                .map(entity -> {
                    User user = new User(entity.getName(), entity.getEmail());
                    // Reflection to set ID since it's final in User
                    try {
                        var idField = User.class.getDeclaredField("id");
                        idField.setAccessible(true);
                        idField.set(user, entity.getId());
                    } catch (Exception e) {
                        throw new RuntimeException("Failed to set user ID", e);
                    }

                    // Convert JPA roles to domain roles
                    entity.getRoles().forEach(roleEntity -> {
                        Role role = new Role(roleEntity.getRoleName());
                        try {
                            var roleIdField = Role.class.getDeclaredField("id");
                            roleIdField.setAccessible(true);
                            roleIdField.set(role, roleEntity.getId());
                        } catch (Exception e) {
                            throw new RuntimeException("Failed to set role ID", e);
                        }
                        user.assignRole(role);
                    });

                    return user;
                });
    }
}