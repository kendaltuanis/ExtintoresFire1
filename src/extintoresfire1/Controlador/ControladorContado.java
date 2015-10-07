package extintoresfire1.Controlador;

import extintoresfire1.modelos.Contado;
import java.util.List;

public class ControladorContado {

    public List<Contado> Refrescar() {
        return Contado.Refrescar();
    }

    public void Insertar(String fecha, String nombre,String empresa, String direccion, String numerocedula, String telefono, String cantidad, String producto, String precioytipo, String mediodepago) {
        Contado contados = new Contado(fecha, nombre,empresa, direccion, numerocedula, telefono, cantidad, producto, precioytipo, mediodepago);
        contados.Insertar();
    }

    public void EditarEstado(int id,String estado) {
        Contado contadoEstado = new Contado();
        contadoEstado.EditarEstado(id,estado);
    }

}
