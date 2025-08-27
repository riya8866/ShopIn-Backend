package com.train.basic.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.train.basic.model.Admin;
import com.train.basic.repository.AdminRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;

    // Register admin
    @Override
    public Admin registerAdmin(Admin admin) {
        if (adminRepository.existsByEmail(admin.getEmail())) {
            throw new RuntimeException("Email already registered!");
        }
        return adminRepository.save(admin);
    }

    // Login admin
    @Override
    public Admin loginAdmin(String email, String password) {
        Optional<Admin> adminOpt = adminRepository.findByEmail(email);
        if (adminOpt.isPresent()) {
            Admin admin = adminOpt.get();
            if (admin.getPassword().equals(password)) { // for real app → BCryptPasswordEncoder
                return admin;
            }
        }
        throw new RuntimeException("Invalid email or password!");
    }
}
