package com.example.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Director extends Persona {
    private String especialidad;

    public Director(String id, String nombre, String especialidad) {
        super(id, nombre);
        this.especialidad = especialidad;
    }
    @Override
    public String getTipo() {
        return "Director";
    }
}
