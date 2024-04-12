package com.example.restaurant.service;

import java.io.File;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.restaurant.dto.UserCreationRequest;
import com.example.restaurant.exception.UserNotFoundException;
import com.example.restaurant.model.Users;
import com.example.restaurant.repository.UserRepository;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final ImageService imageService;

    private final PasswordEncoder passwordEncoder;

    public List<Users> getListUser() {
        return userRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Users> userDetail = userRepository.findByName(username);

        return userDetail.map(UserInfoDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found " + username));
    }

    public Users addUser(UserCreationRequest user) {
        final var fileName = imageService.save(user.profileImage());
        final var imageUrl = imageService.getImageUrl(fileName);
        final var savedUser = Users
                .builder()
                .name(user.name())
                .gender(user.gender())
                .username(user.username())
                .password(passwordEncoder.encode(user.password()))
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
