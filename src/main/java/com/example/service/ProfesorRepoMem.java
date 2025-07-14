package com.example.service;

import com.example.interfaces.IProfesorRepo;
import com.example.model.Materia;
import com.example.model.Profesor;

import java.util.List;

public class ProfesorRepoMem implements IProfesorRepo {

    private List<Profesor> profesores;

    public ProfesorRepoMem() {
        this.profesores = new java.util.ArrayList<>();
    }

    @Override
    public void addProfesor(Profesor profesor) {
        if(profesor != null && !profesores.contains(profesor)) {
            profesores.add(profesor);
        } else {
            throw new IllegalArgumentException("El profesor ya existe o es nulo.");
        }
    }

    @Override
    public void removeProfesor(String id) {
        Profesor encontrar = findProfesor(id);
        if (encontrar != null) {
            profesores.remove(encontrar);
        } else {
            throw new IllegalArgumentException("Profesor con ID " + id + " no encontrado.");
        }
    }

    @Override
    public Profesor findProfesor(String id) {
        for (Profesor profesor : profesores) {
            if (profesor.getId().equals(id)) {
                return profesor;
            }
        }
        throw new IllegalArgumentException("Profesor con ID " + id + " no encontrado.");
    }

}
