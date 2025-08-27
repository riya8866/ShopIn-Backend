package com.train.basic.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.train.basic.model.Role;
import com.train.basic.model.User;
import com.train.basic.repository.UserRepository;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    // -------------------
    // Register User (always CUSTOMER)
    // -------------------
    @Override
    public User registerUser(User user, MultipartFile profileImage) throws IOException {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already registered!");
        }

        if (profileImage != null && !profileImage.isEmpty()) {
            user.setProfileImage(profileImage.getBytes());
        }

        user.setRole(Role.CUSTOMER); // force customer role
        user.setActive(true);

        return userRepository.save(user);
    }

    // -------------------
    // Login
    // -------------------
    @Override
    public User login(String email, String password) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (user.getPassword().equals(password)) { // production → use BCryptPasswordEncoder
                return user;
            }
        }
        throw new RuntimeException("Invalid email or password!");
    }

    // -------------------
    // CRUD Operations
    // -------------------
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id " + id));
    }

    @Override
    public User updateUser(Long id, User updatedUser, MultipartFile profileImage) throws IOException {
        User user = getUserById(id);

        user.setFullName(updatedUser.getFullName());
        user.setPhoneNumber(updatedUser.getPhoneNumber());
        user.setDob(updatedUser.getDob());
        user.setGender(updatedUser.getGender());
        user.setAddress(updatedUser.getAddress());
        user.setCity(updatedUser.getCity());
        user.setState(updatedUser.getState());
        user.setCountry(updatedUser.getCountry());
        user.setPincode(updatedUser.getPincode());

        if (profileImage != null && !profileImage.isEmpty()) {
            user.setProfileImage(profileImage.getBytes());
        }

        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
