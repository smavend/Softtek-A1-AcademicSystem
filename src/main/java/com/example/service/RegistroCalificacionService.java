package com.example.service;

import com.example.exception.NotaInvalidaException;
import com.example.model.Calificacion;
import com.example.model.Estudiante;
import com.example.model.Examen;
import com.example.model.Materia;
import com.example.repository.ICalificacionRepository;

import java.util.ArrayList;
import java.util.List;

public class RegistroCalificacionService {

    //Esta clase me va a permitir añadir mi calificacion y nada mas xd
    private ICalificacionRepository repoCalificacion;

    //Constructor
    public RegistroCalificacionService(ICalificacionRepository repoCalificacion) {
        this.repoCalificacion = repoCalificacion;
    }

    //Registrar calificacion mucho mejor now
    public void registrarNota(Estudiante estudiante, Examen examen, double nota) throws NotaInvalidaException {

        if (nota < 0 || nota > 20) {
            throw new NotaInvalidaException("Nota fuera de rango permitido (0-20). Rango válido: 0-20.");
        }
        Calificacion nuevaCalificacion = new Calificacion(estudiante,examen, nota);

        repoCalificacion.addCalificacion(nuevaCalificacion);

        System.out.println("Nota " + nota + " registrada exitosamente para el examen " + examen.getMateria().getNombre() +
                " (ID Examen: " + examen.getId() + ") del estudiante " + estudiante.getNombre() + ".");
    }


}
