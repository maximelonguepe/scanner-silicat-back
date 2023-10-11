package com.main.scannersilicatback.controllers;

import com.main.scannersilicatback.requests.AuthentificationRequest;
import com.main.scannersilicatback.requests.RegisterRequest;
import com.main.scannersilicatback.responses.AuthentificationResponse;
import com.main.scannersilicatback.services.AuthentificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthentificationController {
    private final AuthentificationService authentificationService;
    @PostMapping("/register")
    public ResponseEntity<AuthentificationResponse> register(@RequestBody RegisterRequest registerRequest){
        return ResponseEntity.ok(authentificationService.register(registerRequest));
    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthentificationResponse> authenticate(@RequestBody AuthentificationRequest authRequest){
        return ResponseEntity.ok(authentificationService.authenticate(authRequest));
    }

}
