
package extintoresfire1;

import extintoresfire1.modelos.Noticias;
import java.util.List;


public class ControladorNoticias {
    
    
    public List<Noticias> Refrescar() {
        Noticias noticias = new Noticias();
        return noticias.Refrescar();
    }
    
}
