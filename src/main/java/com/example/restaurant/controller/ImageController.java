package com.example.restaurant.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.restaurant.service.ImageService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/download")
public class ImageController {
    private final ImageService imageService;

    @GetMapping("/{fileName}")
    public ResponseEntity<InputStreamResource> getMethodName(@PathVariable String fileName) throws IOException {
        final var getImage = imageService.getImage(fileName);
        return ResponseEntity.ok()
                .contentType(getImage.contentType())
                .body(new InputStreamResource(getImage.stream()));
    }

}