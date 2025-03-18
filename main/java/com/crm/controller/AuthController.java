package com.crm.controller;

import com.crm.dto.UserDto;
import com.crm.model.User;
import com.crm.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@Valid @RequestBody User user) {
        // Set default role and authorities for new users
        user.setRole("USER");
        Set<String> authorities = new HashSet<>();
        authorities.add("ROLE_USER");
        user.setAuthorities(authorities);
        
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }

    // In a real application, you would implement login with JWT or other token-based auth
    // This is a simplified example
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        // Authentication would be handled by Spring Security
        // This is just a placeholder
        return ResponseEntity.ok().build();
    }

    static class LoginRequest {
        private String email;
        private String password;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}

