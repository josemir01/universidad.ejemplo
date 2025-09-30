/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import Modelo.Alumno;
import Persistencia.AlumnoData;
import Persistencia.Conexion;
import java.time.LocalDate;
import java.time.Month;
import java.util.Scanner;

public class javaMain {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Conexion conexion = new Conexion(
                "jdbc:mariadb://localhost:3306/universidadulp","root",""
        );
         Alumno a1 = new Alumno(34685984, LocalDate.of(2004, Month.MAY, 12), true, "Bonetto", "Luca");
        Alumno a12 = new Alumno(47893573, LocalDate.of(2006, 7, 11), true, "Diaz", "Agustin");
        AlumnoData ad1 = new AlumnoData(conexion);
        ad1.insertarAlumno(a1);
        ad1.insertarAlumno(a12);

        int opcion;
        do {
            System.out.println("Menu Alumnos");
            System.out.println("1 - Insertar alumno");
            System.out.println("2 - Mostrar todos los alumnos");
            System.out.println("3 - Buscar alumno por ID");
            System.out.println("4 - Actualizar alumno");
            System.out.println("5 - Baja logica de alumno");
            System.out.println("6 - Alta logica de alumno");
            System.out.println("7 - Borrar alumno");
            System.out.println("0 - Salir");
            System.out.print("Opcion: ");
            opcion = sc.nextInt();
            sc.nextLine(); 

            switch (opcion) {
                case 1:
                    System.out.println("DNI:");
                    int dni = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Apellido:");
                    String apellido = sc.nextLine();
                    System.out.println("Nombre:");
                    String nombre = sc.nextLine();
                    System.out.println("Fecha nacimiento (YYYY-MM-DD):");
                    String fechaStr = sc.nextLine();
                    LocalDate fecha = LocalDate.parse(fechaStr);

                    Alumno nuevo = new Alumno(dni, fecha, true, apellido, nombre);
                    ad1.insertarAlumno(nuevo);
                    break;

                case 2:
                    ad1.mostrarTodos();
                    break;

                case 3:
                    System.out.println("Ingrese ID de alumno:");
                    int idBuscar = sc.nextInt();
                    ad1.buscarAlumno(idBuscar);
                    break;

                case 4:
                    System.out.println("ID del alumno a actualizar:");
                    int idAct = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Nuevo apellido:");
                    String nuevoApellido = sc.nextLine();
                    System.out.println("Nuevo nombre:");
                    String nuevoNombre = sc.nextLine();
                    ad1.actualizarAlumno(idAct, nuevoApellido, nuevoNombre);
                    break;

                case 5:
                    System.out.println("ID del alumno a dar de baja:");
                    int idBaja = sc.nextInt();
                    ad1.bajaLogica(idBaja);
                    break;

                case 6:
                    System.out.println("ID del alumno a dar de alta:");
                    int idAlta = sc.nextInt();
                    ad1.altaLogica(idAlta);
                    break;

                case 7:
                    System.out.println("ID del alumno a borrar:");
                    int idBorrar = sc.nextInt();
                    ad1.borrarAlumno(idBorrar);
                    break;

                case 0:
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println("Opción inválida");
            }

        } while (opcion != 0);

        sc.close();
    }
}