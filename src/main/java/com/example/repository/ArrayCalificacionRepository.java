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
    public void removeCalificacion(String id) {
        int index = -1;

        //buscar la calificacion por el id
        for (int i = 0; i<count;i++){
            if(calificaciones[i].getExamen().getId().equals(String.valueOf(id))){
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

    @Override
    public List<Calificacion> getCalificacionByMateria(String idMateria) {
        List<Calificacion> calificacionesPorMateria = new ArrayList<>();

        //Recorrer el array de calificaciones
        for (int i = 0; i < count; i++) {
            //Verificar si la materia de la calificación coincide con el id proporcionado
            if (calificaciones[i].getExamen().getMateria().getCodigo().equals(idMateria)) {
                calificacionesPorMateria.add(calificaciones[i]);
            }
        }

        return calificacionesPorMateria; //retorna la lista filtrada
    }

    @Override
    public boolean deleteCalificationByEstudiante(String estudianteId, String materiaId) {
        boolean found = false;

        //Recorrer el array de calificaciones
        for (int i = 0; i < count; i++) {
            //Verificar si la calificación pertenece al estudiante y materia especificados
            if (calificaciones[i].getEstudiante().getId().equals(estudianteId) &&
                calificaciones[i].getExamen().getMateria().getCodigo().equals(materiaId)) {
                removeCalificacion(calificaciones[i].getExamen().getId());
                found = true;
                break; // Salir del bucle una vez que se ha eliminado la calificación
            }
        }

        return found; //retorna true si se encontró y eliminó la calificación, false en caso contrario
    }

}
