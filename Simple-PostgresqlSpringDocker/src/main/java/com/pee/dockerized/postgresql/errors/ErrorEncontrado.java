package com.pee.dockerized.postgresql.errors;

public class ErrorEncontrado extends RuntimeException {
    public ErrorEncontrado(String message) {
        super(message);
    }
}
