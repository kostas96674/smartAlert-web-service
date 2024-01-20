package com.unipi.smartalert.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UsersController {

    // GET /users - Get all users
    @GetMapping
    public void getAll() {
        // TODO: Implement
    }

    // GET /users/{id} - Get a user by ID
    @GetMapping("/{id}")
    public void getOneById(@PathVariable int id) {
        // TODO: Implement
    }

    // POST /users - Create a new user
    @PostMapping
    public void create() {
        // TODO: Implement
    }

    // PUT /users/{id} - Update a user
    @PutMapping("/{id}")
    public void update(@PathVariable int id) {
        // TODO: Implement
    }

    // DELETE /users/{id} - Delete a user
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        // TODO: Implement
    }

    // ====== User Roles ====== //

    // PUT /users/{userId}/roles/{roleId} - Add a role to a user
    @PutMapping("/{userId}/roles/{roleId}")
    public void addRole(@PathVariable int userId, @PathVariable int roleId) {
        // TODO: Implement
    }

    // DELETE /users/{userId}/roles/{roleId} - Remove a role from a user
    @DeleteMapping("/{userId}/roles/{roleId}")
    public void removeRole(@PathVariable int userId, @PathVariable int roleId) {
        // TODO: Implement
    }

    // ====== User Reports ====== //

    // GET /users/{userId}/reports - Get all the reports of a user
    @GetMapping("/{userId}/reports")
    public void getAllReports(@PathVariable int userId) {
        // TODO: Implement
    }

    // GET /users/{userId}/reports/{reportId} - Get a report of a user by ID
    @GetMapping("/{userId}/reports/{reportId}")
    public void getReportById(@PathVariable int userId, @PathVariable int reportId) {
        // TODO: Implement
    }

    // POST /users/{userId}/reports - Create a new report for a user
    @PostMapping("/{userId}/reports")
    public void createReport(@PathVariable int userId) {
        // TODO: Implement
    }

    // PUT /users/{userId}/reports/{reportId} - Update a user's report
    @PutMapping("/{userId}/reports/{reportId}")
    public void updateReport(@PathVariable int userId, @PathVariable int reportId) {
        // TODO: Implement
    }

    // DELETE /users/{userId}/reports/{reportId} - Delete a user's report
    @DeleteMapping("/{userId}/reports/{reportId}")
    public void deleteReport(@PathVariable int userId, @PathVariable int reportId) {
        // TODO: Implement
    }

}
