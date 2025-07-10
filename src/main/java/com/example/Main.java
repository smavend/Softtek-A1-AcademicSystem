package com.example;

import com.example.exception.NotaInvalidaException;
// import com.example.exception.CalificacionYaExistenteException; // Eliminado
import com.example.model.Aula;
import com.example.model.Calificacion;
import com.example.model.Estudiante;
import com.example.model.Examen;
import com.example.model.Materia;
import com.example.model.Profesor;
import com.example.repository.ArrayCalificacionRepository; // Usamos tu nombre de implementación
import com.example.repository.ICalificacionRepository;
import com.example.service.RegistroCalificacionService;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- ¡Bienvenido a Gestión Académica Simplificada! ---");

        // 1. Inicialización del Repositorio y Servicio
        ICalificacionRepository repoCalificacion = new ArrayCalificacionRepository();
        RegistroCalificacionService registroService = new RegistroCalificacionService(repoCalificacion);

        // 2. Datos de Prueba Fijos (para probar el sistema sin pedir mucha entrada)
        Profesor profMate = new Profesor("P101", "Julia Mendoza", "Matemáticas");
        Aula aula301 = new Aula("A301", 30);
        Materia mateAvanzada = new Materia("M201", "Matemáticas Avanzadas", profMate, aula301);
        Estudiante estPrincipal = new Estudiante("1","Luis Gonzales");

        // Exámenes de prueba con IDs predefinidos
        Examen examenParcial = new Examen(1, mateAvanzada, LocalDate.parse("2025-06-15"));
        Examen examenFinal = new Examen(2, mateAvanzada, LocalDate.parse("2025-07-20"));
        Examen examenRecuperacion = new Examen(3, mateAvanzada, LocalDate.parse("2025-08-05"));


        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n--- Menú de Calificaciones ---");
            System.out.println("1. Registrar una calificación (Ejemplos fijos)");
            System.out.println("2. Mostrar todas las calificaciones");
            System.out.println("3. Eliminar una calificación por ID de Examen");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());

                switch (opcion) {
                    case 1:
                        System.out.println("\n--- Registrando Calificaciones de Prueba ---");
                        // Intenta registrar una nota para el Examen 1
                        System.out.print("Ingrese la nota para el Examen Parcial (ID: " + examenParcial.getId() + ") de " + estPrincipal.getNombre() + ": ");
                        try {
                            double notaParcial = Double.parseDouble(scanner.nextLine());
                            registroService.registrarNota(estPrincipal, examenParcial, notaParcial);
                        } catch (NotaInvalidaException e) {
                            System.err.println("Error: " + e.getMessage());
                        } // No se atrapa CalificacionYaExistenteException aquí

                        // Intenta registrar una nota para el Examen 2
                        System.out.print("Ingrese la nota para el Examen Final (ID: " + examenFinal.getId() + ") de " + estPrincipal.getNombre() + ": ");
                        try {
                            double notaFinal = Double.parseDouble(scanner.nextLine());
                            registroService.registrarNota(estPrincipal, examenFinal, notaFinal);
                        } catch (NotaInvalidaException e) {
                            System.err.println("Error: " + e.getMessage());
                        }

                        // Intenta registrar una nota para el Examen 3
                        System.out.print("Ingrese la nota para el Examen Recuperación (ID: " + examenRecuperacion.getId() + ") de " + estPrincipal.getNombre() + ": ");
                        try {
                            double notaRecuperacion = Double.parseDouble(scanner.nextLine());
                            registroService.registrarNota(estPrincipal, examenRecuperacion, notaRecuperacion);
                        } catch (NotaInvalidaException e) {
                            System.err.println("Error: " + e.getMessage());
                        }
                        break;
                    case 2:
                        System.out.println("\n--- Listado de Calificaciones Registradas ---");
                        List<Calificacion> calificaciones = repoCalificacion.showAllCalificacion();
                        if (calificaciones.isEmpty()) {
                            System.out.println("No hay calificaciones registradas.");
                        } else {
                            for (Calificacion c : calificaciones) {
                                System.out.println(c);
                            }
                        }
                        break;
                    case 3:
                        System.out.println("\n--- Eliminar Calificación ---");
                        System.out.print("Ingrese el ID del Examen (ej. 1, 2, 3) asociado a la calificación a eliminar: ");
                        int idExamenAEliminar = Integer.parseInt(scanner.nextLine());
                        repoCalificacion.removeCalificacion(idExamenAEliminar);
                        // El mensaje de éxito/fracaso ahora lo gestiona tu implementación del repositorio
                        break;
                    case 0:
                        System.out.println("Saliendo de Gestión Académica. ¡Hasta pronto!");
                        break;
                    default:
                        System.out.println("Opción no válida. Por favor, intente de nuevo.");
                        break;
                }
            } catch (NumberFormatException e) {
                System.err.println("Entrada inválida. Por favor, ingrese un número.");
                opcion = -1;
            } catch (Exception e) {
                System.err.println("Ocurrió un error inesperado: " + e.getMessage());
                opcion = -1;
            }
        } while (opcion != 0);

        scanner.close();
    }
}