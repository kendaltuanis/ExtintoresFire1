package extintoresfire1.modelos;

import Datos.Conexion;
import Datos.ParametroSql;
import Datos.TipoDato;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class Usuarios {

    private int id;
    private String nombre, clave;

    public Usuarios(String nombre, String clave) {
        this.nombre = nombre;
        this.clave = clave;
    }

    public Usuarios(int id, String nombre, String clave) {
        this.id = id;
        this.nombre = nombre;
        this.clave = clave;
    }

    public Usuarios() {
    }

    public List<Usuarios> Refrescar() {

        String sql;

        List<Usuarios> usuario = new ArrayList<>();
        ResultSet resulL = null;

        if (Conexion.conexionI!=null) {
            sql = "SELECT id, nombre, contrasena FROM \"Extintores\".usuarios;";

            resulL = Conexion.conexionI.EjecutarConsultaSql(sql, new ArrayList<>());
        }else{
        if (Conexion.conexionL!=null) {
            sql = "SELECT id, nombre, contrasena FROM usuarios;";
            resulL = Conexion.conexionL.EjecutarConsultaSql(sql, new ArrayList<>());
        }
        }
        

        try {
            while (resulL.next()) {
                usuario.add(new Usuarios(resulL.getInt("id"),
                        resulL.getString("nombre"),
                        resulL.getString("contrasena")));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return usuario;
    }

    public void Insertar(ResultSet resul) {
        String sql = "INSERT INTO usuario(id, usuario, contrasena) VALUES (?, ?, ?);";

        try {
            List<ParametroSql> par = new ArrayList<>();
            par.add(new ParametroSql(resul.getInt("id"), TipoDato.Integer));
            par.add(new ParametroSql(resul.getString("usuario"), TipoDato.Varchar));
            par.add(new ParametroSql(resul.getString("contrasena"), TipoDato.Varchar));

            Conexion.conexionL.EjecutarSql(sql, par);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }

    }

    public void borrarContenido() {

        String sql = "TRUNCATE usuario";
        Conexion.conexionL.BorrarTabla(sql);

    }

    public String getNombre() {
        return nombre;
    }

    public String getClave() {
        return clave;
    }

}
