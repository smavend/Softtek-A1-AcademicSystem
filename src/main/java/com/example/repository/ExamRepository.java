package com.example.repository;

import com.example.model.Examen;
import com.example.model.Materia;

public interface ExamRepository {
    void agregarExamen(Materia materia, Examen examen) throws Exception;
    void eliminarExamen(Materia materia, String descripcion) throws Exception;
    Examen buscarExamen(Materia materia, String descripcion) throws Exception;
    Examen[] listarExamenes(Materia materia);
}
