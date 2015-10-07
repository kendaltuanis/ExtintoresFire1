package extintoresfire1.Controlador;

import extintoresfire1.modelos.Abonos;
import java.util.List;

public class ControladorAbonos {

    public List<Abonos> Refrescar(String ruta) {
        return Abonos.Refrescar(ruta);
    }

    public void Insertar(String ruta, String fecha, int numerofactura, String recibidode, String concepto, int abono, String mediodepago) {

        Abonos abonos = new Abonos(fecha, numerofactura, recibidode, concepto, abono, mediodepago);
        abonos.Insertar(ruta);
    }
    
     public void EditarEstado(String ruta, int id, String estado) {
        Abonos contadoEstado = new Abonos();
        contadoEstado.Editar(ruta, id, estado);
    }

}
