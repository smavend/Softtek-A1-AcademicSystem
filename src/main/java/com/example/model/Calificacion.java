package com.example.model;

import lombok.Data;

@Data
public class Calificacion {
    private Materia materia;
    private double nota;

    public Calificacion(Materia materia, double nota) {
        this.materia = materia;
        this.nota = nota;
    }
}
