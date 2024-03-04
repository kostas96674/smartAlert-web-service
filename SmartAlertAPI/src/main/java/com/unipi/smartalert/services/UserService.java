package com.unipi.smartalert.services;

import com.unipi.smartalert.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User findByEmail(String email);
    void save(User user);

}