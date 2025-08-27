package com.train.basic.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.train.basic.model.Admin;
import com.train.basic.service.AdminServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class AdminController {

    private final AdminServiceImpl adminService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginData) {
        String email = loginData.get("email");
        String password = loginData.get("password");

        Admin admin;
        try {
            admin = adminService.loginAdmin(email, password);
        } catch (Exception e) {
            // Catch any service exception and return 401
            return ResponseEntity.status(401).body(Map.of("error", "Invalid credentials"));
        }

        if (admin == null) {
            return ResponseEntity.status(401).body(Map.of("error", "Invalid credentials"));
        }

        // Successful login response
        Map<String, Object> response = Map.of(
            "message", "Login successful",
            "adminId", admin.getId(),
            "role", "ADMIN"
        );

        return ResponseEntity.ok(response);
    }
}
