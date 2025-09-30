/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import Modelo.Alumno;
import java.sql.*;

/**
 *
 * @author Admin
 */
public class AlumnoData {
    private Connection con=(null);
    public AlumnoData(Conexion con) {
      
      this.con=con.BuscarConexion();
       
    }
public void insertarAlumno(Alumno e){
  String query="INSERT INTO alumno(dni,apellido,nombre,fecha_nacimiento,estado) VALUES(?,?,?,?,?) ";
  try {
    PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
    ps.setInt(1, e.getDni());
    ps.setString(2, e.getApellido());
    ps.setString(3, e.getNombre());
    ps.setDate(4, Date.valueOf(e.getFecha()));
    ps.setBoolean(5, e.isEstado());
    ps.executeUpdate(); // 3

    ResultSet rs = ps.getGeneratedKeys();  // recupero y asigno
    if (rs.next()) {
        e.setId_alumno(rs.getInt(1));
    } else {
        System.out.println("No se pudo tener ID");
    }

    ps.close();
    System.out.println("Guardado!");
} catch (SQLException ex) {
    ex.printStackTrace();
}
}    
    
    
}
