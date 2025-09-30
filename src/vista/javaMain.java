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

public class javaMain {
    public static void main(String []args){
        Conexion cn = new Conexion("jdbc:mariadb://localhost:3306/universidadulp", "root", "");
        Alumno a1 = new Alumno(34685984, LocalDate.of(2004, Month.MAY, 12), true, "Bonetto", "Luca");
        AlumnoData ad1 = new AlumnoData(cn);
        ad1.insertarAlumno(a1);
        
        
    }
}
