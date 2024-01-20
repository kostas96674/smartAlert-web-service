package com.unipi.smartalert.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reports")
public class IncidentReportsController {

    // GET /reports - Get all incident reports
    @GetMapping
    public void getAll() {
        // TODO: Implement
    }

    // GET /reports/{id} - Get an incident report by ID
    @GetMapping("/{id}")
    public void getOneById(@PathVariable int id) {
        // TODO: Implement
    }

    // POST /reports - Create a new incident report
    @PostMapping
    public void create() {
        // TODO: Implement
    }

    // PUT /reports/{id} - Update an incident report
    @PutMapping("/{id}")
    public void update(@PathVariable int id) {
        // TODO: Implement
    }

    // DELETE /reports/{id} - Delete an incident report
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        // TODO: Implement
    }

}
