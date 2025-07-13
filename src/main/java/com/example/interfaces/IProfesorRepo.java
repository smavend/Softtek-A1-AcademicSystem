package com.example.interfaces;

import com.example.model.Materia;
import com.example.model.Profesor;

public interface IProfesorRepo {

    void addProfesor(Profesor profesor);
    void removeProfesor(String id);
    Profesor findProfesor(String id);
    void asignMateria(String idProfesor, Materia materia);
    void verMaterias(String idProfesor);
}
