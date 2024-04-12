package com.example.restaurant.dto;

import org.springframework.web.multipart.MultipartFile;

public record UserCreationRequest(String name, String gender, String username, String password, String email,
                MultipartFile profileImage, int roleId) {
}
