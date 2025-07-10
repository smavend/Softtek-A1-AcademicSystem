package com.example.repository;

import com.example.model.Calificacion;

import java.util.List;

public interface ICalificacionRepository {
    //Agregar, eliminar, listar

    //Acciones por alumno
    void addCalificacion(Calificacion calificacion);
    void removeCalificacion(int id);
    //Calificacion findCalificacion(Calificacion calificacion);
    List<Calificacion> showAllCalificacion();


}
