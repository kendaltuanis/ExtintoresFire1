    package extintoresfire1;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

public class MenuController implements Initializable {

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @FXML
    private Button facturacion,reportes;
    

    @FXML
    private void Facturacion(ActionEvent e) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Facturación");
        alert.setHeaderText("¿Donde quieres ir?");
        alert.setContentText("Escoge la opción");

        ButtonType buttonTypeOne = new ButtonType("Contado y Crédito");
        ButtonType buttonTypeTwo = new ButtonType("Abonos");
        ButtonType buttonTypeCancel = new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeCancel);

        Optional<ButtonType> result = alert.showAndWait();
        Parent root = null;
        if (result.get() == buttonTypeOne) {
            try {
                root = FXMLLoader.load(getClass().getResource("Factura.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(ControllerMain.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (result.get() == buttonTypeTwo) {
            try {
                root = FXMLLoader.load(getClass().getResource("Recibo.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(ControllerMain.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {

        }
        Stage stages = (Stage) facturacion.getScene().getWindow();
        stages.close();
        // la ventana en uso
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        // stage.setTitle("My New Stage Title");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void Reportes(ActionEvent e) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("Reporte.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(ControllerMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        Stage stages = (Stage) reportes.getScene().getWindow();
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
