package com.pee.dockerized.postgresql.dto;


public record JWTAuthResponse(String token, String refreshToken) {
}
