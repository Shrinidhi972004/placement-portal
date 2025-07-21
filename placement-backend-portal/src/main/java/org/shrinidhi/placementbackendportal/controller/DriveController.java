package org.shrinidhi.placementbackendportal.controller;

import org.shrinidhi.placementbackendportal.model.Drive;
import org.shrinidhi.placementbackendportal.service.DriveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/drives")
@CrossOrigin(origins = "http://localhost:3000")
public class DriveController {
    @Autowired
    private DriveService driveService;

    @GetMapping
    public List<Drive> getAllDrives() {
        return driveService.getAllDrives();
    }

    @PostMapping
    public Drive createDrive(@RequestBody Drive drive) {
        return driveService.saveDrive(drive);
    }

    @DeleteMapping("/{id}")
    public void deleteDrive(@PathVariable Long id) {
        driveService.deleteDrive(id);
    }
}
