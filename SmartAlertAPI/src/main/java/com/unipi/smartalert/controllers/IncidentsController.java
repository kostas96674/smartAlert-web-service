package com.unipi.smartalert.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/incidents")
public class IncidentsController {

    // GET /incidents - Get all incidents
    @GetMapping
    public void getAll() {
        // TODO: Implement
    }

    // GET /incidents/{id} - Get an incident by ID
    @GetMapping("/{id}")
    public void getOneById(@PathVariable int id) {
        // TODO: Implement
    }

    // POST /incidents - Create a new incident
    @PostMapping
    public void create() {
        // TODO: Implement
    }

    // PUT /incidents/{id} - Update an incident
    @PutMapping("/{id}")
    public void update(@PathVariable int id) {
        // TODO: Implement
    }

    // DELETE /incidents/{id} - Delete an incident
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        // TODO: Implement
    }

}
