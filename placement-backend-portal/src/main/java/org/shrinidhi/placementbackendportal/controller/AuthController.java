package org.shrinidhi.placementbackendportal.controller;

import org.shrinidhi.placementbackendportal.dto.UserRegisterDTO;
import org.shrinidhi.placementbackendportal.dto.UserLoginDTO;
import org.shrinidhi.placementbackendportal.model.User;
import org.shrinidhi.placementbackendportal.service.UserService;
import org.shrinidhi.placementbackendportal.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRegisterDTO dto) {
        try {
            User user = userService.registerUser(dto);
            String role = user instanceof org.shrinidhi.placementbackendportal.model.Student ? "STUDENT" : "OFFICER";
            String token = JwtUtil.generateToken(user.getEmail(), role);
            return ResponseEntity.ok(new AuthResponse(token, role));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginDTO loginDto) {
        Optional<User> userOpt = userService.findByEmail(loginDto.getEmail());
        if (userOpt.isPresent() && userService.checkPassword(loginDto.getPassword(), userOpt.get().getPassword())) {
            User user = userOpt.get();
            String role = user instanceof org.shrinidhi.placementbackendportal.model.Student ? "STUDENT" : "OFFICER";
            String token = JwtUtil.generateToken(user.getEmail(), role);
            return ResponseEntity.ok(new AuthResponse(token, role));
        }
        return ResponseEntity.status(401).body("Invalid credentials");
    }
}
