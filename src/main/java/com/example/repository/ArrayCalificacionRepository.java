package com.example.repository;

import com.example.model.Calificacion;

import java.util.ArrayList;
import java.util.List;

public class ArrayCalificacionRepository implements ICalificacionRepository{
    //Creamos un array para las calificaciones
    private Calificacion[] calificaciones;
    private int count;  //contador para el arreglo
    private static final int CAPACITY_ARRAY = 10; //Capacidad del Array

    //Se crea un constructor vacia para inicializar los valores sin necesidad de pedir parametros
    public ArrayCalificacionRepository() {
        this.calificaciones = new Calificacion[CAPACITY_ARRAY]; //Inicializamos el array con un valor de 10
        this.count = 0; //Inicializamos el contador en 0
    }

    @Override
    public void addCalificacion(Calificacion calificacion) {
        //verificar si el array esta lleno
        if(count >= calificaciones.length){
            System.out.println("El array esta lleno");
        }
        calificaciones[count] = calificacion;   //añadir calificacion
        count++;    //aumentar contador
    }

    @Override
    public void removeCalificacion(int id) {
        int index = -1;

        //buscar la calificacion por el id
        for (int i = 0; i<count;i++){
            if(calificaciones[i].getExamen().getId() == id){
                index = i;
                break;
            }
        }

        //muestra mensaje en caso de no encontrar el examen
        if(index == -1){
            System.out.println("no se encontro, !!!!!!");
        }
        //Mover los elementos para lñlenar el espacio vacio
        for (int i = index; i<count-1; i++){
            calificaciones[i] = calificaciones[i+1];
        }
        calificaciones[count-1] = null; //limpia la ultima posicion
        count--;    //Decrementa el contador

    }

    @Override
    public List<Calificacion> showAllCalificacion() {
        //Retornar una copia de la lista de las calificaciones existentes
        List<Calificacion> allCalificacion = new ArrayList<>();
        for(int i = 0;i<count;i++){
            allCalificacion.add(calificaciones[i]);
        }
        return allCalificacion; //retorna la lista
    }
}
