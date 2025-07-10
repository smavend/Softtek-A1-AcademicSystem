package com.example.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Examen {
    private int id;
    private Materia materia;
    private LocalDate fecha;

    public Examen(int id,Materia materia, LocalDate fecha) {
        this.id = id;
        this.materia = materia;
        this.fecha = fecha;
    }
}
