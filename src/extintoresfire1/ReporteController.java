package extintoresfire1;

import com.sun.javafx.scene.control.skin.DatePickerContent;
import com.sun.javafx.scene.control.skin.DatePickerSkin;
import extintoresfire1.ControladorBD.ControladorAbonos;
import extintoresfire1.ControladorBD.ControladorContado;
import extintoresfire1.modelos.Abonos;
import extintoresfire1.modelos.Contado;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class ReporteController implements Initializable {

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    public SimpleIntegerProperty gasto = new SimpleIntegerProperty();
    public SimpleStringProperty descripcion = new SimpleStringProperty();

    public int getGasto() {
        return gasto.get();
    }

    public String getDescripcion() {
        return descripcion.get();
    }

    @FXML
    private DatePicker calendario;
    @FXML
    private MenuBar menu;
    @FXML
    private TableView<ReporteController> tablaGastos;
    @FXML
    private TableView<String> tablasVales;
    @FXML
    private TextField txtVales, txtOtrosCargos, txtDescripcionCargos, txtGasto, txtDescripcionGasto,
            txtPagoCredito, txtEfectivo, txtDeposito, txtCheque, txtRealizoTotalSin, txtRealizoTotal, txtVale;

    int efectivo=0, deposito=0, cheque=0, realizoTotal=0, realiztoTotalSinGastos = 0;
    @FXML
    private TextArea notaExtra;
    @FXML
    TableColumn columnaGasto;
    @FXML
    TableColumn columnaDescripcion;

    @FXML
    private Button btnAgregarGasto, btnAgregarVale, btnCerrarDia, btnVerVales;
    @FXML
    private ListView<String> listaColaboladores;

    private DateCell iniCell = null;
    private DateCell endCell = null;
    private LocalDate iniDate;
    private LocalDate endDate;

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
            root = FXMLLoader.load(getClass().getResource("Reporte.fxml"));
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
    final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/uuuu", Locale.ENGLISH);
    ObservableList<ReporteController> data;

    @FXML
    private void AgregarGastos(ActionEvent e) {
        try {
            ReporteController re = new ReporteController();
            re.gasto.setValue(Integer.parseInt(txtGasto.getText()));
            re.descripcion.setValue(txtDescripcionGasto.getText());
            data.add(re);
            txtGasto.clear();
            txtDescripcionGasto.clear();
        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Datos erroneos");
            alert.setContentText("No hay ningún dato para agregar");
            alert.showAndWait();
        }
    }

    @FXML
    private void DatosFacturas(ActionEvent e) {

        ControladorContado ctrlContado = new ControladorContado();
        List<Contado> contados = ctrlContado.Refrescar();
        for (Contado contado : contados) {
            if (contado.getFecha().equals(calendario.getValue())) {
                if (!contado.getEstado().equals("Nula")) {
                    String[] montoFacturas = contado.getPrecioytipo().split(",");
                    if (contado.getMediodepago().equals("Efectivo")) {
                        for (String montoFactura : montoFacturas) {
                            efectivo += Integer.parseInt(montoFactura.substring(1, montoFactura.length()));
                        }                     
                    }
                    if (contado.getMediodepago().equals("Deposito")) {
                        for (String montoFactura : montoFacturas) {
                            deposito += Integer.parseInt(montoFactura.substring(1, montoFactura.length()));
                        } 
                    }
                    if (contado.getMediodepago().equals("Cheque")) {
                        for (String montoFactura : montoFacturas) {
                            cheque += Integer.parseInt(montoFactura.substring(1, montoFactura.length()));
                        } 
                    }
                }
            }
        }
    }
    @FXML
    private void AgregarAbonos(ActionEvent e) {

        int creditos = 0;
        ControladorAbonos ctrlAbonos = new ControladorAbonos();
        List<Abonos> abonos = ctrlAbonos.Refrescar();
        for (Abonos abono : abonos) {
            if (abono.getFecha().equals(calendario.getValue())) {
                if (!abono.getEstado().equals("Nula")) {

                    if (abono.getMediodepago().equals("Efectivo")) {
                        efectivo += abono.getAbono();
                    }
                    if (abono.getMediodepago().equals("Deposito")) {
                        deposito += abono.getAbono();
                    }
                    if (abono.getMediodepago().equals("Cheque")) {
                        cheque += abono.getAbono();
                    }

                    creditos += abono.getAbono();

                }
            }

        }
        txtPagoCredito.setText(String.valueOf(creditos));
        

    }

    private void Calendario() {
        calendario.setPromptText("Escoger Fecha");
        calendario.setConverter(new StringConverter<LocalDate>() {

            @Override
            public String toString(LocalDate object) {
                if (iniDate != null && endDate != null) {
                    return iniDate.format(formatter) + " - " + endDate.format(formatter);
                }
                return object.format(formatter);
            }

            @Override
            public LocalDate fromString(String string) {
                if (string.contains("-")) {
                    try {
                        iniDate = LocalDate.parse(string.split("-")[0].trim(), formatter);
                        endDate = LocalDate.parse(string.split("-")[1].trim(), formatter);
                    } catch (DateTimeParseException dte) {
                        return LocalDate.parse(string, formatter);
                    }
                    return iniDate;
                }
                return LocalDate.parse(string, formatter);
            }
        });

        calendario.showingProperty().addListener((obs, b, b1) -> {
            if (b1) {
                DatePickerContent content = (DatePickerContent) ((DatePickerSkin) calendario.getSkin()).getPopupContent();

                List<DateCell> cells = content.lookupAll(".day-cell").stream()
                        .filter(ce -> !ce.getStyleClass().contains("next-month"))
                        .map(n -> (DateCell) n)
                        .collect(Collectors.toList());

                // select initial range
                if (iniDate != null && endDate != null) {
                    int ini = iniDate.getDayOfMonth();
                    int end = endDate.getDayOfMonth();
                    cells.stream()
                            .forEach(ce -> ce.getStyleClass().remove("selected"));
                    cells.stream()
                            .filter(ce -> Integer.parseInt(ce.getText()) >= ini)
                            .filter(ce -> Integer.parseInt(ce.getText()) <= end)
                            .forEach(ce -> ce.getStyleClass().add("selected"));
                }
                iniCell = null;
                endCell = null;
                content.setOnMouseDragged(e -> {
                    Node n = e.getPickResult().getIntersectedNode();
                    DateCell c = null;
                    if (n instanceof DateCell) {
                        c = (DateCell) n;
                    } else if (n instanceof Text) {
                        c = (DateCell) (n.getParent());
                    }
                    if (c != null && c.getStyleClass().contains("day-cell")
                            && !c.getStyleClass().contains("next-month")) {
                        if (iniCell == null) {
                            iniCell = c;
                        }
                        endCell = c;
                    }
                    if (iniCell != null && endCell != null) {
                        int ini = (int) Math.min(Integer.parseInt(iniCell.getText()),
                                Integer.parseInt(endCell.getText()));
                        int end = (int) Math.max(Integer.parseInt(iniCell.getText()),
                                Integer.parseInt(endCell.getText()));
                        cells.stream()
                                .forEach(ce -> ce.getStyleClass().remove("selected"));
                        cells.stream()
                                .filter(ce -> Integer.parseInt(ce.getText()) >= ini)
                                .filter(ce -> Integer.parseInt(ce.getText()) <= end)
                                .forEach(ce -> ce.getStyleClass().add("selected"));
                    }
                });
                content.setOnMouseReleased(e -> {
                    if (iniCell != null && endCell != null) {
                        iniDate = LocalDate.of(calendario.getValue().getYear(),
                                calendario.getValue().getMonth(),
                                Integer.parseInt(iniCell.getText()));
                        endDate = LocalDate.of(calendario.getValue().getYear(),
                                calendario.getValue().getMonth(),
                                Integer.parseInt(endCell.getText()));
                        System.out.println("Selection from " + iniDate + " to " + endDate);

                        calendario.setValue(iniDate);
                        int ini = iniDate.getDayOfMonth();
                        int end = endDate.getDayOfMonth();
                        cells.stream()
                                .forEach(ce -> ce.getStyleClass().remove("selected"));
                        cells.stream()
                                .filter(ce -> Integer.parseInt(ce.getText()) >= ini)
                                .filter(ce -> Integer.parseInt(ce.getText()) <= end)
                                .forEach(ce -> ce.getStyleClass().add("selected"));
                    }
                    endCell = null;
                    iniCell = null;
                });
            }
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Calendario();
        columnaDescripcion.setCellValueFactory(new PropertyValueFactory<ReporteController, String>("descripcion"));
        columnaGasto.setCellValueFactory(new PropertyValueFactory<ReporteController, Integer>("gasto"));

        final ObservableList<String> listItems = FXCollections.observableArrayList("Eladio", "David");
        listaColaboladores.setItems(listItems);
        data = FXCollections.observableArrayList();
        tablaGastos.setItems(data);

    }

}
