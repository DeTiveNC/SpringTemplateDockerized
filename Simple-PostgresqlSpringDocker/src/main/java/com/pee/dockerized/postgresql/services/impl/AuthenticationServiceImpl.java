package com.pee.dockerized.postgresql.services.impl;

import com.pee.dockerized.postgresql.dto.JWTAuthResponse;
import com.pee.dockerized.postgresql.dto.RefreshTokenRequest;
import com.pee.dockerized.postgresql.dto.SignInRequest;
import com.pee.dockerized.postgresql.dto.SignUpRequest;
import com.pee.dockerized.postgresql.entity.Role;
import com.pee.dockerized.postgresql.entity.User;
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
public class AuthenticationServiceImpl  implements AuthentificationService {
    private final UserServiceImpl userService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    public User signUp(SignUpRequest newUser) throws RuntimeException {
        try {
            User user = new User();
            user.setUserIdent(newUser.username());
            user.setEmail(newUser.email());
            user.setPassword(passwordEncoder.encode(newUser.password()));
            user.setRole(Role.USER);
            return userService.saveUser(user);
        }  catch (Exception e){
            throw new RuntimeException("Invalid email/password");
        }
    }
    @Override
    public JWTAuthResponse signIn(SignInRequest userSingIn) throws RuntimeException {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userSingIn.email(), userSingIn.password()));
        try {
            var user = userService.findByEmail(userSingIn.email());
            var token = jwtService.generateToken(user);
            var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);
            return new JWTAuthResponse(token, refreshToken);
        } catch (Exception e){
            throw new RuntimeException("Invalid email/password");
        }
    }
    @Override
    public JWTAuthResponse refreshToken(RefreshTokenRequest refreshTokenRequest) throws RuntimeException {
        String userEmail = jwtService.extractUsername(refreshTokenRequest.token());
        try {
           User user = userService.findByEmail(userEmail);
            if (!jwtService.validateToken(refreshTokenRequest.token(), user)){
                throw new RuntimeException("Invalid refresh token");
            }
            var token = jwtService.generateToken(user);
            return new JWTAuthResponse(token, refreshTokenRequest.token());
        } catch (RuntimeException e){
            throw new RuntimeException("User not found");
        }
    }
}
