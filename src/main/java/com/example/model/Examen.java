package com.example.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Examen {
    private Materia materia;
    private LocalDate fecha;

    public Examen(Materia materia, LocalDate fecha) {
        this.materia = materia;
        this.fecha = fecha;
    }
}
