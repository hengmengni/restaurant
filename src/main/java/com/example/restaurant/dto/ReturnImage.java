package com.example.restaurant.dto;

import java.io.FileInputStream;

import org.springframework.http.MediaType;

public record ReturnImage(MediaType contentType, FileInputStream stream) {

}
