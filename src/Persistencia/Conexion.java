package Persistencia;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
        

public class Conexion {
    private String url;
    private String usuario;
    private String password;
    
    private static Connection conexion=null;

    public Conexion(String url, String usuario, String password) {
        this.url = url;
        this.usuario = usuario;
        this.password = password;
    }
    public Connection BuscarConexion(){
        if(conexion==null){
            try{
              Class.forName("org.mariadb.jdbc.Driver");
              conexion=DriverManager.getConnection(url, usuario, password);
            }catch(SQLException | ClassNotFoundException e){
                JOptionPane.showMessageDialog(null, "no se pudo establecer la conexion");
            }
            
        }
        return conexion;
        
    }
}
