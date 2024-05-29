package com.pee.dockerized.postgresql.dto;

public record SignUpRequest(String email, String password, String username) {
}
