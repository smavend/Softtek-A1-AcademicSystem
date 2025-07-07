package com.example.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Examen {
    private Materia materia;
    private LocalDate fecha;

    public Examen(Materia materia, LocalDate fecha) {
        this.materia = materia;
        this.fecha = fecha;
    }
}
