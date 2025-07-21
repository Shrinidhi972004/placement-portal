package org.shrinidhi.placementbackendportal.service;

import org.shrinidhi.placementbackendportal.model.Drive;
import org.shrinidhi.placementbackendportal.repository.DriveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriveService {
    @Autowired
    private DriveRepository driveRepository;

    public List<Drive> getAllDrives() {
        return driveRepository.findAll();
    }

    public Drive saveDrive(Drive drive) {
        return driveRepository.save(drive);
    }

    public void deleteDrive(Long id) {
        driveRepository.deleteById(id);
    }
}
