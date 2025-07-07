package com.example.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Profesor extends Persona{
    private String especialidad;

    public Profesor(String id, String nombre, String especialidad) {
        super(id, nombre);
        this.especialidad = especialidad;
    }

    @Override
    public String getTipo() {
        return "Profesor";
    }
}
