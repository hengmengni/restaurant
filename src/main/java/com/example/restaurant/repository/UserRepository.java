package com.example.restaurant.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.restaurant.model.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {
    Optional<Users> findByName(String username);
}
