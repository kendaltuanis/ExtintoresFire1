package extintoresfire1;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;


public class Productos {
    
    public SimpleStringProperty nombre = new SimpleStringProperty();
    public SimpleStringProperty apellido = new SimpleStringProperty();
    public SimpleIntegerProperty edad = new SimpleIntegerProperty();
    public SimpleStringProperty telefono = new SimpleStringProperty();
    
    
    public String getNombre(){
        return nombre.get();
    }
    
    public String getApellido(){
        return apellido.get();
    }
    
    public Integer getEdad (){
        return edad.get();
    }
    
    public String getTelefono(){
        return telefono.get();
    }
    
}