package com.pee.dockerized.postgresql.dto;

import lombok.Data;

@Data
public class JWTAuthResponse {
    private String token;
    private String refreshToken;
}
