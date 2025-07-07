package com.example.service;

import com.example.exception.NotaInvalidaException;
import com.example.model.Calificacion;
import com.example.model.Estudiante;

public class RegistroCalificacionService {
    public void registrarCalificacion(Estudiante estudiante, Calificacion calificacion) throws NotaInvalidaException {
        if (calificacion.getNota() < 0 || calificacion.getNota() > 20) {
            throw new NotaInvalidaException("La nota debe estar entre 0 y 20.");
        }

        estudiante.agregarCalificacion(calificacion);
    }
}
