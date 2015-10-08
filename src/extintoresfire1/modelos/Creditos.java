package extintoresfire1.modelos;

import Datos.Conexion;
import Datos.ParametroSql;
import Datos.TipoDato;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Creditos {

    String fecha, nombre, empresa, direccion, telefono, producto, precioytipo, ruta, cantidad, estado, numerocedula;
    int numerofactura, id;

    public Creditos(int id, int numerofactura, String fecha, String nombre, String empresa, String direccion, String numerocedula, String telefono, String cantidad, String producto, String precioytipo, String estado) {
        this.id = id;
        this.numerofactura = numerofactura;
        this.fecha = fecha;
        this.nombre = nombre;
        this.empresa = empresa;
        this.direccion = direccion;
        this.numerocedula = numerocedula;
        this.telefono = telefono;
        this.cantidad = cantidad;
        this.producto = producto;
        this.precioytipo = precioytipo;
        this.estado = estado;
    }

    public Creditos(String fecha, String nombre, String empresa, String direccion, String numerocedula, String telefono, String cantidad, String producto, String precioytipo) {
        this.fecha = fecha;
        this.nombre = nombre;
        this.empresa = empresa;
        this.direccion = direccion;
        this.numerocedula = numerocedula;
        this.telefono = telefono;
        this.cantidad = cantidad;
        this.producto = producto;
        this.precioytipo = precioytipo;
    }

    public Creditos() {
    }

    public static List<Creditos> Refrescar() {

        String sql = "SELECT id, numerofactura, fecha, nombre, empresa, direccion, telefono, cedula, cantidad, producto, precioytipo,estado\n"
                + "  FROM credito;";

        List<Creditos> contados = new ArrayList<>();
        ResultSet resul = Conexion.conexionL.EjecutarConsultaSql(sql, new ArrayList<>());
        try {
            while (resul.next()) {
                contados.add(new Creditos(resul.getInt("id"),
                        resul.getInt("numerofactura"),
                        resul.getString("fecha"),
                        resul.getString("nombre"),
                        resul.getString("empresa"),
                        resul.getString("direccion"),
                        resul.getString("telefono"),
                        resul.getString("cedula"),
                        resul.getString("cantidad"),
                        resul.getString("producto"),
                        resul.getString("precioytipo"),
                        resul.getString("estado")
                ));

            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return contados;

    }

    public void Insertar() {

        String sql = "INSERT INTO credito( fecha, nombre, empresa, direccion, telefono,cedula, cantidad, producto, precioytipo) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?,?, ?);";

        List<ParametroSql> par = new ArrayList<>();
        par.add(new ParametroSql(fecha, TipoDato.Varchar));
        par.add(new ParametroSql(nombre, TipoDato.Varchar));
        par.add(new ParametroSql(empresa, TipoDato.Varchar));
        par.add(new ParametroSql(direccion, TipoDato.Varchar));
        par.add(new ParametroSql(telefono, TipoDato.Varchar));
        par.add(new ParametroSql(numerocedula, TipoDato.Varchar));
        par.add(new ParametroSql(cantidad, TipoDato.Varchar));
        par.add(new ParametroSql(producto, TipoDato.Varchar));
        par.add(new ParametroSql(precioytipo, TipoDato.Varchar));

        Conexion.conexionL.EjecutarSql(sql, par);

    }

    public void EditarEstado(int id, String estado) { //editar el estado de la factura: "Pago","Pendiente","Nula"
        String sql = "UPDATE credito SET estado=? WHERE id=?;";

        List<ParametroSql> par = new ArrayList<>();

        par.add(new ParametroSql(estado, TipoDato.Varchar));
        par.add(new ParametroSql(id, TipoDato.Integer));

        Conexion.conexionL.EjecutarSql(sql, par);

    }

    public int getId() {
        return id;
    }

    public String getPrecioytipo() {
        return precioytipo;
    }

    public String getEstado() {
        return estado;
    }

    public String getFecha() {
        return fecha;
    }

    public int getNumerofactura() {
        return numerofactura;
    }

    public String getCantidad() {
        return cantidad;
    }

    public String getProducto() {
        return producto;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getNumerocedula() {
        return numerocedula;
    }

}
