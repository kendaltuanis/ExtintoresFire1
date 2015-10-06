
package extintoresfire1.modelos;

import Datos.Conexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class Noticias {
    
    private String noticia,fecha;
    private int id;

    public Noticias(int id,String noticia, String fecha) {
        this.id=id;
        this.noticia = noticia;
        this.fecha = fecha;
    }

    public Noticias() {
    }
    
    
    public List<Noticias> Refrescar() {

        String sql = "SELECT id, nombre, contrasena FROM usuarios;";
        
        List<Noticias> noticia = new ArrayList<>();
        ResultSet resulL = null;
        try {
             resulL = Conexion.conexionL.EjecutarConsultaSql(sql, new ArrayList<>());
        } catch (Exception e) {
            System.out.println(e.getCause());
        }
       
        try {
            while (resulL.next()) {
                noticia.add(new Noticias(resulL.getInt("id"),
                        resulL.getString("noticia"),
                        resulL.getString("fecha")));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return noticia;
    }

    public String getNoticia() {
        return noticia;
    }

    public String getFecha() {
        return fecha;
    }

    public int getId() {
        return id;
    }
    
    
}
