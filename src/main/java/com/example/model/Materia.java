package com.example.model;

import com.example.exception.CupoExcedidoException;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Materia {
    private String codigo;
    private String nombre;
    private Profesor profesor;
    private Aula aula;

    private final int MAX_ESTUDIANTES = 30;

    public Materia(String codigo, String nombre, Profesor profesor, Aula aula) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.profesor = profesor;
        this.aula = aula;
    }

}
