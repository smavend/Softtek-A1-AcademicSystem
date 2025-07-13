package com.example.interfaces;

import com.example.model.Estudiante;
import com.example.model.Materia;

public interface IEstudianteRepo {
    void addEstudiante(Estudiante estudiante);
    void removeEstudiante(String id);    
    Estudiante findEstudiante(String id);
    void asignMateria(String estudianteId, Materia materiaId);
    void verMateriasInscritas(String estudianteId);

}
