package com.example.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Estudiante extends Persona{
    private List<Materia> materias;

    public Estudiante() {
        super();
        this.materias = new ArrayList<>();
    }

    public Estudiante(String id, String nombre) {
        super(id, nombre);
        this.materias = new ArrayList<>();
    }

    @Override
    public String getTipo() {
        return "Estudiante";
    }

    public void inscribirMateria(Materia materia) {
        materias.add(materia);
    }

    public List<Materia> getMateriasInscritas() {
        return materias;
    }
}
