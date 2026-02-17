package com.khapara.userservice.controller;

import com.khapara.userservice.dto.ChangePasswordDTO;
import com.khapara.userservice.dto.LoginDTO;
import com.khapara.userservice.dto.RegDTO;
import com.khapara.userservice.entity.User;
import com.khapara.userservice.repository.UserRepository;
import com.khapara.userservice.security.CustomUserDetailsService;
import com.khapara.userservice.security.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRep;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private CustomUserDetailsService userDetails;
    @Autowired
    private AuthenticationManager authManager;

    @PostMapping("/public/sign-up")
    public ResponseEntity<?> register(@Valid @RequestBody RegDTO regDTO) {

        try {
            if (userRep.findByEmail(regDTO.getEmail()).isPresent())
                return ResponseEntity.status(HttpStatus.FOUND).body("Account already exists");

            User user = new User();
            user.setFullName(regDTO.getFullName());
            user.setEmail(regDTO.getEmail());
            user.setPassword(passwordEncoder.encode(regDTO.getPassword()));
            user.setIsActive(true);
            userRep.save(user);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(Map.of("message", "User registered successfully"));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error_message", "Unable to register the user due to server error"));
        }

    }

    @PostMapping("/public/sign-in")
    public ResponseEntity<?> Login(@Valid @RequestBody LoginDTO loginDTO) {

        try {
            Authentication authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword())
            );

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String token = jwtUtil.generateToken(userDetails);

            return ResponseEntity
                    .ok()
                    .body(Map.of("message", "User signed in successfully", "token", token));

        } catch (AuthenticationException e) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error_message", "Invalid email or password"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @PostMapping("/private/change-password")
    public ResponseEntity<?> changePassword(@AuthenticationPrincipal UserDetails userDetails,
                                            @RequestBody ChangePasswordDTO dto) {
        try {
            User user = userRep.findByEmail(userDetails.getUsername())
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));

            if (!passwordEncoder.matches(dto.getOldPassword(), user.getPassword())) {
                throw new IllegalArgumentException("Old password is incorrect");
            }
            user.setPassword(passwordEncoder.encode(dto.getNewPassword()));
            userRep.save(user);
            return ResponseEntity.ok(Map.of("message", "Password changed successfully"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", e.getMessage()));
        }
    }

}
