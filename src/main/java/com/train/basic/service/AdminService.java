package com.train.basic.service;

import com.train.basic.model.Admin;

public interface AdminService {

    Admin registerAdmin(Admin admin);

    Admin loginAdmin(String email, String password);
}
