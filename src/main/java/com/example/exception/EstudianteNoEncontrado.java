package com.example.exception;

public class EstudianteNoEncontrado extends RuntimeException {
    public EstudianteNoEncontrado(String message) {
        super(message);
    }
}
