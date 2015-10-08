
package extintoresfire1;

import com.sun.javafx.scene.control.skin.DatePickerContent;
import com.sun.javafx.scene.control.skin.DatePickerSkin;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.StringConverter;


public class ReporteController implements Initializable {

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    
    @FXML DatePicker calendario;
    @FXML TextField txtVale;
    @FXML
    private MenuBar menu;
    
    private DateCell iniCell=null;
private DateCell endCell=null;

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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        calendario.setPromptText("Escoger Fecha");
       calendario.setConverter(new StringConverter<LocalDate>() {

        @Override
        public String toString(LocalDate object) {
            if(iniDate!=null && endDate!=null){
                return iniDate.format(formatter)+" - "+endDate.format(formatter);
            }
            return object.format(formatter);
        }

        @Override
        public LocalDate fromString(String string) {
            if(string.contains("-")){
                try{
                    iniDate=LocalDate.parse(string.split("-")[0].trim(), formatter);
                    endDate=LocalDate.parse(string.split("-")[1].trim(), formatter);
                } catch(DateTimeParseException dte){
                    return LocalDate.parse(string, formatter);
                }
                return iniDate;
            }
            return LocalDate.parse(string, formatter);
        }
    });
 

    calendario.showingProperty().addListener((obs,b,b1)->{
        if(b1){
            DatePickerContent content = (DatePickerContent)((DatePickerSkin)calendario.getSkin()).getPopupContent();

            List<DateCell> cells = content.lookupAll(".day-cell").stream()
                    .filter(ce->!ce.getStyleClass().contains("next-month"))
                    .map(n->(DateCell)n)
                    .collect(Collectors.toList());

            // select initial range
            if(iniDate!=null && endDate!=null){
                int ini=iniDate.getDayOfMonth();
                int end=endDate.getDayOfMonth();
                cells.stream()
                    .forEach(ce->ce.getStyleClass().remove("selected"));
                cells.stream()
                    .filter(ce->Integer.parseInt(ce.getText())>=ini)
                    .filter(ce->Integer.parseInt(ce.getText())<=end)
                    .forEach(ce->ce.getStyleClass().add("selected"));
            }
            iniCell=null; 
            endCell=null;
            content.setOnMouseDragged(e->{
                Node n=e.getPickResult().getIntersectedNode();
                DateCell c=null;
                if(n instanceof DateCell){
                    c=(DateCell)n;
                } else if(n instanceof Text){
                    c=(DateCell)(n.getParent());
                }
                if(c!=null && c.getStyleClass().contains("day-cell") &&
                        !c.getStyleClass().contains("next-month")){
                    if(iniCell==null){
                        iniCell=c;
                    }
                    endCell=c;
                }
                if(iniCell!=null && endCell!=null){
                    int ini=(int)Math.min(Integer.parseInt(iniCell.getText()), 
                            Integer.parseInt(endCell.getText()));
                    int end=(int)Math.max(Integer.parseInt(iniCell.getText()), 
                            Integer.parseInt(endCell.getText()));
                    cells.stream()
                        .forEach(ce->ce.getStyleClass().remove("selected"));
                    cells.stream()
                        .filter(ce->Integer.parseInt(ce.getText())>=ini)
                        .filter(ce->Integer.parseInt(ce.getText())<=end)
                        .forEach(ce->ce.getStyleClass().add("selected"));
                }
            });
            content.setOnMouseReleased(e->{
                if(iniCell!=null && endCell!=null){
                    iniDate=LocalDate.of(calendario.getValue().getYear(), 
                                         calendario.getValue().getMonth(),
                                         Integer.parseInt(iniCell.getText()));
                    endDate=LocalDate.of(calendario.getValue().getYear(),
                                         calendario.getValue().getMonth(),
                                         Integer.parseInt(endCell.getText()));
                    System.out.println("Selection from "+iniDate+" to "+endDate);

                    calendario.setValue(iniDate);
                    int ini=iniDate.getDayOfMonth();
                    int end=endDate.getDayOfMonth();
                    cells.stream()
                        .forEach(ce->ce.getStyleClass().remove("selected"));
                    cells.stream()
                        .filter(ce->Integer.parseInt(ce.getText())>=ini)
                        .filter(ce->Integer.parseInt(ce.getText())<=end)
                        .forEach(ce->ce.getStyleClass().add("selected"));
                }
                endCell=null;
                iniCell=null;                   
            });
        }
    });
    }    
    
}
