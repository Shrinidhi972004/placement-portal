package org.shrinidhi.placementbackendportal.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/officer")
public class OfficerController {
    @PreAuthorize("hasAuthority('OFFICER')")
    @GetMapping("/dashboard")
    public ResponseEntity<String> getOfficerDashboard() {
        return ResponseEntity.ok("Welcome to the Officer Dashboard. You have OFFICER access.");
    }
}
