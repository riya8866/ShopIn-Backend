package com.train.basic.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.train.basic.model.Role;
import com.train.basic.model.User;
import com.train.basic.service.UserServiceImpl;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userServiceImpl;

    // -----------------------------
    // Register Customer
    // -----------------------------
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) throws IOException {
        User savedUser = userServiceImpl.registerUser(user, null);
        return ResponseEntity.ok(savedUser);
    }

    // -----------------------------
    // Customer Login
    // -----------------------------
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginData) {
        String email = loginData.get("email");
        String password = loginData.get("password");

        User user = userServiceImpl.login(email, password);

        if (!user.getRole().equals(Role.CUSTOMER)) {
            return ResponseEntity.status(403).body(Map.of("error", "Not a customer"));
        }

        return ResponseEntity.ok(Map.of(
                "message", "Login successful",
                "role", user.getRole(),
                "userId", user.getId()
        ));
    }

    // -----------------------------
    // Admin Login (separate endpoint)
    // -----------------------------
    @PostMapping("/admin-login")
    public ResponseEntity<?> adminLogin(@RequestBody Map<String, String> loginData) {
        String email = loginData.get("email");
        String password = loginData.get("password");

        User user = userServiceImpl.login(email, password);

        if (!user.getRole().equals(Role.ADMIN)) {
            return ResponseEntity.status(403).body(Map.of("error", "Not an admin"));
        }

        return ResponseEntity.ok(Map.of(
                "message", "Admin login successful",
                "role", user.getRole(),
                "userId", user.getId()
        ));
    }

    // -----------------------------
    // CRUD Operations
    // -----------------------------
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userServiceImpl.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userServiceImpl.getUserById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) throws IOException {
        return ResponseEntity.ok(userServiceImpl.updateUser(id, user, null));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userServiceImpl.deleteUser(id);
        return ResponseEntity.ok(Map.of("message", "User deleted successfully"));
    }
}
