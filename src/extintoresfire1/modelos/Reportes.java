package extintoresfire1.modelos;

import Datos.Conexion;
import Datos.ParametroSql;
import Datos.TipoDato;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Reportes {

    String fecha, ruta, gastos, numerorecibo, descripcion, notaextra;
    int id, realizosinrebajas, realizoconrebajas;

    public Reportes(int id, String fecha, String gastos, String numerorecibo, String descripcion, String notaextra, int realizosinrebajas, int realizoconrebajas) {
        this.id = id;
        this.fecha = fecha;
        this.gastos = gastos;
        this.numerorecibo = numerorecibo;
        this.descripcion = descripcion;
        this.notaextra = notaextra;
        this.realizosinrebajas = realizosinrebajas;
        this.realizoconrebajas = realizoconrebajas;
    }

    public Reportes(String fecha, String gastos, String numerorecibo, String descripcion, String notaextra, int realizosinrebajas, int realizoconrebajas) {
        this.fecha = fecha;
        this.gastos = gastos;
        this.numerorecibo = numerorecibo;
        this.descripcion = descripcion;
        this.notaextra = notaextra;
        this.realizosinrebajas = realizosinrebajas;
        this.realizoconrebajas = realizoconrebajas;
    }

    public void Insertar() {

        String sql="INSERT INTO reportes(fecha,gastos, numerorecibo, descripcion, notaextra, realizosinrebajas,realizoconrebajas) VALUES (?,?, ?, ?, ?, ?,?);";

        List<ParametroSql> par = new ArrayList<>();
        par.add(new ParametroSql(fecha, TipoDato.Varchar));
        par.add(new ParametroSql(gastos, TipoDato.Varchar));
        par.add(new ParametroSql(numerorecibo, TipoDato.Varchar));
        par.add(new ParametroSql(descripcion, TipoDato.Varchar));
        par.add(new ParametroSql(notaextra, TipoDato.Varchar));
        par.add(new ParametroSql(realizosinrebajas, TipoDato.Integer));
        par.add(new ParametroSql(realizoconrebajas, TipoDato.Integer));

        Conexion.conexionL.EjecutarSql(sql, par);

    }

    public static List<Reportes> Refrescar() {
        String sql="SELECT id,fecha, gastos, numerorecibo, descripcion, notaextra, realizosinrebajas,realizoconrebajas FROM reportes;";

        List<Reportes> reportes = new ArrayList<>();
        ResultSet resul = Conexion.conexionL.EjecutarConsultaSql(sql, new ArrayList<>());
        try {
            while (resul.next()) {
                reportes.add(new Reportes(resul.getInt("id"),
                        resul.getString("fecha"),
                        resul.getString("gastos"),
                        resul.getString("numerorecibo"),
                        resul.getString("descripcion"),
                        resul.getString("notaextra"),
                        resul.getInt("realizosinrebajas"),
                        resul.getInt("realizoconrebajas")
                ));

            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return reportes;
    }

    public String getGastos() {
        return gastos;
    }

    public int getRealizosinrebajas() {
        return realizosinrebajas;
    }

    public int getRealizoconrebajas() {
        return realizoconrebajas;
    }

    public String getFecha() {
        return fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }
    

}
