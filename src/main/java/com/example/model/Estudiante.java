package com.example.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Estudiante extends Persona{
    private List<Calificacion> calificaciones;

    public Estudiante(String id, String nombre) {
        super(id, nombre);
        this.calificaciones = new ArrayList<>();
    }

    public void agregarCalificacion(Calificacion c) {
        calificaciones.add(c);
    }

    @Override
    public String getTipo() {
        return "Estudiante";
    }
}
