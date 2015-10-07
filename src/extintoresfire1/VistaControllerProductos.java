package extintoresfire1;

import extintoresfire1.Controlador.ControladorContado;
import extintoresfire1.Controlador.ControladorCreditos;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class VistaControllerProductos implements Initializable {

    ControladorContado ctrlContado;
    ControladorCreditos ctrlCreditos;

    String cantidad, descripcion, precioytipo;

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
    @FXML
    private DatePicker fechaCalendario;

    private int posicionPersonaEnTabla;

    @FXML
    private RadioButton rdbContado, rdbCredito;
    @FXML
    private TextField txtGravado, txtExento, txtTotal, txtNombre, txtEmpresa, txtDireccion, txtTelefono, txtCedula;

    @FXML
    private MenuBar menu;

    @FXML
    private ComboBox cbmCantidad, cbmDescripcion, cmbTipoPago;

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

        try {
            cbmCantidad.getValue().toString();
            cbmCantidad.getValue().toString();
        } catch (Exception e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Campos erroneos");
            alert.setContentText("Cantidad o Descripción no está escogido");
            alert.showAndWait();
            return;
        }

        if (txtExento.getText().equals("") && txtGravado.getText().equals("")) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Espacio en blanco");
            alert.setContentText("Ingrese un monto en el espacio en blanco Gravado/Exento");
            alert.showAndWait();
            return;
        }
        if (txtExento.getText().equals("") && !txtGravado.getText().equals("")) {
            int gravado = Integer.parseInt(txtGravado.getText());
            int cantidad = Integer.parseInt(cbmCantidad.getValue().toString());
            Productos pro = new Productos();
            pro.nombre.set(cbmDescripcion.getValue().toString());  // nombre
            pro.apellido.set(null);   //apellido
            pro.edad.set(cantidad);   // edad
            pro.telefono.set(String.valueOf((gravado * cantidad))); // telefono
            productos.add(pro);
            txtGravado.clear();
            if (txtTotal.getText().equals("")) {
                txtTotal.setText(String.valueOf((gravado * cantidad)));
            } else {
                int t = Integer.parseInt(txtTotal.getText()) + (gravado * cantidad);
                txtTotal.setText(String.valueOf(t));
            }
            return;
        } else if (!txtExento.getText().equals("") && txtGravado.getText().equals("")) {
            int exento = Integer.parseInt(txtExento.getText());
            int cantidad = Integer.parseInt(cbmCantidad.getValue().toString());
            Productos pro = new Productos();
            pro.nombre.set(cbmDescripcion.getValue().toString());
            pro.apellido.set(String.valueOf((exento * cantidad)));
            pro.edad.set(cantidad);
            pro.telefono.set(null);
            productos.add(pro);
            txtExento.clear();

            if (txtTotal.getText().equals("")) {
                txtTotal.setText(String.valueOf((exento * cantidad)));
            } else {
                int t = Integer.parseInt(txtTotal.getText()) + (exento * cantidad);
                txtTotal.setText(String.valueOf(t));
            }
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

    private void inicializarComboBoxs() {
        cbmCantidad.getItems().addAll(
                1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20
        );
        cbmDescripcion.getItems().addAll(
                "Ext ABC 4kg", "Ext ABC 2kg", "Ext ABC 1kg", "Ext BC 4.5kg", "RepExtABC 10lbs", "RepExtABC 5lbs", "RepExtABC 2.2lbs", "RepExtABC 20lbs", "RepExtABC 15lbs",
                "RecExtBC 10lbs", "RecExtBC 20lbs", "RecExtBC 15lbs", "RecExtBC 5lbs", "ManBC 10lbs", "ManBC 20lbs", "ManBC 5lbs", "ManBC 15lbs", "RepExtAFFF 20lbs", "RepExtAFFF 10lbs"
        );
        cmbTipoPago.getItems().addAll("Efectivo", "Transferencia/Depósito", "Cheque");

    }

    @FXML
    private void AgregarFactura(ActionEvent event) {

        if (!rdbContado.isSelected() && !rdbCredito.isSelected()) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Tipo Factura");
            alert.setContentText("Selecciona el tipo de factura(Contado/Credito)");
            alert.showAndWait();
            return;
        }
        if (validarFecha() && cmbTipoPago.getValue() != null) {
            if (rdbContado.isSelected()) {
                ctrlContado = new ControladorContado();
                ObtenerDatosTabla();
                ctrlContado.Insertar(fechaCalendario.getValue().toString(), txtNombre.getText(),
                        txtEmpresa.getText(), txtDireccion.getText(), txtCedula.getText(),
                        txtTelefono.getText(), cantidad, descripcion, precioytipo, cmbTipoPago.getValue().toString());
                return;
            } else {
                ctrlCreditos = new ControladorCreditos();
                ObtenerDatosTabla();
                ctrlCreditos.Insertar(fechaCalendario.getValue().toString(), txtNombre.getText(),
                        txtEmpresa.getText(), txtDireccion.getText(), txtCedula.getText(),
                        txtTelefono.getText(), cantidad, descripcion, precioytipo);

                return;
            }

        }
        if (!rdbCredito.isSelected()) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Método de pago");
            alert.setContentText("Selecciona el método de pago");
            alert.showAndWait();
        }

    }

    private void ObtenerDatosTabla() {

        cantidad = "";
        descripcion = "";
        precioytipo = "";

        int size = tablaPersonas.getItems().size();
        if (size == 0) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Tabla erronea");
            alert.setContentText("No hay ningún producto para hacer una factura");
            alert.showAndWait();
            return;
        }
        for (int i = 0; i < size; i++) {
            cantidad += tablaPersonas.getItems().get(i).getEdad() + ",";
            descripcion += tablaPersonas.getItems().get(i).getNombre() + ",";
            if (tablaPersonas.getItems().get(i).getApellido() == null) {
                precioytipo += "G" + tablaPersonas.getItems().get(i).getTelefono() + ",";
            } else {
                precioytipo += "E" + tablaPersonas.getItems().get(i).getApellido() + ",";
            }
        }
        cantidad = cantidad.substring(0, cantidad.length() - 1);
        descripcion = descripcion.substring(0, descripcion.length() - 1);
        precioytipo = precioytipo.substring(0, precioytipo.length() - 1);
    }

    private boolean validarFecha() {

        System.out.println(fechaCalendario.getValue());
        System.out.println(LocalDate.now());

        if (fechaCalendario.getValue() == null) {
            fechaCalendario.setValue(LocalDate.now());
            return true;
        }
        if (!fechaCalendario.getValue().equals(LocalDate.now())) {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Ventana de confirmación");
            alert.setHeaderText("Fecha diferente");
            alert.setContentText("¿Quieres agregar la factura con una fecha diferente de hoy? (No recomendado)");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                return true;
            } else {
                return false;
            }
        }
        return true;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // Inicializamos la tabla
        this.inicializarTablaPersonas();

        inicializarComboBoxs();

        // Solo números en los textfield
        SoloNumeros();

        // Seleccionar las tuplas de la tabla de las productos
        final ObservableList<Productos> tablaPersonaSel = tablaPersonas.getSelectionModel().getSelectedItems();

        tablaPersonaSel.addListener(selectorTablaPersonas);

    }
}
