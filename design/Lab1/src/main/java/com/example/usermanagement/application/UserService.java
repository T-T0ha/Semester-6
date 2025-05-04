package com.example.usermanagement.application;
import com.example.usermanagement.domain.Role;
import com.example.usermanagement.application.interfaces.RoleRepository;
import com.example.usermanagement.application.interfaces.UserRepository;
import com.example.usermanagement.domain.User;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public UUID createUser(String name, String email) {
        User user = new User(name, email);
        return userRepository.save(user).getId();
    }

    public User getUser(UUID userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public void assignRoleToUser(UUID userId, UUID roleId) {
        User user = getUser(userId);
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new RuntimeException("Role not found"));
        user.assignRole(role);
        userRepository.save(user);
    }
}