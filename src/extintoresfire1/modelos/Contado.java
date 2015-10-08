package extintoresfire1.modelos;

import Datos.Conexion;
import Datos.ParametroSql;
import Datos.TipoDato;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Contado {

    String fecha, nombre, empresa, direccion, telefono, producto, precioytipo, mediodepago, ruta, cantidad, estado,numerocedula;
    int numerofactura, id;

    public Contado(int id, int numerofactura, String fecha, String nombre, String empresa, String direccion, String numerocedula, String telefono, String cantidad, String producto, String precioytipo, String mediodepago, String estado) {
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
        this.mediodepago = mediodepago;
        this.estado = estado;
    }

    public Contado(String fecha, String nombre, String empresa, String direccion, String numerocedula, String telefono, String cantidad, String producto, String precioytipo, String mediodepago) {
        this.fecha = fecha;
        this.nombre = nombre;
        this.empresa = empresa;
        this.direccion = direccion;
        this.numerocedula = numerocedula;
        this.telefono = telefono;
        this.cantidad = cantidad;
        this.producto = producto;
        this.precioytipo = precioytipo;
        this.mediodepago = mediodepago;
    }

    public Contado() {
    }

    public static List<Contado> Refrescar() {

        String sql = "SELECT id, numerofactura, fecha, nombre, empresa, direccion, telefono,cedula, cantidad, producto, precioytipo, mediopago, estado\n" +
"  FROM contado;";

        List<Contado> contados = new ArrayList<>();
        ResultSet resul = Conexion.conexionL.EjecutarConsultaSql(sql, new ArrayList<>());
        try {
            while (resul.next()) {
                contados.add(new Contado(resul.getInt("id"),
                        resul.getInt("numerofactura"),
                        resul.getString("fecha"),
                        resul.getString("nombre"),
                        resul.getString("empresa"),
                        resul.getString("direccion"),
                        resul.getString("cedula"),
                        resul.getString("telefono"),
                        resul.getString("cantidad"),
                        resul.getString("producto"),
                        resul.getString("precioytipo"),
                        resul.getString("mediopago"),
                        resul.getString("estado")
                ));

            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return contados;

    }

    public void Insertar() {

        String sql = "INSERT INTO contado( fecha, nombre, empresa, direccion, telefono,cedula, cantidad, producto, precioytipo, mediopago) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?,?, ?, ?);";

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
        par.add(new ParametroSql(mediodepago, TipoDato.Varchar));

        Conexion.conexionL.EjecutarSql(sql, par);

    }

    public void EditarEstado(int id,String estado) { //editar el estado de la factura: "Pago","Pendiente","Nula"
        String sql = "UPDATE contado SET estado=? WHERE id=?;";

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

    public String getMediodepago() {
        return mediodepago;
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
