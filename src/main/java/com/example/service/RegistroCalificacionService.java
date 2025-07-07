package com.example.service;

import com.example.exception.NotaInvalidaException;
import com.example.model.Calificacion;
import com.example.model.Estudiante;
import com.example.model.Materia;

public class RegistroCalificacionService {
    public void registrarNota(Estudiante est, Materia mat, double nota) throws NotaInvalidaException {
        if (nota < 0 || nota > 20) {
            throw new NotaInvalidaException("Nota fuera de rango permitido (0-20).");
        }

        Calificacion c = new Calificacion(mat, nota);
        est.agregarCalificacion(c);
    }
    // TODO
    /*public Estudiante buscarEstudiantePorNombre(Materia m, String nombre){
    }*/
}
