package com.example.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Persona {
    protected String id;
    protected String nombre;

    public Persona(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    // Constructor por defecto para lombok
    public Persona() {}

    public abstract String getTipo();
}