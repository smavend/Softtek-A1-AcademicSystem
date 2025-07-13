package com.example.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class Examen extends Evaluacion{
    private String descripcion;

    public Examen(String id, String nombre, double notaMax,  Materia materia, LocalDateTime fecha, String descripcion) {
        super();
        this.descripcion = descripcion;
    }

    @Override
    public String getTipo() {
        return "Examen";
    }
}
