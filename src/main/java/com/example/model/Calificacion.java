package com.example.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Calificacion {
    private Examen examen;
    private double nota;

    public Calificacion(Examen examen, double nota) {
        this.examen = examen;
        this.nota = nota;
    }
}
