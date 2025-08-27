package com.train.basic.service;

import com.train.basic.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface UserService {

    User registerUser(User user, MultipartFile profileImage) throws IOException;

    User login(String email, String password);

    List<User> getAllUsers();

    User getUserById(Long id);

    User updateUser(Long id, User user, MultipartFile profileImage) throws IOException;

    void deleteUser(Long id);
}
