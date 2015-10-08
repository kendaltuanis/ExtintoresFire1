package extintoresfire1.ControladorBD;

import extintoresfire1.modelos.Usuarios;
import java.util.List;

public class ControladorUsuarios {

    public List<Usuarios> Refrescar() {
        Usuarios usuarios = new Usuarios();
        return usuarios.Refrescar();
    }
    
    public void borrar(){
     Usuarios usuarios = new Usuarios();
    usuarios.borrarContenido();
    }

}
