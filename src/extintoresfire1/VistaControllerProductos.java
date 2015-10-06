package extintoresfire1;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class VistaControllerProductos implements Initializable {

    // Declaramos la tabla y las columnas
    @FXML
    private TableView<Productos> tablaPersonas;
    @FXML
    private TableColumn nombreCL; // descripcion
    @FXML
    private TableColumn apellidoCL;  // exento
    @FXML
    private TableColumn edadCL;  // cantidad
    @FXML
    private TableColumn telefonoCL; // gravado
    ObservableList<Productos> productos;   // tab

    private int posicionPersonaEnTabla;

    @FXML
    private TextField txtGravado;

    @FXML
    private TextField txtExento;

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
    private void nuevaFactura(ActionEvent e) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("Factu.fxml"));
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
    private void aniadir(ActionEvent event) {

        if (txtExento.getText().equals("") && txtGravado.getText().equals("")) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Espacio en blanco");
            alert.setContentText("Ingrese un monto en el espacio en blanco Gravado/Exento");
            alert.showAndWait();
            return;
        }
          

        if (txtExento.getText().equals("") && !txtGravado.getText().equals("")) {
            Productos pro = new Productos();
            pro.nombre.set("PRUEBA");  // nombre
            pro.apellido.set(null);   //apellido
            pro.edad.set(24);   // edad
            pro.telefono.set(txtGravado.getText()); // telefono
            productos.add(pro);
            txtGravado.clear();
            return;
        } else if (!txtExento.getText().equals("") && txtGravado.getText().equals("")) {
            Productos pro = new Productos();
            pro.nombre.set("PRUEBA");
            pro.apellido.set(txtExento.getText());
            pro.edad.set(24);
            pro.telefono.set(null);
            productos.add(pro);
             txtExento.clear();
            return;
        }

        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Campos erroneos");
        alert.setContentText("No pueden estar llenos los campos Gravado y Exento al mismo tiempo");
        alert.showAndWait();
    }

    @FXML
    private void eliminar(ActionEvent event) {
        productos.remove(posicionPersonaEnTabla);
    }
    /**
     * Listener de la tabla productos
     */
    private final ListChangeListener<Productos> selectorTablaPersonas
            = new ListChangeListener<Productos>() {
                @Override
                public void onChanged(ListChangeListener.Change<? extends Productos> c) {
                    ponerPersonaSeleccionada();
                }
            };

    /**
     * PARA SELECCIONAR UNA CELDA DE LA TABLA "tablaProductos"
     */
    public Productos getTablaPersonasSeleccionada() {
        if (tablaPersonas != null) {
            List<Productos> tabla = tablaPersonas.getSelectionModel().getSelectedItems();
            if (tabla.size() == 1) {
                final Productos competicionSeleccionada = tabla.get(0);
                return competicionSeleccionada;
            }
        }
        return null;
    }

    /**
     * Método para poner en los textFields la tupla que selccionemos
     */
    private void ponerPersonaSeleccionada() {
        final Productos persona = getTablaPersonasSeleccionada();
        posicionPersonaEnTabla = productos.indexOf(persona);

        if (persona != null) {

            // Pongo los textFields con los datos correspondientes           
            // Pongo los botones en su estado correspondiente        
        }
    }

    /**
     * Método para inicializar la tabla
     */
    private void inicializarTablaPersonas() {
        nombreCL.setCellValueFactory(new PropertyValueFactory<Productos, String>("nombre"));
        apellidoCL.setCellValueFactory(new PropertyValueFactory<Productos, String>("apellido"));
        edadCL.setCellValueFactory(new PropertyValueFactory<Productos, Integer>("edad"));
        telefonoCL.setCellValueFactory(new PropertyValueFactory<Productos, String>("telefono"));

        productos = FXCollections.observableArrayList();
        tablaPersonas.setItems(productos);

    }

    private void SoloNumeros() {
        txtExento.lengthProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                if (newValue.intValue() > oldValue.intValue()) {
                    char ch = txtExento.getText().charAt(oldValue.intValue());
                    //Check if the new character is the number or other's
                    if (!(ch >= '0' && ch <= '9')) {

                        //if it's not number then just setText to previous one
                        txtExento.setText(txtExento.getText().substring(0, txtExento.getText().length() - 1));
                    }
                }
            }

        });

        txtGravado.lengthProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                if (newValue.intValue() > oldValue.intValue()) {
                    char ch = txtGravado.getText().charAt(oldValue.intValue());
                    //Check if the new character is the number or other's
                    if (!(ch >= '0' && ch <= '9')) {

                        //if it's not number then just setText to previous one
                        txtGravado.setText(txtGravado.getText().substring(0, txtGravado.getText().length() - 1));
                    }
                }
            }

        });

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // Inicializamos la tabla
        this.inicializarTablaPersonas();

        // Solo números en los textfield
        SoloNumeros();

        // Seleccionar las tuplas de la tabla de las productos
        final ObservableList<Productos> tablaPersonaSel = tablaPersonas.getSelectionModel().getSelectedItems();

        tablaPersonaSel.addListener(selectorTablaPersonas);

    }
}
