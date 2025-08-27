package com.train.basic.dto;

import com.train.basic.model.Role;

public class AuthResponse {
    private Long id;
    private String email;
    private Role role;

    public AuthResponse(Long id, String email, Role role) {
        this.id = id;
        this.email = email;
        this.role = role;
    }
    public Long getId() { return id; }
    public String getEmail() { return email; }
    public Role getRole() { return role; }
}

