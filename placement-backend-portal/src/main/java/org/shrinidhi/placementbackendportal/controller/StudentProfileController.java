package org.shrinidhi.placementbackendportal.controller;

import org.shrinidhi.placementbackendportal.dto.StudentProfileDTO;
import org.shrinidhi.placementbackendportal.dto.StudentProfileEditDTO;
import org.shrinidhi.placementbackendportal.service.StudentProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/student/profile")
@PreAuthorize("hasAuthority('STUDENT')")
public class StudentProfileController {
    @Autowired
    private StudentProfileService profileService;

    @GetMapping
    public ResponseEntity<StudentProfileDTO> getProfile(Authentication auth) {
        String email = auth.getName();
        return ResponseEntity.ok(profileService.getProfile(email));
    }

    @PutMapping("/edit")
    public ResponseEntity<StudentProfileDTO> editProfile(
        Authentication auth,
        @RequestBody StudentProfileEditDTO editDTO
    ) {
        String email = auth.getName();
        return ResponseEntity.ok(profileService.updateProfile(email, editDTO));
    }

    @PostMapping("/photo")
    public ResponseEntity<?> uploadPhoto(
        Authentication auth,
        @RequestParam("file") MultipartFile file
    ) throws Exception {
        String email = auth.getName();
        String url = profileService.uploadFile(file, "photo", email);
        return ResponseEntity.ok(java.util.Map.of("profilePhotoUrl", url));
    }

    @PostMapping("/resume")
    public ResponseEntity<?> uploadResume(
        Authentication auth,
        @RequestParam("file") MultipartFile file
    ) throws Exception {
        String email = auth.getName();
        String url = profileService.uploadFile(file, "resume", email);
        return ResponseEntity.ok(java.util.Map.of("resumeUrl", url));
    }
}
