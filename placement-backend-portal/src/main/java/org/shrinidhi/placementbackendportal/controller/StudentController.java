package org.shrinidhi.placementbackendportal.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/student")
public class StudentController {
    @PreAuthorize("hasAuthority('STUDENT')")
    @GetMapping("/dashboard")
    public ResponseEntity<String> getStudentDashboard() {
        return ResponseEntity.ok("Welcome to the Student Dashboard. You have STUDENT access.");
    }
}
