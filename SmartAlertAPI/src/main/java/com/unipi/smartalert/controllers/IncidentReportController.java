package com.unipi.smartalert.controllers;

import com.unipi.smartalert.dtos.ReportDTO;
import com.unipi.smartalert.services.IncidentReportService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@AllArgsConstructor
@RequestMapping("/reports")
public class IncidentReportController {

    private final IncidentReportService service;

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
    public void create(
            @RequestPart(name = "report") ReportDTO report,
            @RequestPart(name = "image") MultipartFile image
    ) {
        service.saveReport(report, image);
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
