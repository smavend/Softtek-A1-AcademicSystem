package com.example.repository;

import com.example.model.Calificacion;

import java.util.List;

public interface ICalificacionRepository {
    //Agregar, eliminar, listar

    //Acciones por alumno
    void addCalificacion(Calificacion calificacion);
    void removeCalificacion(String id);
    //Calificacion findCalificacion(Calificacion calificacion);
    List<Calificacion> showAllCalificacion();
    List<Calificacion> getCalificacionByMateria(String materiaId);
    boolean deleteCalificationByEstudiante(String estudianteId, String materiaId);
}
