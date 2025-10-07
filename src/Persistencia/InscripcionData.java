package Persistencia;

import Modelo.Inscripcion;
import java.sql.Connection;
import java.sql.*;

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
       }catch(SQLException ex){
           ex.printStackTrace();
       }
   } 
}
