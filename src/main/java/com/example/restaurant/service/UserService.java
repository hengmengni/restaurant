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
import com.example.restaurant.exception.MessageException;
import com.example.restaurant.exception.NotFoundException;
import com.example.restaurant.model.User;
import com.example.restaurant.repository.RoleUserRepository;
import com.example.restaurant.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final ImageService imageService;
    private final PasswordEncoder passwordEncoder;
    private final RoleUserRepository roleUserRepository;

    public List<User> getListUser() {
        return userRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> userDetail = userRepository.findByName(username);

        return userDetail.map(UserInfoDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found " + username));
    }

    public User addUser(UserCreationRequest user) {
        final var fileName = imageService.save(user.profileImage());
        final var imageUrl = imageService.getImageUrl(fileName);
        final var roleUser = roleUserRepository.findById(user.roleId()).orElse(null);
        if (roleUser == null) {
            throw new NotFoundException("Role ID : " + user.roleId() + " Not found");
        }
        if (user.name() == null) {
            throw new MessageException("Name is null");
        } else if (user.username() == null) {
            throw new MessageException("Username is null");
        } else if (user.password() == null) {
            throw new MessageException("Password is null");
        } else if (user.email() == null) {
            throw new MessageException("Email is null");
        } else if (user.gender() == null) {
            throw new MessageException("Gender is null");
        } else if (user.roleId() == 0) {
            throw new MessageException("Role ID is null");
        }
        final var savedUser = User
                .builder()
                .name(user.name())
                .gender(user.gender())
                .username(user.username())
                .password(passwordEncoder.encode(user.password()))
                .email(user.email())
                .profileImage(imageUrl)
                .role(roleUser)
                .build();
        return userRepository.save(savedUser);
    }

    public void deleteUser(int id) {
        final var isExisted = userRepository.existsById(id);
        if (!isExisted) {
            throw new NotFoundException("User ID : " + id + " Not Found");
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
