package com.example.model;


import lombok.Getter;
import lombok.Setter;
import com.example.model.Evaluacion;


import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter

        /*
public class Examen {
    private int id;
    private Materia materia;
    private LocalDate fecha;

    public Examen(int id,Materia materia, LocalDate fecha) {
        this.id = id;
        this.materia = materia;
        this.fecha = fecha;

         */

public class Examen extends Evaluacion{
    private String descripcion;

    public Examen(String id, String nombre, double notaMax,  Materia materia, LocalDateTime fecha, String descripcion) {

        super(id,nombre,notaMax,materia,fecha);
        this.descripcion = descripcion;
    }

    @Override
    public String getTipo() {
        return "Examen";

    }
}
