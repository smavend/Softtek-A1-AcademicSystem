package com.example.model;

import lombok.Data;

@Data
public class Profesor {
    private String id;
    private String nombre;

    public Profesor(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
}
