package com.example.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.restaurant.model.RoleUser;

public interface RoleUserRepository extends JpaRepository<RoleUser, Integer> {

}