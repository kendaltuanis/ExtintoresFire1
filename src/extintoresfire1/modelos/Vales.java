package extintoresfire1.modelos;

import Datos.Conexion;
import Datos.ParametroSql;
import Datos.TipoDato;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Vales {

    String fecha, nombre, descripcion;

    int vale, otroscargos, id;

    public Vales(int id, String fecha, String nombre, int vale, String descripcion, int otroscargos) {
        this.id = id;
        this.fecha = fecha;
        this.nombre = nombre;
        this.vale = vale;
        this.descripcion = descripcion;
        this.otroscargos = otroscargos;
    }

    public Vales(String fecha, String nombre, int vale, String descripcion, int otroscargos) {
        this.fecha = fecha;
        this.nombre = nombre;
        this.vale = vale;
        this.descripcion = descripcion;
        this.otroscargos = otroscargos;
    }

    public static List<Vales> Refrescar() {

        String sql="SELECT id, fecha, empleado, vale, descripcion, otroscargos FROM vales;";
        List<Vales> vales = new ArrayList<>();
        ResultSet resul = Conexion.conexionL.EjecutarConsultaSql(sql, new ArrayList<>());
        try {
            while (resul.next()) {
                vales.add(new Vales(resul.getInt("id"),
                        resul.getString("fecha"),
                        resul.getString("empleado"),
                        resul.getInt("vale"),
                        resul.getString("descripcion"),
                        resul.getInt("otroscargos")
                ));

            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return vales;

    }

    public void Insertar() {

        String sql="INSERT INTO vales(fecha, empleado, vale, descripcion, otroscargos)VALUES (?, ?, ?, ?, ?);";

        List<ParametroSql> par = new ArrayList<>();
        par.add(new ParametroSql(fecha, TipoDato.Varchar));
        par.add(new ParametroSql(nombre, TipoDato.Varchar));
        par.add(new ParametroSql(vale, TipoDato.Integer));
        par.add(new ParametroSql(descripcion, TipoDato.Varchar));
        par.add(new ParametroSql(otroscargos, TipoDato.Integer));

        Conexion.conexionL.EjecutarSql(sql, par);

    }

    public String getFecha() {
        return fecha;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getVale() {
        return vale;
    }

    public int getOtroscargos() {
        return otroscargos;
    }
    
    

}
