package org.shrinidhi.placementbackendportal.service;

import org.shrinidhi.placementbackendportal.dto.StudentProfileDTO;
import org.shrinidhi.placementbackendportal.dto.StudentProfileEditDTO;
import org.shrinidhi.placementbackendportal.model.Student;
import org.shrinidhi.placementbackendportal.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class StudentProfileService {
    @Autowired
    private StudentRepository studentRepository;

    public StudentProfileDTO getProfile(String email) {
        Student student = studentRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("Student not found"));
        return toDTO(student);
    }

    public StudentProfileDTO updateProfile(String email, StudentProfileEditDTO editDTO) {
        Student student = studentRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("Student not found"));
        student.setName(editDTO.getName());
        student.setPhone(editDTO.getPhone());
        student.setCgpa(editDTO.getCgpa());
        student.setBranch(editDTO.getBranch());
        student.setYearOfStudy(editDTO.getYearOfStudy());
        student.setSkills(editDTO.getSkills());
        student.setProjects(editDTO.getProjects());
        student.setAchievements(editDTO.getAchievements());
        student.setSocialLinks(editDTO.getSocialLinks());
        studentRepository.save(student);
        return toDTO(student);
    }

    public String uploadFile(MultipartFile file, String type, String email) throws IOException {
        Student student = studentRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("Student not found"));
        String uploadDir = "uploads/" + email + "/";
        Files.createDirectories(Paths.get(uploadDir));
        String filename = type.equals("resume") ? "resume_" + System.currentTimeMillis() + ".pdf" : "photo_" + System.currentTimeMillis() + ".jpg";
        Path path = Paths.get(uploadDir + filename);
        Files.write(path, file.getBytes());
        if (type.equals("resume")) student.setResumeUrl(path.toString());
        else student.setProfilePhotoUrl(path.toString());
        studentRepository.save(student);
        return path.toString();
    }

    private StudentProfileDTO toDTO(Student student) {
        StudentProfileDTO dto = new StudentProfileDTO();
        dto.setName(student.getName());
        dto.setEmail(student.getEmail());
        dto.setPhone(student.getPhone());
        dto.setCgpa(student.getCgpa());
        dto.setBranch(student.getBranch());
        dto.setYearOfStudy(student.getYearOfStudy());
        dto.setSkills(student.getSkills());
        dto.setResumeUrl(student.getResumeUrl());
        dto.setProfilePhotoUrl(student.getProfilePhotoUrl());
        dto.setProjects(student.getProjects());
        dto.setAchievements(student.getAchievements());
        dto.setSocialLinks(student.getSocialLinks());
        return dto;
    }
}
