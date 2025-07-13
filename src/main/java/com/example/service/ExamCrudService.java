package com.example.service;

import com.example.model.Examen;
import com.example.model.Materia;
import com.example.repository.ExamRepository;

public class ExamCrudService implements ExamService {
    private ExamRepository examenRepository;

    public ExamCrudService(ExamRepository examenRepository) {
        this.examenRepository = examenRepository;
    }

    @Override
    public void crearExamen(Materia materia, Examen examen) throws Exception {
        examenRepository.agregarExamen(materia, examen);
    }

    @Override
    public void eliminarExamen(Materia materia, String descripcion) throws Exception {
        examenRepository.eliminarExamen(materia, descripcion);
    }

    @Override
    public Examen obtenerExamen(Materia materia, String descripcion) throws Exception {
        return examenRepository.buscarExamen(materia, descripcion);
    }

    @Override
    public Examen[] listarExamenes(Materia materia) {
        return examenRepository.listarExamenes(materia);
    }
}
