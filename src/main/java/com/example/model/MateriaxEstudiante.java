package com.example.model;

import com.example.exception.CupoExcedidoException;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MateriaxEstudiante {
    private Estudiante estudiante;
    private Materia materia;

    private final int MAX_ESTUDIANTES = 30;

    public MateriaxEstudiante(Estudiante estudiante, Materia materia) {
        this.estudiante = estudiante;
        this.materia = materia;
    }

    // TODO: agregar estudiantes en service
}