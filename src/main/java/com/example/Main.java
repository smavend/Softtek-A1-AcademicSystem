package com.example;

import com.example.exception.CupoExcedidoException;
import com.example.exception.NotaInvalidaException;
import com.example.model.*;
import com.example.repository.ArrayCalificacionRepository;
import com.example.repository.ArrayExamRepository;
import com.example.repository.ExamRepository;
import com.example.repository.ICalificacionRepository;
import com.example.service.ExamCrudService;
import com.example.service.ExamService;
import com.example.service.RegistroCalificacionService;

import java.time.LocalDateTime;
import java.util.*;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws CupoExcedidoException, NotaInvalidaException {

        ICalificacionRepository repoCalificacion = new ArrayCalificacionRepository();
        RegistroCalificacionService registroService = new RegistroCalificacionService(repoCalificacion);

        // Datos de prueba: Profesores
        Profesor prof1 = new Profesor("P1", "Juan Pérez", "Matemática");
        Profesor prof2 = new Profesor("P2", "Laura García", "Física");

        // Datos de prueba: Estudiantes
        Estudiante est1 = new Estudiante("E1", "Ana López");
        Estudiante est2 = new Estudiante("E2", "Carlos Ruiz");

        // Datos de prueba: Aulas y materias
        Aula aula1 = new Aula("A101", 30);
        Aula aula2 = new Aula("B202", 25);

        Materia mat1 = new Materia("MAT101", "Álgebra", prof1, aula1);
        Materia mat2 = new Materia("FIS101", "Mecánica", prof2, aula2);
        Materia mat3 = new Materia("MAT102", "Geometría", prof1, aula1,25);

        // Agregar estudiantes a materias
        mat1.agregarEstudiante(est1);
        mat1.agregarEstudiante(est2);
        mat2.agregarEstudiante(est2);
        mat3.agregarEstudiante(est1);

        // Repositorio de exámenes y servicio
        ExamRepository examenRepo = new ArrayExamRepository();
        ExamService examenService = new ExamCrudService(examenRepo);

        // Exámenes creados
        Examen ex1 = new Examen("EX1", "Examen Parcial Álgebra", 20.0, mat1, LocalDateTime.of(2025, 5, 1, 10, 0), "Parcial");
        Examen ex2 = new Examen("EX2", "Examen Final Álgebra", 20.0, mat1, LocalDateTime.of(2025, 6, 15, 10, 0), "Final");
        Examen ex3 = new Examen("EX3", "Diagnóstico Geometría", 20.0, mat3, LocalDateTime.of(2025, 4, 10, 8, 0), "Diagnóstico");

        mat1.getExamenes()[0] = ex1;
        ex1.setMateria(mat1);
        mat1.getExamenes()[1] = ex2;
        ex2.setMateria(mat1);
        mat3.getExamenes()[0] = ex3;
        ex3.setMateria(mat3);

        System.out.println("\n--- Verificando IDs antes de crear el mapa de login ---");
        System.out.println("ID de est1: " + ex1.getId()+ex1.getDescripcion());
        System.out.println("ID de est2: " + ex2.getId());
        System.out.println("---------------------------------------------------\n");

        // Calificaciones simuladas

        repoCalificacion.addCalificacion(new Calificacion(est1, ex1, 16.0));
        repoCalificacion.addCalificacion(new Calificacion(est1, ex2, 18.5));
        repoCalificacion.addCalificacion(new Calificacion(est2, ex1, 12.0));
        repoCalificacion.addCalificacion(new Calificacion(est2, ex3, 14.0));

        // Datos para navegación
        List<Profesor> profesores = List.of(prof1, prof2);
        List<Estudiante> estudiantes = List.of(est1, est2);
        List<Materia> materias = List.of(mat1, mat2, mat3);
        List<Examen> examenes = Arrays.asList(ex1, ex2, ex3);

        Map<String, Profesor> mapaProfesores = new HashMap<>();
        for (Profesor p : profesores) mapaProfesores.put(p.getId(), p);

        Map<String, Estudiante> mapaEstudiantes = new HashMap<>();
        for (Estudiante e : estudiantes) mapaEstudiantes.put(e.getId(), e);

        // Menú principal
        while (true) {
            System.out.println("\n===== SISTEMA ACADÉMICO =====");
            String id = leerCadena("Ingrese su ID (o escriba 'salir'):");
            if (id.equalsIgnoreCase("salir")) break;

            if (mapaProfesores.containsKey(id)) {
                profesorMenu(mapaProfesores.get(id), materias, examenService, repoCalificacion, registroService);
            } else if (mapaEstudiantes.containsKey(id)) {
                estudianteMenu(mapaEstudiantes.get(id), materias, examenes, examenService, repoCalificacion);
            } else {
                System.out.println("⚠️ ID no reconocido.");
            }
        }

        System.out.println("Sesión finalizada.");
    }

    // Menú para profesores
    private static void profesorMenu(Profesor profesor, List<Materia> materias, ExamService examenService, ICalificacionRepository calRepo, RegistroCalificacionService calService) throws NotaInvalidaException {
        while (true) {
            System.out.println("\n--- Menú Profesor (" + profesor.getNombre() + ") ---");
            List<Materia> propias = materias.stream().filter(m -> m.getProfesor().equals(profesor)).toList();

            for (int i = 0; i < propias.size(); i++) {
                System.out.println((i + 1) + ". " + propias.get(i).getNombre());
            }
            System.out.println("0. Cerrar sesión");

            int opcion = leerEntero("Seleccione una materia:");
            if (opcion == 0) break;
            if (opcion < 1 || opcion > propias.size()) {
                System.out.println("Opción inválida.");
                continue;
            }

            Materia seleccionada = propias.get(opcion - 1);
            profesorMateriaMenu(seleccionada, examenService, calRepo, calService);
        }
    }

    // Submenú del profesor
    private static void profesorMateriaMenu(Materia materia, ExamService examenService, ICalificacionRepository calRepo, RegistroCalificacionService calService) throws NotaInvalidaException {
        int opcion;
        do {
            System.out.println("\n--- Gestión de " + materia.getNombre() + " ---");
            System.out.println("1. Crear examen");
            System.out.println("2. Eliminar examen");
            System.out.println("3. Buscar examen");
            System.out.println("4. Listar exámenes");
            System.out.println("5. Registrar una calificación (Ejemplos fijos)");
            System.out.println("6. Mostrar todas las calificaciones");
            System.out.println("7. Eliminar una calificación por ID de Examen");
            //System.out.println("5. Gestionar calificaciones"); // TODO
            //System.out.println("6. Gestionar estudiantes"); // TODO
            System.out.println("0. Volver");

            opcion = leerEntero("Opción:");
            switch (opcion) {
                case 1 -> {
                    String id = leerCadena("ID del examen:");
                    String nombre = leerCadena("Nombre del examen:");
                    double notaMax = Double.parseDouble(leerCadena("Nota máxima:"));
                    LocalDateTime fecha = LocalDateTime.now(); // o usar un parser de fecha
                    // se añade una fecha personalizada
                    String fechaStr = leerCadena("Fecha del examen (YYYY-MM-DD HH:MM):");
                    try {
                        fecha = LocalDateTime.parse(fechaStr.replace(" ", "T"));
                    } catch (Exception e) {
                        System.out.println("Error al parsear la fecha. Usando fecha actual.");
                    }
                    String descripcion = leerCadena("Descripción:");
                    Examen examen = new Examen(id, nombre, notaMax, materia, fecha, descripcion);
                    try {
                        examenService.crearExamen(materia, examen);
                        System.out.println("✅ Examen creado.");
                    } catch (Exception e) {
                        System.out.println("❌ Error: " + e.getMessage());
                    }
                }
                case 2 -> {
                    String descripcion = leerCadena("Descripción del examen a eliminar:");
                    try {
                        examenService.eliminarExamen(materia, descripcion);
                        System.out.println("✅ Examen eliminado.");
                    } catch (Exception e) {
                        System.out.println("❌ Error: " + e.getMessage());
                    }
                }
                case 3 -> {
                    String descripcion = leerCadena("Descripción del examen:");
                    try {
                        Examen ex = examenService.obtenerExamen(materia, descripcion);
                        System.out.println("✅ Examen encontrado: " + ex.getDescripcion() + " - " + ex.getFecha());
                    } catch (Exception e) {
                        System.out.println("❌ Error: " + e.getMessage());
                    }
                }
                case 4 -> {
                    System.out.println("--- Exámenes registrados ---");
                    for (Examen ex : examenService.listarExamenes(materia)) {
                        if (ex != null) {
                            System.out.println("• " + ex.getDescripcion() + " (" + ex.getFecha() + ")");
                        }
                    }
                }
                case 5 -> {
                    System.out.println("\n--- Registrando Calificaciones de Prueba ---");
                    // Intenta registrar una nota para el Examen 1
                    System.out.println("Seleccione una de las evaluaciones disponibles:");

                    for (int i = 0; i < examenService.listarExamenes(materia).length; i++) {
                        if (examenService.listarExamenes(materia)[i] == null) continue;
                        System.out.println((i + 1) + ". " + examenService.listarExamenes(materia)[i].getDescripcion() + " (ID: " + examenService.listarExamenes(materia)[i].getId() + ")");
                    }
                    System.out.println("0. Cerrar sesión");

                    int califIndex = leerEntero("Seleccione una evaluación:");
                    if (califIndex == 0) break;
                    if (califIndex < 1 || califIndex > examenService.listarExamenes(materia).length) {
                        System.out.println("❌ Opción inválida.");
                        continue;
                    }

                    for (int i = 0; i < materia.getEstudiantes().length; i++) {
                        if (materia.getEstudiantes()[i] == null) continue;
                        System.out.println((i + 1) + ". " + materia.getEstudiantes()[i].getNombre());
                    }
                    System.out.println("0. Cerrar sesión");

                    int estIndex = leerEntero("Seleccione un estudiante:");
                    if (estIndex == 0) break;
                    if (estIndex < 1 || estIndex > materia.getEstudiantes().length) {
                        System.out.println("❌ Opción inválida.");
                        continue;
                    }

                    double nota = leerDouble("Ingrese la nota correspondiente (0-"+ examenService.listarExamenes(materia)[califIndex - 1].getNotaMax() + "): ");

                    calService.registrarNota(materia.getEstudiantes()[estIndex - 1], examenService.listarExamenes(materia)[califIndex - 1], nota);
                    // No se atrapa CalificacionYaExistenteException aquí
                    break;
                }
                case 6 -> {
                    System.out.println("\n--- Listado de Calificaciones Registradas ---");
                    List<Calificacion> calificaciones = calRepo.getCalificacionByMateria(materia.getCodigo());
                    if (calificaciones.isEmpty()) {
                        System.out.println("No hay calificaciones registradas.");
                    } else {
                        for (int i = 0; i < calificaciones.size(); i++) {
                            System.out.println((i + 1) + ". " + calificaciones.get(i).getExamen().getDescripcion() +
                                    " - Estudiante: " + calificaciones.get(i).getEstudiante().getNombre() +
                                    " - Nota: " + calificaciones.get(i).getNota());
                        }
                    }
                    break;
                }
                case 7 -> {
                    System.out.println("\n--- Eliminar Calificación ---");

                    List<Calificacion> calificaciones = calRepo.getCalificacionByMateria(materia.getCodigo());
                    if (calificaciones.isEmpty()) {
                        System.out.println("No hay calificaciones registradas.");
                    } else {
                        for (int i = 0; i < calificaciones.size(); i++) {
                            System.out.println((i + 1) + ". " + calificaciones.get(i).getExamen().getDescripcion() +
                                    " - Estudiante: " + calificaciones.get(i).getEstudiante().getNombre() +
                                    " - Nota: " + calificaciones.get(i).getNota());
                        }
                    }
                    int idExamenAEliminar = leerEntero("Ingrese el ID del examen de la calificación a eliminar:");
                    calRepo.removeCalificacion(calificaciones.get(idExamenAEliminar - 1).getExamen().getId());
                    break;

                }

                case 0 -> System.out.println("Volviendo al menú anterior...");
                default -> System.out.println("⚠️ Opción inválida.");
            }
        } while (opcion != 0);
    }

    // Menú para estudiantes
    private static void estudianteMenu(Estudiante estudiante, List<Materia> materias, List<Examen> examenes, ExamService examenService, ICalificacionRepository calificaciones) {
        List<Materia> inscritas = materias.stream()
                .filter(m -> {
                    return Arrays.asList(m.getEstudiantes()).contains(estudiante);
                })
                .toList();

        while (true) {
            System.out.println("\n--- Menú Estudiante (" + estudiante.getNombre() + ") ---");
            for (int i = 0; i < inscritas.size(); i++) {
                System.out.println((i + 1) + ". " + inscritas.get(i).getNombre());
            }
            System.out.println("0. Cerrar sesión");

            int opcion = leerEntero("Seleccione una materia:");
            if (opcion == 0) break;
            if (opcion < 1 || opcion > inscritas.size()) {
                System.out.println("❌ Opción inválida.");
                continue;
            }

            Materia seleccionada = inscritas.get(opcion - 1);
            estudianteMateriaMenu(seleccionada, estudiante, examenService, calificaciones);
        }
    }

    // Submenú del estudiante
    private static void estudianteMateriaMenu(Materia materia, Estudiante estudiante, ExamService examenService, ICalificacionRepository calificaciones) {
        System.out.println("\n--- Exámenes de " + materia.getNombre() + " ---");
        Examen[] lista = examenService.listarExamenes(materia);
        boolean hayExamenes = false;

        for (Examen ex : lista) {
            if (ex != null) {
                hayExamenes = true;
                System.out.print("• " + ex.getId() + ": " + ex.getDescripcion() + " - Fecha: " + ex.getFecha());
                Optional<Calificacion> cal = calificaciones.getCalificacionByMateria(materia.getCodigo()).stream()
                        .filter(c -> c.getEstudiante().equals(estudiante) && c.getExamen().equals(ex))
                        .findFirst();
                if (cal.isPresent()) {
                    System.out.println(" → Nota: " + cal.get().getNota());
                } else {
                    System.out.println(" → Sin calificación");
                }
            }
        }

        if (!hayExamenes) {
            System.out.println("No hay exámenes registrados.");
        }

        System.out.println("[Presione Enter para continuar]");
        scanner.nextLine();
    }

    // Métodos de lectura
    private static int leerEntero(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje + " ");
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Ingrese un número válido.");
            }
        }
    }

    private static double leerDouble(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje + " ");
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Ingrese un número válido.");
            }
        }
    }

    private static String leerCadena(String mensaje) {
        System.out.print(mensaje + " ");
        return scanner.nextLine().trim();
    }
}