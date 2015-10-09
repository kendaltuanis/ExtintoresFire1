package extintoresfire1.ControladorBD;


import extintoresfire1.modelos.Vales;
import java.util.List;

public class ControladorVales {

    public List<Vales> Refrescar() {
        return Vales.Refrescar();
    }

    public void Insertar(String fecha, String nombre, int vale, String descripcion, int otroscargos) {
        Vales vales = new Vales(fecha, nombre, vale, descripcion, otroscargos);
        vales.Insertar();
    }

}
