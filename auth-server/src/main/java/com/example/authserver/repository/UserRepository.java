package com.example.authserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.authserver.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    // Custom query to find a user by their email
    Optional<User> findByEmail(String email);
}
