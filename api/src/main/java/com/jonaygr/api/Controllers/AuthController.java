package com.jonaygr.api.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jonaygr.api.DTOs.LoginRequest;
import com.jonaygr.api.DTOs.TokenAuth;
import com.jonaygr.api.Services.TrabajadorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins="*", allowCredentials = "false")
public class AuthController {
    private final TrabajadorService trabajadorService;

    private final ObjectMapper objectMapper;


    public AuthController(TrabajadorService trabajadorService, ObjectMapper objectMapper) {
        this.trabajadorService = trabajadorService;
        this.objectMapper = objectMapper;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest login) {
        try {
            TokenAuth tokenAuth = trabajadorService.authenticateUser(login.getEmail(), login.getPassword());
            return ResponseEntity.ok(tokenAuth.getToken());
        } catch (Exception e) {
            return ResponseEntity.status(401).build(); // Unauthorized
        }
    }
}
