package org.example.jobsrestfulapi.controller;

import jakarta.validation.Valid;
import org.example.jobsrestfulapi.dto.LoginDTO;
import org.example.jobsrestfulapi.dto.SignUpDTO;
import org.example.jobsrestfulapi.exception.ResourcesNotFound;
import org.example.jobsrestfulapi.model.Role;
import org.example.jobsrestfulapi.model.User;
import org.example.jobsrestfulapi.repository.RoleRepository;
import org.example.jobsrestfulapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;

    @PostMapping("/signin")
    public ResponseEntity authenticateUser(@Valid @RequestBody LoginDTO loginDTO){
        Authentication authentication =authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDTO.getEmail(),loginDTO.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return ResponseEntity.ok("User signed-in successfully");
    }
    @PostMapping("/signup")
    public ResponseEntity registerUser(@Valid @RequestBody SignUpDTO signUpDTO){
        if(userRepository.findByEmail(signUpDTO.getEmail()).isPresent()){
            return ResponseEntity.badRequest().body("Email already in use");
        }
        if(userRepository.findByUsername(signUpDTO.getUsername()).isPresent()){
            return ResponseEntity.badRequest().body("User name already in use");
        }
        User user=new User();
        user.setUsername(signUpDTO.getUsername());
        user.setEmail(signUpDTO.getEmail());
        user.setPassword(passwordEncoder.encode(signUpDTO.getPassword()));

        Role role=roleRepository.findByName("USER").orElseThrow(
                ()->new ResourcesNotFound("Role user is not found")
        );
        user.setRoles(Collections.singleton(role));
        userRepository.save(user);
        return ResponseEntity.ok("User registered successfully");
    }

}
