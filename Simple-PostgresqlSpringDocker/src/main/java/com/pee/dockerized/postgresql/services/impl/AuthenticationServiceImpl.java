package com.pee.dockerized.postgresql.services.impl;

import com.pee.dockerized.postgresql.dto.JWTAuthResponse;
import com.pee.dockerized.postgresql.dto.RefreshTokenRequest;
import com.pee.dockerized.postgresql.dto.SignInRequest;
import com.pee.dockerized.postgresql.dto.SignUpRequest;
import com.pee.dockerized.postgresql.entity.Role;
import com.pee.dockerized.postgresql.entity.User;
import com.pee.dockerized.postgresql.repository.UserRepository;
import com.pee.dockerized.postgresql.services.AuthentificationService;
import com.pee.dockerized.postgresql.services.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl   implements AuthentificationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    public User signUp(SignUpRequest newUser) {
        User user = new User();
        user.setUsername(newUser.getUsername());
        user.setEmail(newUser.getEmail());
        user.setPassword(passwordEncoder.encode(newUser.getPassword()));
        user.setRole(Role.USER);
        return userRepository.save(user);
    }
    @Override
    public JWTAuthResponse signIn(SignInRequest userSingIn) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userSingIn.getEmail(), userSingIn.getPassword()));
        var user = userRepository.findByEmail(userSingIn.getEmail()).orElseThrow(() -> new RuntimeException("User not found"));
        var token = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);

        JWTAuthResponse jwtAuthResponse = new JWTAuthResponse();
        jwtAuthResponse.setToken(token);
        jwtAuthResponse.setRefreshToken(refreshToken);
        return jwtAuthResponse;
    }
    @Override
    public JWTAuthResponse refreshToken(RefreshTokenRequest refreshTokenRequest){
        String userEmail = jwtService.extractUsername(refreshTokenRequest.getToken());
        User user = userRepository.findByEmail(userEmail).orElseThrow(() -> new RuntimeException("User not found"));
        if (!jwtService.validateToken(refreshTokenRequest.getToken(), user)){
            throw new RuntimeException("Invalid refresh token");
        }
        var token = jwtService.generateToken(user);
        JWTAuthResponse jwtAuthResponse = new JWTAuthResponse();
        jwtAuthResponse.setToken(token);
        jwtAuthResponse.setRefreshToken(refreshTokenRequest.getToken());
        return jwtAuthResponse;
    }
}
