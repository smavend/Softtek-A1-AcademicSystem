package com.example.interfaces;

import com.example.model.Materia;
import com.example.model.Profesor;

import java.util.List;

public interface IProfesorRepo {
    void addProfesor(Profesor profesor);
    void removeProfesor(String id, List<Materia> materias);
    Profesor findProfesor(String id);
    List<Profesor> findAllProfesores();
}
