
package extintoresfire1;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.stage.Stage;

public class ReciboController implements Initializable {

    @FXML
    private MenuBar menu;
    

    @FXML
    private void irMenu(ActionEvent e) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        Stage stages = (Stage) menu.getScene().getWindow();
                stages.close();
                // la ventana en uso
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                // stage.setTitle("My New Stage Title");
                stage.setScene(scene);
                stage.show();
    }
    
     @FXML
    private void nuevoRecibo(ActionEvent e) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("Recibo.fxml"));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        Stage stages = (Stage) menu.getScene().getWindow();
                stages.close();
                // la ventana en uso
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                // stage.setTitle("My New Stage Title");
                stage.setScene(scene);
                stage.show();
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
