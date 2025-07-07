package com.example.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Estudiante {
    private String id;
    private String nombre;
    private List<Calificacion> calificaciones;

    public Estudiante(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.calificaciones = new ArrayList<>();
    }

    public void agregarCalificacion(Calificacion c) {
        calificaciones.add(c);
    }
}
