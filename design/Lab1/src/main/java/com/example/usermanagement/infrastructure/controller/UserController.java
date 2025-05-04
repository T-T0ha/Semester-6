package com.example.usermanagement.infrastructure.controller;
import com.example.usermanagement.domain.Role; // Add this import
import com.example.usermanagement.application.UserService;
import com.example.usermanagement.domain.User;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UUID> createUser(@Valid @RequestBody CreateUserRequest request) {
        UUID userId = userService.createUser(request.name(), request.email());
        return ResponseEntity.ok(userId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable UUID id) {
        User user = userService.getUser(id);
        return ResponseEntity.ok(new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRoles().stream()
                        .map(RoleResponse::new)
                        .collect(Collectors.toSet())
        ));
    }

    @PostMapping("/{userId}/assign-role/{roleId}")
    public ResponseEntity<String> assignRoleToUser(
            @PathVariable UUID userId,
            @PathVariable UUID roleId) {
        userService.assignRoleToUser(userId, roleId);
        return ResponseEntity.ok("Role assigned successfully");
    }

    record CreateUserRequest(
            @NotBlank String name,
            @NotBlank @Email String email
    ) {}

    record UserResponse(
            UUID id,
            String name,
            String email,
            Set<RoleResponse> roles
    ) {}

    record RoleResponse(
            UUID id,
            String roleName
    ) {
        RoleResponse(Role role) {
            this(role.getId(), role.getRoleName());
        }
    }
}