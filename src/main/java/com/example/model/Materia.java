package com.example.model;

import com.example.exception.CapacidadExcedidaException;
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
    private Estudiante[] estudiantes;
    private Examen[] examenes = new Examen[10];
    private int cupoMaximo;

    public Materia(String codigo, String nombre, Profesor profesor, Aula aula) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.profesor = profesor;
        this.aula = aula;
        this.cupoMaximo = aula.getCapacidad();
        this.estudiantes = new Estudiante[cupoMaximo];
    }

    public Materia(String codigo, String nombre, Profesor profesor, Aula aula, int cupoMaximo) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.profesor = profesor;
        this.aula = aula;
        if (cupoMaximo > aula.getCapacidad()) {
            throw new CapacidadExcedidaException("Los cupos de la materia no pueden ser mayor que la capacidad del aula.");
        }
        this.cupoMaximo = aula.getCapacidad();
        this.estudiantes = new Estudiante[cupoMaximo];
    }
}
