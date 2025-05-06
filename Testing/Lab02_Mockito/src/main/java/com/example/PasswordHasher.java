package com.example;

public class PasswordHasher {
    public String hashPassword(String password) {
        // In a real application, use a proper hashing algorithm like BCrypt
        // This is a simplified example returning a dummy hash
        return "hashed_" + password;
    }
}