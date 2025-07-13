package com.example.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Aula {
    private String id;
    private int capacidad;

    public Aula(String id, int capacidad) {
        this.id = id;
        this.capacidad = capacidad;
    }
}
