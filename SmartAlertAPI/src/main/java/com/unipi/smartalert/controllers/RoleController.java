package com.unipi.smartalert.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {

    @GetMapping
    public String getRoleOfAuthenticatedUser() {
        // Get the authentication object from the security context
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        List<GrantedAuthority> authorities = new ArrayList<>(authentication.getAuthorities());

        // Return the role as a response
        return authorities.isEmpty() ? "ROLE_USER" : authorities.getFirst().getAuthority();
    }

    // GET /roles/{id} - Get a role by ID
    @GetMapping("/{id}")
    public void getOneById(@PathVariable int id) {
        // TODO: Implement
    }

    // POST /roles - Create a new role
    @PostMapping
    public void create() {
        // TODO: Implement
    }

    // PUT /roles/{id} - Update a role
    @PutMapping("/{id}")
    public void update(@PathVariable int id) {
        // TODO: Implement
    }

    // DELETE /roles/{id} - Delete a role
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        // TODO: Implement
    }

}
