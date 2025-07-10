package com.example.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Calificacion {
    private Examen examen;
    private double nota;

    public Calificacion(Examen examen, double nota) {
        this.examen = examen;
        this.nota = nota;
    }

    @Override // <--- ¡Importante: esta anotación indica que sobrescribes un método!
    public String toString() {
        // Formatear la salida para que sea legible
        return "Calificación [Examen ID: " + examen.getId() +
                ", Materia: " + examen.getMateria().getNombre() +
                " - Fecha: " + examen.getFecha() +
                ", Nota: " + nota + "]";
    }
}
