package com.example.restaurant.service;

import java.io.File;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.restaurant.dto.UserCreationRequest;
import com.example.restaurant.exception.UserNotFoundException;
import com.example.restaurant.model.User;
import com.example.restaurant.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ImageService imageService;

    public List<User> getListUser() {
        return userRepository.findAll();
    }

    public User addUser(UserCreationRequest user) {
        final var fileName = imageService.save(user.profileImage());
        final var imageUrl = imageService.getImageUrl(fileName);
        final var savedUser = User
                .builder()
                .name(user.name())
                .gender(user.gender())
                .username(user.username())
                .password(user.password())
                .email(user.email())
                .profileImage(imageUrl)
                .build();
        return userRepository.save(savedUser);
    }

    public void deleteUser(int id) {
        final var isExisted = userRepository.existsById(id);
        if (!isExisted) {
            throw new UserNotFoundException("User ID : " + id + " Not Found");
        }

        String directoryPath = "/pos/restaurant/";

        final var deleteImage = userRepository.findById(id).get().getProfileImage();
        int lastIndex = deleteImage.lastIndexOf('/');
        String filename = deleteImage.substring(lastIndex + 1);
        File imageToDelete = new File(directoryPath + filename);
        if (imageToDelete.exists()) {
            imageToDelete.delete();
        }
        userRepository.deleteById(id);
    }
}
