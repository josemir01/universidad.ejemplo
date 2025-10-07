package Persistencia;

import Modelo.Alumno;
import Modelo.Inscripcion;
import Modelo.Materia;
import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InscripcionData {
    
    
    private AlumnoData idAlumno;
    private MateriaData idMateria;
    private Connection con = null;
    
    
    // hay q encontrar la forma de pasarle el idAlumno e idMateria como parametro

    public InscripcionData(Conexion con){
        this.con=con.BuscarConexion();
        this.idAlumno=new AlumnoData(con);
        this.idMateria=new MateriaData(con);
    }
    
   public void inscribirAlumno(Inscripcion inscripto){
       String query="INSERT INTO Inscripcion(nota) Values(?,?,?,?)";
       
       try{
          PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
          ps.setInt(1,inscripto.getNota());
          
          
          ps.executeUpdate();
          
          ResultSet rs = ps.getGeneratedKeys();
          if(rs.next()){
             inscripto.setIdInscripto(rs.getInt(1)); 
          }else{
                System.out.println("No se pudo obtener el ID ");
            }
          
           ps.close();
           System.out.println("inscripcion guardada correctamente");
       }catch(SQLException ex){
           ex.printStackTrace();
       }
   }
   
   public void BorrarInscripcion(int id){
       String sql = "DELETE * FROM inscripcion WHERE id_inscripto=?";
       try{
           
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            int filas = ps.executeUpdate();
            if (filas > 0) {
                System.out.println("inscripcion borrada de la DB");
            } else {
                System.out.println("No se encuentra la inscripcion con ID " + id);
            }
            ps.close();   
           
       }catch(SQLException ex){
          ex.printStackTrace();
       }
   }
   
  public void ActualizarNota(int id, int notanueva){
      String sql = "UPDATE inscripcion SET nota=? WHERE id_inscripto=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, notanueva);
            ps.setInt(2, id);
            int filas = ps.executeUpdate();
            if (filas > 0) {
                System.out.println("Nota actualizada correctamente");
            } else {
                System.out.println("No se encuentra la Nota con ID " + id);
            }
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
  }
  
  public ArrayList<Inscripcion> obtenerInscripciones(){
      ArrayList<Inscripcion> inscriptos=new ArrayList<>();
      String sql="Select * FROM inscripcion";
      try{
       PreparedStatement ps = con.prepareStatement(sql);
       ResultSet rs = ps.executeQuery();
       while(rs.next()){
        int idInscripto=rs.getInt("id_inscripto");
        int idAlumno=rs.getInt("id_alumno");
        int idMateria=rs.getInt("id_materia");
        int nota =rs.getInt("nota");
        
        Alumno alumno=idAlumno.buscarAlumno(idAlumno);
        Materia materia=idMateria.buscarMateria(idMateria);
        
        Inscripcion inscripto=new Inscripcion();
       }
       
      }catch(SQLException ex){
        ex.printStackTrace();
      }
        return null;
      
  }
}
