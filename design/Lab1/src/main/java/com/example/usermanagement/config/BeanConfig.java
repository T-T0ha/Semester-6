package com.example.usermanagement.config;

import com.example.usermanagement.application.interfaces.RoleRepository;
import com.example.usermanagement.application.interfaces.UserRepository;
import com.example.usermanagement.infrastructure.persistence.RoleRepositoryImpl;
import com.example.usermanagement.infrastructure.persistence.UserRepositoryImpl;
import com.example.usermanagement.infrastructure.persistence.RoleJpaRepository;
import com.example.usermanagement.infrastructure.persistence.UserJpaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {
    @Bean
    public UserRepository userRepository(UserJpaRepository userJpaRepository) {
        return new UserRepositoryImpl(userJpaRepository);
    }

    @Bean
    public RoleRepository roleRepository(RoleJpaRepository roleJpaRepository) {
        return new RoleRepositoryImpl(roleJpaRepository);
    }
}