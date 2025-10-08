package Persistencia;

import Modelo.Alumno;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class AlumnoData {
    private Connection con = null;

    public AlumnoData(Conexion con) {
        this.con = con.BuscarConexion();
    }

    public void insertarAlumno(Alumno e) {
        String query = "INSERT INTO alumno(dni,apellido,nombre,fecha_nacimiento,estado) VALUES(?,?,?,?,?) ";
        try {
            PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, e.getDni());
            ps.setString(2, e.getApellido());
            ps.setString(3, e.getNombre());
            ps.setDate(4, Date.valueOf(e.getFecha()));
            ps.setBoolean(5, e.isEstado());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                e.setId_alumno(rs.getInt(1));
            } else {
                System.out.println("No se pudo tener el ID ");
            }

            ps.close();
            System.out.println("Alumno guardado correctamente");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void mostrarTodos() {
        String sql = "SELECT * FROM alumno";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Alumno a = new Alumno(
                        rs.getInt("dni"),
                        rs.getDate("fecha_nacimiento").toLocalDate(),
                        rs.getBoolean("estado"),
                        rs.getString("apellido"),
                        rs.getString("nombre")
                );
                a.setId_alumno(rs.getInt("id_alumno"));
                System.out.println("ID: " + a.getId_alumno() +
                        " | DNI: " + a.getDni() +
                        " | " + a.getApellido() + ", " + a.getNombre() +
                        " | Fecha: " + a.getFecha() +
                        " | Estado: " + (a.isEstado() ? "Activo" : "Inactivo"));
            }
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public Alumno buscarAlumno(int id) {
        Alumno a = null;
        String sql = "SELECT * FROM alumno WHERE id_alumno=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                a = new Alumno(
                        rs.getInt("dni"),
                        rs.getDate("fecha_nacimiento").toLocalDate(),
                        rs.getBoolean("estado"),
                        rs.getString("apellido"),
                        rs.getString("nombre")
                );
                a.setId_alumno(rs.getInt("id_alumno"));
                System.out.println("Encontrado: " + a.getNombre() + " " + a.getApellido());
            } else {
                System.out.println("No se encontró el alumno con ID " + id);
            }
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return a;
    }

    public void actualizarAlumno(int id, String nuevoApellido, String nuevoNombre) {
        String sql = "UPDATE alumno SET apellido=?, nombre=? WHERE id_alumno=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nuevoApellido);
            ps.setString(2, nuevoNombre);
            ps.setInt(3, id);
            int filas = ps.executeUpdate();
            if (filas > 0) {
                System.out.println("Alumno actualizado correctamente");
            } else {
                System.out.println("No se encuentra el alumno con ID " + id);
            }
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void bajaLogica(int id) {
        String sql = "UPDATE alumno SET estado=false WHERE id_alumno=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            int filas = ps.executeUpdate();
            if (filas > 0) {
                System.out.println("Alumno dado de baja");
            } else {
                System.out.println("No se encuentra el alumno con ID " + id);
            }
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void altaLogica(int id) {
        String sql = "UPDATE alumno SET estado=true WHERE id_alumno=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            int filas = ps.executeUpdate();
            if (filas > 0) {
                System.out.println("Alumno dado de alta");
            } else {
                System.out.println("No se encuentra el alumno con ID " + id);
            }
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void borrarAlumno(int id) {
        String sql = "DELETE FROM alumno WHERE id_alumno=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            int filas = ps.executeUpdate();
            if (filas > 0) {
                System.out.println("Alumno borrado de la DB");
            } else {
                System.out.println("No se encuentra el alumno con ID " + id);
            }
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public TreeSet<Alumno> obtenerAlumnos(){
      TreeSet<Alumno> listaDeAlumnos=new TreeSet<>();
      String sql="Select * FROM alumno";
      try{
       PreparedStatement ps = con.prepareStatement(sql);
       ResultSet rs = ps.executeQuery();
       while(rs.next()){
           int id_alumno=rs.getInt("id_alumno");
           int dni=rs.getInt("dni");
           String apellido=rs.getString("apellido");
           String nombre =rs.getString("nombre");
           Date fecha =rs.getDate("fecha_nacimiento");
           boolean estado =rs.getBoolean("estado");
           
           Alumno alumno = new Alumno(dni, fecha.toLocalDate(), estado, apellido, nombre);
           alumno.setId_alumno(id_alumno);
           listaDeAlumnos.add(alumno);
       }
        
         
         
         ps.close();
      }catch(SQLException ex){
        ex.printStackTrace();
      }
        return listaDeAlumnos;
      
  }
}