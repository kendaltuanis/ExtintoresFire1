package extintoresfire1.ControladorBD;


import extintoresfire1.modelos.Reportes;
import java.util.List;

public class ControladorReportes {

    public List<Reportes> Refrescar() {
        return Reportes.Refrescar();
    }

    public void Insertar(String fecha, String gastos, String numerorecibo, String descripcion, String notaextra, int realizosinrebajas, int realizoconrebajas) {

        Reportes reporte = new Reportes(fecha, gastos, numerorecibo, descripcion, notaextra, realizosinrebajas, realizoconrebajas);
        reporte.Insertar();
    }

}
