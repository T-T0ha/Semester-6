package com.example;


import com.example.User;
import com.example.UserRepository;
import com.example.PasswordHasher;

public class UserService {
    private final UserRepository userRepository;
    private final PasswordHasher passwordHasher;

    public UserService(UserRepository userRepository, PasswordHasher passwordHasher) {
        this.userRepository = userRepository;
        this.passwordHasher = passwordHasher;
    }

    public User authenticate(String username, String password) throws Exception {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new Exception("User not found: " + username);
        }
        String hashedInputPassword = passwordHasher.hashPassword(password);
        if (!user.getPasswordHash().equals(hashedInputPassword)) {
            throw new Exception("Invalid password for user: " + username);
        }
        return user;
    }

}
