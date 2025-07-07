package com.example.model;

import lombok.Data;

@Data
public class Aula {
    private String codigo;
    private int capacidad;

    public Aula(String codigo, int capacidad) {
        this.codigo = codigo;
        this.capacidad = capacidad;
    }
}
