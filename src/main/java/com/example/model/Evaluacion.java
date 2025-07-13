package com.example.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public abstract class Evaluacion {
    protected String id;
    protected String nombre;
    protected double notaMax;
    protected Materia materia;
    protected LocalDateTime fecha;

    // Constructor por defecto para lombok
    public Evaluacion() {}

    public abstract String getTipo();
}
