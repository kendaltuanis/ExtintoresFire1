package extintoresfire1.ControladorBD;

import extintoresfire1.modelos.Creditos;
import java.util.List;

public class ControladorCreditos {

    public List<Creditos> Refrescar() {
        return Creditos.Refrescar();
    }

    public void Insertar(String fecha, String nombre,String empresa, String direccion, String numerocedula, String telefono, String cantidad, String producto, String precioytipo) {
        Creditos creditos = new Creditos(fecha, nombre, empresa, direccion, numerocedula, telefono, cantidad, producto, precioytipo);
        creditos.Insertar();
    }

    public void EditarEstado(String ruta, int id, String estado) {
        Creditos creditoEstado = new Creditos();
        creditoEstado.EditarEstado(id, estado);
    }

}
