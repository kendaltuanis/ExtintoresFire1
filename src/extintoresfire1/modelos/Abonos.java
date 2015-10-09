package extintoresfire1.modelos;

import Datos.Conexion;
import Datos.ParametroSql;
import Datos.TipoDato;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Abonos {

    String fecha, ruta, recibidode, concepto, mediodepago, estado;
    int id, numerofactura, abono, numerorecibo;

    public Abonos(int id, int numerorecibo, String fecha, int numerofactura, String recibidode, String concepto, int abono, String mediodepago, String estado) {
        this.id = id;
        this.numerorecibo = numerorecibo;
        this.fecha = fecha;
        this.numerofactura = numerofactura;
        this.recibidode = recibidode;
        this.concepto = concepto;
        this.abono = abono;
        this.mediodepago = mediodepago;
        this.estado = estado;

    }

    public Abonos(String fecha, int numerofactura, String recibidode, String concepto, int abono, String mediodepago) {
        this.fecha = fecha;
        this.numerofactura = numerofactura;
        this.recibidode = recibidode;
        this.concepto = concepto;
        this.abono = abono;
        this.mediodepago = mediodepago;

    }

    public Abonos() {
    }

    public static List<Abonos> Refrescar() {

        String sql = "SELECT id, numerorecibo, fecha, numerofactura, recibidode, concepto,abono, mediodepago, estado FROM abonos;";

        List<Abonos> abonos = new ArrayList<>();
        ResultSet resul = Conexion.conexionL.EjecutarConsultaSql(sql, new ArrayList<>());
        try {
            while (resul.next()) {
                abonos.add(new Abonos(resul.getInt("id"),
                        resul.getInt("numerorecibo"),
                        resul.getString("fecha"),
                        resul.getInt("numerofactura"),
                        resul.getString("recibidode"),
                        resul.getString("concepto"),
                        resul.getInt("abono"),
                        resul.getString("mediodepago"),
                        resul.getString("estado")
                ));

            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return abonos;

    }

    public void Insertar() {
        String sql = "INSERT INTO abonos(fecha, numerofactura, recibidode, concepto,abono, mediodepago)\n"
                + "    VALUES (?, ?, ?, ?, ?, ?);";

        List<ParametroSql> par = new ArrayList<>();
        par.add(new ParametroSql(fecha, TipoDato.Varchar));
        par.add(new ParametroSql(numerofactura, TipoDato.Integer));
        par.add(new ParametroSql(recibidode, TipoDato.Varchar));
        par.add(new ParametroSql(concepto, TipoDato.Varchar));
        par.add(new ParametroSql(abono, TipoDato.Integer));
        par.add(new ParametroSql(mediodepago, TipoDato.Varchar));

        Conexion.conexionL.EjecutarSql(sql, par);

    }

    public void Editar(int id, String estado) { //editar el estado de la factura: "Pago","Pendiente","Nula"
        String sql="UPDATE abonos SET estado=? WHERE id=?;";

        List<ParametroSql> par = new ArrayList<>();
        par.add(new ParametroSql(estado, TipoDato.Varchar));
        par.add(new ParametroSql(id, TipoDato.Integer));

        Conexion.conexionL.EjecutarSql(sql, par);

    }

    public int getNumerofactura() {
        return numerofactura;
    }

    public int getAbono() {
        return abono;
    }

    public int getNumerorecibo() {
        return numerorecibo;
    }

    public String getMediodepago() {
        return mediodepago;
    }

    public String getEstado() {
        return estado;
    }

    public String getFecha() {
        return fecha;
    }

}
