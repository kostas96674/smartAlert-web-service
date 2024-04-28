package com.unipi.smartalert.controllers;

import com.unipi.smartalert.models.User;
import com.unipi.smartalert.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private UserService service;

    @PostMapping("/signup")
    public ResponseEntity<Void> signUp(@RequestBody User user) {
        service.save(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
