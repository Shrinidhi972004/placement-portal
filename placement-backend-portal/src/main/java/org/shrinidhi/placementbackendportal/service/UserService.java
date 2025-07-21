package org.shrinidhi.placementbackendportal.service;

import org.shrinidhi.placementbackendportal.model.User;
import org.shrinidhi.placementbackendportal.model.Student;
import org.shrinidhi.placementbackendportal.model.Officer;
import org.shrinidhi.placementbackendportal.repository.UserRepository;
import org.shrinidhi.placementbackendportal.dto.UserRegisterDTO;
import org.shrinidhi.placementbackendportal.dto.UserLoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public User registerUser(UserRegisterDTO dto) {
        if (findByEmail(dto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already exists");
        }
        if ("STUDENT".equalsIgnoreCase(dto.getRole())) {
            Student student = new Student();
            student.setName(dto.getName());
            student.setEmail(dto.getEmail());
            student.setPassword(passwordEncoder.encode(dto.getPassword()));
            student.setCgpa(dto.getCgpa());
            student.setSkills(dto.getSkills());
            return userRepository.save(student);
        } else if ("OFFICER".equalsIgnoreCase(dto.getRole())) {
            Officer officer = new Officer();
            officer.setName(dto.getName());
            officer.setEmail(dto.getEmail());
            officer.setPassword(passwordEncoder.encode(dto.getPassword()));
            return userRepository.save(officer);
        } else {
            throw new IllegalArgumentException("Invalid role: " + dto.getRole());
        }
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public boolean checkPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}
