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

    @Override
    public void asignMateria(String idProfesor, Materia materia) {
        //Asignar una materia a un profesor sabiensdo que cuando el profesor se crea tiene su materia en null
        if (materia == null) {
            throw new IllegalArgumentException("La materia no puede ser nula.");
        }
        Profesor profesor = findProfesor(idProfesor);
        if (profesor != null) {
            profesor.asignarMateria(materia);
        } else {
            throw new IllegalArgumentException("Profesor con ID " + idProfesor + " no encontrado.");
        }
    }

    @Override
    public void verMaterias(String idProfesor) {
        Profesor profesor = findProfesor(idProfesor);
        //Ver las materia asignada a un profesor
        if (profesor != null) {
            Materia materia = profesor.getMateriaAsignada();
            if (materia != null) {
                System.out.println("Materia asignada a " + profesor.getNombre() + ": " + materia.getNombre());
            } else {
                System.out.println("El profesor " + profesor.getNombre() + " no tiene materia asignada.");
            }
        } else {
            throw new IllegalArgumentException("Profesor con ID " + idProfesor + " no encontrado.");
        }


    }
}
