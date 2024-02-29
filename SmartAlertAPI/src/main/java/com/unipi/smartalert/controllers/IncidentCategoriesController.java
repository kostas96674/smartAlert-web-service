package com.unipi.smartalert.controllers;

import com.unipi.smartalert.dtos.IncidentCategoryDTO;
import com.unipi.smartalert.models.IncidentCategory;
import com.unipi.smartalert.repositories.IncidentCategoryRepository;
import com.unipi.smartalert.services.IncidentCategoryService;
import lombok.AllArgsConstructor;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/incident-categories")
@AllArgsConstructor
public class IncidentCategoriesController {

    private final IncidentCategoryService service;

    // GET /incidents - Get all incidents
    @GetMapping
    public List<IncidentCategoryDTO> getAll() {
        Locale locale = LocaleContextHolder.getLocale();
        return service.findByLanguage(locale.getLanguage());
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
