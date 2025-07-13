package com.example.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Profesor extends Persona{
    private Materia materiasAsignada;

    public Profesor(String id, String nombre) {
        super(id, nombre);
    }

    public void asignarMateria(Materia materia) {
        this.materiasAsignada = materia;
    }
    public Materia getMateriaAsignada() {
        return materiasAsignada;
    }

    @Override
    public String getTipo() {
        return "Profesor";
    }
}
