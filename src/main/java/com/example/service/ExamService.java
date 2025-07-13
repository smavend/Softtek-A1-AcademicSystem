package com.example.service;

import com.example.model.Examen;
import com.example.model.Materia;

public interface ExamService {
    void crearExamen(Materia materia, Examen examen) throws Exception;
    void eliminarExamen(Materia materia, String descripcion) throws Exception;
    Examen obtenerExamen(Materia materia, String descripcion) throws Exception;
    Examen[] listarExamenes(Materia materia);
}
