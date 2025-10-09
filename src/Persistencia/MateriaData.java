
package Persistencia;

import Modelo.Alumno;
import Modelo.Materia;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MateriaData {
  private Connection con = null;  

    public MateriaData(Conexion con) {
        this.con=con.BuscarConexion();
        
    }
   
    public void insertarMateria(Materia e){
     String query = "INSERT INTO materia(nombre,año,estado) VALUES(?,?,?) ";
      try {
            PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, e.getNombre());
            ps.setInt(2, e.getAnio());
            ps.setBoolean(3, e.isEstado());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                e.setIdMateria(rs.getInt(1));
            } else {
                System.out.println("No se pudo obtener tener el ID ");
            }

            ps.close();
            System.out.println("Materia guardado correctamente");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
      
     }
    public void mostrarTodos() {
        String sql = "SELECT * FROM materia";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Materia a = new Materia(
                        rs.getString("nombre"),
                        rs.getInt("año"),
                        rs.getBoolean("estado")
                );
                a.setIdMateria(rs.getInt("id_materia"));
                System.out.println("ID: " + a.getIdMateria()+
                        " | Nombre: " + a.getNombre()+
                        " | " + a.getAnio()+ ", " + 
                        " | Estado: " + (a.isEstado() ? "Activo" : "Inactivo"));
            }
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public List<Materia> obtenerTodas() {
    List<Materia> materias = new ArrayList<>();
    String sql = "SELECT * FROM materia";
    try {
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Materia m = new Materia(
                    rs.getString("nombre"),
                    rs.getInt("año"),
                    rs.getBoolean("estado")
            );
            m.setIdMateria(rs.getInt("id_materia"));
            materias.add(m);
        }
        ps.close();
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return materias;
}
    
    public Materia buscarMateria(int id) {
        Materia a = null;
        String sql = "SELECT * FROM materia WHERE id_materia=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                a = new Materia(
                        rs.getString("nombre"),
                        rs.getInt("año"),
                        rs.getBoolean("estado")
                );
                a.setIdMateria(rs.getInt("id_materia"));
                System.out.println("Encontrado: " + a.getNombre() + " " + a.getAnio());
            } else {
                System.out.println("No se encontró la Materia con ID " + id);
            }
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return a;
    }
    public void actualizarMateria(int id, String nuevoNombre, int nuevoAnio) {
        String sql = "UPDATE materia SET nombre=?, año=? WHERE id_materia=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nuevoNombre);
            ps.setInt(2, nuevoAnio);
            ps.setInt(3, id);
            int filas = ps.executeUpdate();
            if (filas > 0) {
                System.out.println("Materia actualizado correctamente");
            } else {
                System.out.println("No se encuentra la Materia con ID " + id);
            }
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public void bajaLogica(int id) {
        String sql = "UPDATE materia SET estado=false WHERE id_materia=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            int filas = ps.executeUpdate();
            if (filas > 0) {
                System.out.println("Materia dada de baja");
            } else {
                System.out.println("No se encuentra la Materia con ID " + id);
            }
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
   public void altaLogica(int id) {
        String sql = "UPDATE materia SET estado=true WHERE id_materia=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            int filas = ps.executeUpdate();
            if (filas > 0) {
                System.out.println("Materia dada de alta");
            } else {
                System.out.println("No se encuentra la Materia con ID " + id);
            }
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public void borrarMateria(int id) {
        String sql = "DELETE FROM materia WHERE id_materia=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            int filas = ps.executeUpdate();
            if (filas > 0) {
                System.out.println("Materia borrada de la DB");
            } else {
                System.out.println("No se encuentra la Materia con ID " + id);
            }
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
  

