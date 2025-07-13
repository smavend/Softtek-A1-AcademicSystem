package com.example.exception;

public class ExamenNoEncontradoException extends RuntimeException {
    public ExamenNoEncontradoException(String message) {
        super(message);
    }
}
