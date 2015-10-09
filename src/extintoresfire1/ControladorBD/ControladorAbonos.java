package extintoresfire1.ControladorBD;

import extintoresfire1.modelos.Abonos;
import java.util.List;

public class ControladorAbonos {

    public List<Abonos> Refrescar() {
        return Abonos.Refrescar();
    }

    public void Insertar(String fecha, int numerofactura, String recibidode, String concepto, int abono, String mediodepago) {

        Abonos abonos = new Abonos(fecha, numerofactura, recibidode, concepto, abono, mediodepago);
        abonos.Insertar();
    }
    
     public void EditarEstado( int id, String estado) {
        Abonos contadoEstado = new Abonos();
        contadoEstado.Editar(id, estado);
    }

}
