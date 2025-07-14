package com.example.service;

import com.example.exception.EstudianteNoEncontrado;
import com.example.interfaces.IEstudianteRepo;
import com.example.model.Calificacion;
import com.example.model.Estudiante;
import com.example.model.Examen;
import com.example.model.Materia;
import com.example.repository.ArrayCalificacionRepository;
import com.example.repository.ICalificacionRepository;

import java.util.List;

public class EstudianteRepoMem implements IEstudianteRepo {

    private List<Estudiante> estudiantes;

    public EstudianteRepoMem() {
        this.estudiantes = new java.util.ArrayList<>();
    }

    @Override
    public void addEstudiante(Estudiante estudiante) {
        if (estudiante != null && !estudiantes.contains(estudiante)) {
            estudiantes.add(estudiante);
        }
        else {
            throw new IllegalArgumentException("El estudiante ya existe o es nulo.");
        }
    }
    @Override
    public void removeEstudiante(String id){
        ICalificacionRepository califRepo = new ArrayCalificacionRepository();
        Estudiante encontrar = findEstudiante(id);
        if (encontrar != null) {
            for (Materia materia : encontrar.getMateriasInscritas()) {
                materia.removerEstudiante(encontrar);
                califRepo.deleteCalificationByEstudiante(materia.getCodigo(), encontrar.getId());
            }
            estudiantes.remove(encontrar);
        }
        else {
            throw new IllegalArgumentException("Estudiante con ID " + id + " no encontrado.");
        }
    }

    @Override
    public Estudiante findEstudiante(String id) throws EstudianteNoEncontrado {
        for (Estudiante estudiante : estudiantes) {
            if (estudiante.getId().equals(id)) {
                return estudiante;
            }
        }
        throw new EstudianteNoEncontrado("Estudiante con ID " + id + " no encontrado.");
    }

    @Override
    public void asignMateria(String idEstudiante, Materia materia) {
        if (materia == null) {
            throw new IllegalArgumentException("La materia no puede ser nula.");
        }
        Estudiante estudiante = findEstudiante(idEstudiante);
        if (estudiante != null) {
            estudiante.inscribirMateria(materia);
        } else {
            throw new IllegalArgumentException("Estudiante con ID " + idEstudiante + " no encontrado.");
        }
    }

    @Override
    public void verMateriasInscritas(String idEstudiante) {
        Estudiante encontrar = findEstudiante(idEstudiante);
        if (encontrar != null) {
            List<Materia> materiasInscritas = encontrar.getMateriasInscritas();
            if (materiasInscritas.isEmpty()) {
                System.out.println("El estudiante con ID " + idEstudiante + " no tiene materias inscritas.");
            } else {
                System.out.println("Materias inscritas por el estudiante con ID " + idEstudiante + ":");
                for (Materia materia : materiasInscritas) {
                    System.out.println("- " + materia.getNombre());
                }
            }
        } else {
            throw new IllegalArgumentException("Estudiante con ID " + idEstudiante + " no encontrado.");
        }
    }

    @Override
    public List<Estudiante> verEstudiantes() {
        return estudiantes;
    }
}
