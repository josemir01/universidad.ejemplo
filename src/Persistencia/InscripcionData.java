package Persistencia;

import Modelo.Alumno;
import Modelo.Inscripcion;
import Modelo.Materia;
import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class InscripcionData {
    
    
    private AlumnoData ad;
    private MateriaData am;
    private Connection con = null;
    
    
    // hay q encontrar la forma de pasarle el idAlumno e idMateria como parametro

    public InscripcionData(Conexion con){
        this.con=con.BuscarConexion();
        this.ad=new AlumnoData(con);
        this.am=new MateriaData(con);
    }
    
   public void inscribirAlumno(Inscripcion inscripto){
       String query="INSERT INTO Inscripcion(id_inscricion) Values(?)";
       
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
   
    public void BorrarInscripcion(int idAlumno,int idMateria){
       String sql = "DELETE  FROM inscripcion WHERE  id_alumno=? AND id_materia=?";
       try{
           
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idAlumno);
            ps.setInt(2,idMateria);
            int filas = ps.executeUpdate();
            if (filas > 0) {
                System.out.println("inscripcion borrada de la DB");
            } else {
                System.out.println("No se encuentra la inscripcion con ID de Alumno " + idAlumno+ " Materia"+" "+idMateria );
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
                JOptionPane.showMessageDialog(null, "Nota actualizada correctamente");
            } else {
                JOptionPane.showMessageDialog(null, "No se encuentra la Nota con ID " + id);
            }
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
  }
  
  public List<Inscripcion> obtenerInscripcionesPorAlumno(int idAlumno) {
    ArrayList<Inscripcion> inscriptos = new ArrayList<>();
    String sql = "SELECT * FROM inscripcion WHERE id_alumno = ?";
    try {
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, idAlumno);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int idInscripto = rs.getInt("id_inscripto");
            int idMateria = rs.getInt("id_materia");
            int nota = rs.getInt("nota");

            Alumno alumno = ad.buscarAlumno(idAlumno);
            Materia materia = am.buscarMateria(idMateria);

            Inscripcion inscripto = new Inscripcion(nota, alumno, materia);
            inscripto.setIdInscripto(idInscripto);
            inscriptos.add(inscripto);
        }
        ps.close();
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return inscriptos;
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
        
         Alumno alumno=ad.buscarAlumno(idAlumno);
         Materia materia=am.buscarMateria(idMateria);
                 
        Inscripcion inscripto=new Inscripcion(nota,alumno,materia);
        inscripto.setIdInscripto(rs.getInt("id_inscripto"));
        inscripto.getIdAlumno().setId_alumno(rs.getInt("id_alumno"));
        inscriptos.add(inscripto);
        
       }
         ps.close();
      }catch(SQLException ex){
        ex.printStackTrace();
    }
    return inscriptos;
}
  
  
   public List<Materia> obtenerMateriasCursadas(int idAlumno) {
    ArrayList<Materia> materias = new ArrayList<>();

    String sql = " SELECT inscripcion.id_materia, nombre, año "
               + " FROM inscripcion, materia "
               + " WHERE inscripcion.id_materia = materia.id_materia "
               + " AND inscripcion.id_alumno = ?;";

    try {
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, idAlumno);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Materia materia = new Materia();
            materia.setIdMateria(rs.getInt("id_materia"));
            materia.setNombre(rs.getString("nombre"));
            materia.setAnio(rs.getInt("año"));
            materias.add(materia);
        }

        rs.close();
        ps.close();

    } catch (SQLException ex) {
        ex.printStackTrace();
    }

    return materias;
}
}
