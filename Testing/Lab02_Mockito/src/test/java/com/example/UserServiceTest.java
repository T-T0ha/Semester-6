package com.example;

import com.example.User;
import com.example.UserRepository;
import com.example.PasswordHasher;
import com.example.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordHasher passwordHasher;

    @InjectMocks
    private UserService userService;

    private User testUser;
    private static final String USERNAME = "testuser";
    private static final String NON_EXISTENT_USERNAME = "nonexistentuser";
    private static final String PLAIN_PASSWORD = "password123";
    private static final String WRONG_PASSWORD = "wrongpassword";
    private static final String HASHED_PASSWORD = "hashedPassword123";
    private static final String WRONG_HASHED_PASSWORD = "wrongHashedPassword";

    @BeforeEach
    void setUp() {
        testUser = new User(USERNAME, HASHED_PASSWORD);


        lenient().when(userRepository.findByUsername(USERNAME)).thenReturn(testUser);
        lenient().when(userRepository.findByUsername(NON_EXISTENT_USERNAME)).thenReturn(null);

        lenient().when(passwordHasher.hashPassword(PLAIN_PASSWORD)).thenReturn(HASHED_PASSWORD);
        lenient().when(passwordHasher.hashPassword(WRONG_PASSWORD)).thenReturn(WRONG_HASHED_PASSWORD);
    }

    @Test
    void authenticate_withValidCredentials_returnsUser() throws Exception {

        User result = userService.authenticate(USERNAME, PLAIN_PASSWORD);


        assertEquals(testUser, result);
        verify(userRepository).findByUsername(USERNAME);
        verify(passwordHasher).hashPassword(PLAIN_PASSWORD);
    }

    @Test
    void authenticate_withInvalidPassword_throwsAuthenticationException() {

        Exception exception = assertThrows(
                Exception.class,
                () -> userService.authenticate(USERNAME, WRONG_PASSWORD)
        );

        assertEquals("Invalid password for user: " + USERNAME, exception.getMessage());
        verify(userRepository).findByUsername(USERNAME);
        verify(passwordHasher).hashPassword(WRONG_PASSWORD);
    }

    @Test
    void authenticate_withNonExistentUser_throwsAuthenticationException() {

        Exception exception = assertThrows(
                Exception.class,
                () -> userService.authenticate(NON_EXISTENT_USERNAME, PLAIN_PASSWORD)
        );

        assertEquals("User not found: " + NON_EXISTENT_USERNAME, exception.getMessage());
        verify(userRepository).findByUsername(NON_EXISTENT_USERNAME);
        verify(passwordHasher, never()).hashPassword(anyString());
    }
}