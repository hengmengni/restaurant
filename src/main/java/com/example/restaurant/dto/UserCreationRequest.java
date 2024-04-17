package com.example.restaurant.dto;

import org.springframework.web.multipart.MultipartFile;

import com.example.restaurant.exception.MessageException;

public record UserCreationRequest(String name, String gender, String username, String password, String email,
                MultipartFile profileImage, Integer roleId) {
        public UserCreationRequest {
                if (roleId == null) {
                        throw new MessageException("Role Id is null");
                }
        }
}
