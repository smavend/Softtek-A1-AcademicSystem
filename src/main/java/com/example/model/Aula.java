package com.example.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Aula {
    private String codigo;
    private int capacidad;

    public Aula(String codigo, int capacidad) {
        this.codigo = codigo;
        this.capacidad = capacidad;
    }
}
