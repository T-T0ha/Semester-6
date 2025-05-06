package com.example;

import com.example.User;

public interface UserRepository {
    User findByUsername(String username);
}