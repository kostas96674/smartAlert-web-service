package com.unipi.smartalert.repositories;

import com.unipi.smartalert.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}