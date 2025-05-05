package com.example.usermanagement.application;

import com.example.usermanagement.application.interfaces.RoleRepository;
import com.example.usermanagement.domain.Role;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RoleServiceTest {

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private RoleService roleService;

    // Test data
    private final UUID testRoleId = UUID.randomUUID();
    private final String testRoleName = "ADMIN";

    @Test
    void createRole_ValidInput_ReturnsRoleId() {
        // Arrange
        Role mockRole = new Role(testRoleName);
        when(roleRepository.save(any(Role.class))).thenReturn(mockRole);

        // Act
        UUID result = roleService.createRole(testRoleName);

        // Assert
        assertNotNull(result);
        verify(roleRepository, times(1)).save(any(Role.class));
    }

    @Test
    void getRole_ExistingId_ReturnsRole() {
        // Arrange
        Role expectedRole = new Role(testRoleName);
        when(roleRepository.findById(testRoleId)).thenReturn(java.util.Optional.of(expectedRole));

        // Act
        Role result = roleService.getRole(testRoleId);

        // Assert
        assertNotNull(result);
        assertEquals(testRoleName, result.getRoleName());
        verify(roleRepository, times(1)).findById(testRoleId);
    }

    @Test
    void getRole_NonExistentId_ThrowsException() {
        // Arrange
        when(roleRepository.findById(testRoleId)).thenReturn(java.util.Optional.empty());

        // Act & Assert
        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> roleService.getRole(testRoleId)
        );

        assertEquals("Role not found", exception.getMessage());
        verify(roleRepository, times(1)).findById(testRoleId);
    }

    @Test
    void createRole_NullInput_ThrowsValidationException() {
        // Act & Assert
        assertThrows(
                IllegalArgumentException.class,
                () -> roleService.createRole(null)
        );

        verify(roleRepository, never()).save(any());
    }

    @Test
    void createRole_EmptyRoleName_ThrowsValidationException() {
        // Act & Assert
        assertThrows(
                IllegalArgumentException.class,
                () -> roleService.createRole("")
        );

        verify(roleRepository, never()).save(any());
    }
}