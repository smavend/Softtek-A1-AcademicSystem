package com.example.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Calificacion {
    private Estudiante estudiante;
    private Examen examen;
    private double nota;

    public Calificacion() {}

    public Calificacion(Estudiante estudiante, Examen examen, double nota) {
        this.estudiante = estudiante;
        this.examen = examen;
        this.nota = nota;
    }
}
