package extintoresfire1;

import extintoresfire1.BD.ConexionBD;
import extintoresfire1.ControladorBD.ControladorUsuarios;
import extintoresfire1.modelos.Noticias;
import extintoresfire1.modelos.Usuarios;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ControllerMain implements Initializable {

    @FXML
    private PasswordField passwordField;

    @FXML
    private TitledPane panelNoticias;

    @FXML
    private Button ingresar;

    @FXML
    private void Ingresar(ActionEvent e) {

        ControladorUsuarios ctrlUsuarios = new ControladorUsuarios();
        String clave = passwordField.getText();
        System.out.println(clave);
        List<Usuarios> usuarios = null;
        try {
            usuarios = ctrlUsuarios.Refrescar();
        } catch (Exception a) {
            System.out.println(a.getMessage());
            ConexionBD.ConexionUnida();

        }

        for (Usuarios usuario : usuarios) {
            System.out.println(usuario.getNombre());
            System.out.println(usuario.getClave());

            if (usuario.getNombre().equals("RutaNacional") && usuario.getClave().equals(clave)) {
                Parent root = null;
                 try {
                        root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
                    } catch (IOException ex) {
                        Logger.getLogger(ControllerMain.class.getName()).log(Level.SEVERE, null, ex);
                    }
                //Cerrar
                Stage stages = (Stage) ingresar.getScene().getWindow();
                stages.close();
                // la ventana en uso
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                // stage.setTitle("My New Stage Title");
                stage.setScene(scene);
                stage.show();

                return;
            }

        }
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Error de contraseña");
        alert.setContentText("Contraseña incorrecta");
        alert.showAndWait();
    }

    private void SetNoticias() {

        ControladorNoticias ctrlNoticias = new ControladorNoticias();
        GridPane grid = new GridPane();
        grid.setVgap(4);
        grid.setPadding(new javafx.geometry.Insets(5, 5, 5, 5));
        List<Noticias> noticias = ctrlNoticias.Refrescar();
        for (int i = 0; i < noticias.size(); i++) {
            grid.add(new Label(noticias.get(i).getFecha() + ": " + noticias.get(i).getNoticia()), 0, i);
        }

        grid.setAlignment(Pos.TOP_RIGHT);

        panelNoticias.setContent(grid);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //SetNoticias();   
    }

}
