package com.example.restaurant.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.UUID;
import java.util.regex.Pattern;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.restaurant.dto.ReturnImage;
import com.example.restaurant.exception.FileNotFoundException;
import com.example.restaurant.exception.FileStorageException;

@Service
public class ImageService {
    static final String FOLDER = "/pos/restaurant/";

    public String save(MultipartFile file) throws FileStorageException {
        final var directory = new File(FOLDER);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        final var fileId = UUID.randomUUID();
        var fileName = fileId.toString();
        final var originalFileName = file.getOriginalFilename();
        if (originalFileName != null) {
            final var nameParts = originalFileName.split(Pattern.quote("."));

            if (nameParts.length > 1) {
                fileName += "." + nameParts[nameParts.length - 1];
            }
        }

        final var savedFile = new File(FOLDER + fileName);
        try (final var os = new FileOutputStream(savedFile)) {
            os.write(file.getBytes());
        } catch (IOException e) {
            throw new FileStorageException("Failed to store file " + fileName, e);
        }

        return fileName;
    }

    public File getFile(String fileName) {
        final var file = new File(FOLDER + fileName);
        if (!file.exists()) {
            throw new FileNotFoundException("File Not Found");
        }
        return file;
    }

    public ReturnImage getImage(String fileName) throws IOException {
        final var file = getFile(fileName);
        final var path = file.toPath();
        var mimeType = Files.probeContentType(path);
        if (mimeType == null) {
            mimeType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
        }
        final var contentType = MediaType.parseMediaType(mimeType);
        final var stream = new FileInputStream(file);
        return new ReturnImage(contentType, stream);
    }

    public String getImageUrl(String fileName) {
        if (fileName == null) {
            return "";
        }
        return ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(fileName)
                .toUriString();
    }

}
