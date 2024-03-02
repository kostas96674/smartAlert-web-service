package com.unipi.smartalert.controllers;

import com.unipi.smartalert.enums.GroupStatus;
import com.unipi.smartalert.services.ReportGroupService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/groups")
public class ReportGroupController {

    private final ReportGroupService service;

    @PatchMapping("/{id}/accept")
    public ResponseEntity<Void> acceptGroup(@PathVariable long id) {
        // Change the group status to ACCEPTED
        service.changeStatus(id, GroupStatus.ACCEPTED);
        // Return 204 if no exception was thrown
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{id}/decline")
    public ResponseEntity<Void> declineGroup(@PathVariable long id) {
        // Change the group status to DECLINED
        service.changeStatus(id, GroupStatus.DECLINED);
        // Return 204 if no exception was thrown
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
