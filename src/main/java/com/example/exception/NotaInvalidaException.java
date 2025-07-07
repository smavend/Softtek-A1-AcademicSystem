package com.example.exception;

public class NotaInvalidaException extends Exception{
    public NotaInvalidaException(String mensaje) {
        super(mensaje);
    }
}
