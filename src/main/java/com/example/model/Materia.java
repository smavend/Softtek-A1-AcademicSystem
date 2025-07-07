package com.example.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Materia {
    private String codigo;
    private String nombre;
    private Profesor profesor;
    private List<Estudiante> estudiantes;

    public Materia(String codigo, String nombre, Profesor profesor) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.profesor = profesor;
        this.estudiantes = new ArrayList<>();
    }

    public void inscribirEstudiante(Estudiante e) {
        estudiantes.add(e);
    }
}
