package com.example.usermanagement.application;

import com.example.usermanagement.application.interfaces.RoleRepository;
import com.example.usermanagement.application.interfaces.UserRepository;
import com.example.usermanagement.domain.Role;
import com.example.usermanagement.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private UserService userService;

    // Test data
    private UUID userId;
    private UUID roleId;
    private User testUser;
    private Role testRole;

    @BeforeEach
    void setUp() {
        userId = UUID.randomUUID();
        roleId = UUID.randomUUID();
        testUser = new User("Test User", "test@example.com");
        testRole = new Role("ADMIN");
    }

    // ===== createUser() Tests =====
    @Test
    void createUser_ValidInput_ReturnsUserId() {
        // Arrange
        when(userRepository.save(any(User.class))).thenReturn(testUser);

        // Act
        UUID result = userService.createUser("Test User", "test@example.com");

        // Assert
        assertNotNull(result);
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void createUser_NullName_ThrowsException() {
        assertThrows(
                IllegalArgumentException.class,
                () -> userService.createUser(null, "test@example.com")
        );
    }

    @Test
    void createUser_InvalidEmail_ThrowsException() {
        assertThrows(
                IllegalArgumentException.class,
                () -> userService.createUser("Test User", "invalid-email")
        );
    }

    // ===== getUser() Tests =====
    @Test
    void getUser_ExistingUser_ReturnsUser() {
        // Arrange
        when(userRepository.findById(userId)).thenReturn(Optional.of(testUser));

        // Act
        User result = userService.getUser(userId);

        // Assert
        assertEquals(testUser, result);
        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    void getUser_NonExistentUser_ThrowsException() {
        // Arrange
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> userService.getUser(userId)
        );

        assertEquals("User not found", exception.getMessage());
    }

    // ===== assignRoleToUser() Tests =====
    @Test
    void assignRoleToUser_ValidIds_AssignsRole() {
        // Arrange
        when(userRepository.findById(userId)).thenReturn(Optional.of(testUser));
        when(roleRepository.findById(roleId)).thenReturn(Optional.of(testRole));
        when(userRepository.save(any(User.class))).thenReturn(testUser);

        // Act
        userService.assignRoleToUser(userId, roleId);

        // Assert
        assertTrue(testUser.getRoles().contains(testRole));
        verify(userRepository, times(1)).save(testUser);
    }

    @Test
    void assignRoleToUser_UserNotFound_ThrowsException() {
        // Arrange
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> userService.assignRoleToUser(userId, roleId)
        );

        assertEquals("User not found", exception.getMessage());
        verify(roleRepository, never()).findById(any());
    }

    @Test
    void assignRoleToUser_RoleNotFound_ThrowsException() {
        // Arrange
        when(userRepository.findById(userId)).thenReturn(Optional.of(testUser));
        when(roleRepository.findById(roleId)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> userService.assignRoleToUser(userId, roleId)
        );

        assertEquals("Role not found", exception.getMessage());
        verify(userRepository, never()).save(any());
    }

    @Test
    void assignRoleToUser_DuplicateRole_IgnoresDuplicate() {
        // Arrange
        testUser.assignRole(testRole); // Pre-assign role
        when(userRepository.findById(userId)).thenReturn(Optional.of(testUser));
        when(roleRepository.findById(roleId)).thenReturn(Optional.of(testRole));

        // Act
        userService.assignRoleToUser(userId, roleId);

        // Assert
        assertEquals(1, testUser.getRoles().size()); // No duplicate added
        verify(userRepository, times(1)).save(testUser);
    }
}