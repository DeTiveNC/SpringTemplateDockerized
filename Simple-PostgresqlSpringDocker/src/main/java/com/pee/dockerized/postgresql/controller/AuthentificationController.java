package com.pee.dockerized.postgresql.controller;

import com.pee.dockerized.postgresql.dto.JWTAuthResponse;
import com.pee.dockerized.postgresql.dto.RefreshTokenRequest;
import com.pee.dockerized.postgresql.dto.SignInRequest;
import com.pee.dockerized.postgresql.dto.SignUpRequest;
import com.pee.dockerized.postgresql.entity.User;
import com.pee.dockerized.postgresql.services.AuthentificationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Tag(name = "Auth Enpoint", description = "Easy Endpoint for jwt docker project")
public class AuthentificationController {
    private final AuthentificationService authentificationService;

    @PostMapping("/signup")
    @Operation(method = "POST", summary = "Post endpoint for sign up a user")
    @ApiResponse(responseCode = "200", description = "Add a user and jwt token")
    public ResponseEntity<User> signUp(@RequestBody SignUpRequest signUp){
        return ResponseEntity.ok(authentificationService.signUp(signUp));
    }
    @PostMapping("/signin")
    @Operation(method = "POST", summary = "Post endpoint for sign in a user")
    @ApiResponse(responseCode = "201", description = "Add a user")
    public ResponseEntity<JWTAuthResponse> signIn(@RequestBody SignInRequest signIn){
        return ResponseEntity.ok(authentificationService.signIn(signIn));
    }
    @PostMapping("/refresh")
    @Operation(method = "POST", summary = "Post endpoint for create a user")
    @ApiResponse(responseCode = "201", description = "Add a user")
    public ResponseEntity<JWTAuthResponse> refreshToken(@RequestBody RefreshTokenRequest refreshToken){
        return ResponseEntity.ok(authentificationService.refreshToken(refreshToken));
    }
}
