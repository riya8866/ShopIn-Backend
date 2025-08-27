package com.train.basic.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.train.basic.model.Role;
import com.train.basic.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

	List<User> findByRole(Role role);
}