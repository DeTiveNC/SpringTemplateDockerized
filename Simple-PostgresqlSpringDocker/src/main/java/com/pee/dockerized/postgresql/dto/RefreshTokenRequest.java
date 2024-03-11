package com.pee.dockerized.postgresql.dto;

import lombok.Data;

@Data
public class RefreshTokenRequest {
    private String token;
}
