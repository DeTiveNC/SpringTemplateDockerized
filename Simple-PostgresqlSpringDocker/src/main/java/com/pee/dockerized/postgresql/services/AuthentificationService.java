package com.pee.dockerized.postgresql.services;

import com.pee.dockerized.postgresql.dto.JWTAuthResponse;
import com.pee.dockerized.postgresql.dto.RefreshTokenRequest;
import com.pee.dockerized.postgresql.dto.SignInRequest;
import com.pee.dockerized.postgresql.dto.SignUpRequest;
import com.pee.dockerized.postgresql.entity.User;

public interface AuthentificationService {
    User signUp(SignUpRequest newUser);

    JWTAuthResponse signIn(SignInRequest userSingIn);

    JWTAuthResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
