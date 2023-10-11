package com.main.scannersilicatback.services;

import com.main.scannersilicatback.entities.Role;
import com.main.scannersilicatback.entities.User;
import com.main.scannersilicatback.repositories.UserRepository;
import com.main.scannersilicatback.requests.AuthentificationRequest;
import com.main.scannersilicatback.requests.RegisterRequest;
import com.main.scannersilicatback.responses.AuthentificationResponse;
import com.main.scannersilicatback.security.config.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.authentication.AuthenticationManager;

@Service
@RequiredArgsConstructor
public class AuthentificationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final  AuthenticationManager authenticationManager;
    public AuthentificationResponse register(RegisterRequest registerRequest) {
        var user = User
                .builder()
                .lastName(registerRequest.getLastName())
                .firstName(registerRequest.getFirstName())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPasswoord()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
        var token = jwtService.generateToken(user);

        return AuthentificationResponse.builder().token(token).build();
    }

    public AuthentificationResponse authenticate(AuthentificationRequest authRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.getEmail(),
                        authRequest.getPassword()
                )
        );
        var user = userRepository.findByEmail(authRequest.getEmail()).orElseThrow();
        var token = jwtService.generateToken(user);

        return AuthentificationResponse.builder().token(token).build();
    }
}
