package com.unipi.smartalert.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/roles")
public class RoleController {

    // GET /roles - Get all roles
    @GetMapping
    public void getAll() {
        // TODO: Implement
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
