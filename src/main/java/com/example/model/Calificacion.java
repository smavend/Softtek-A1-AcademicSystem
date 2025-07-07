package com.example.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Calificacion {
    private Materia materia;
    private double nota;

    public Calificacion(Materia materia, double nota) {
        this.materia = materia;
        this.nota = nota;
    }
}
