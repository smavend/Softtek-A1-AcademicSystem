package com.example.repository;

import com.example.exception.ExamenNoEncontradoException;
import com.example.exception.MaximoExamenesException;
import com.example.model.Examen;
import com.example.model.Materia;

public class ArrayExamRepository implements ExamRepository {
    private final int MAX_EXAMENES = 10;

    @Override
    public void agregarExamen(Materia materia, Examen examen) throws MaximoExamenesException {
        Examen[] examenes = materia.getExamenes();
        for (int i = 0; i < MAX_EXAMENES; i++) {
            if (examenes[i] == null) {
                examenes[i] = examen;
                return;
            }
        }
        throw new MaximoExamenesException("Ya se alcanzó el máximo de 10 exámenes para esta materia.");
    }

    @Override
    public void eliminarExamen(Materia materia, String descripcion) throws ExamenNoEncontradoException {
        Examen[] examenes = materia.getExamenes();
        for (int i = 0; i < examenes.length; i++) {
            if (examenes[i] != null && examenes[i].getDescripcion().equalsIgnoreCase(descripcion)) {
                examenes[i] = null;
                return;
            }
        }
        throw new ExamenNoEncontradoException("Examen no encontrado con descripción: " + descripcion);
    }

    @Override
    public Examen buscarExamen(Materia materia, String descripcion) throws ExamenNoEncontradoException {
        for (Examen examen : materia.getExamenes()) {
            if (examen != null && examen.getDescripcion().equalsIgnoreCase(descripcion)) {
                return examen;
            }
        }
        throw new ExamenNoEncontradoException("Examen no encontrado con descripción: " + descripcion);
    }

    @Override
    public Examen[] listarExamenes(Materia materia) {
        return materia.getExamenes();
    }
}
