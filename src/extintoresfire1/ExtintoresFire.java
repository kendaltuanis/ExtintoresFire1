/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extintoresfire1;

import extintoresfire1.BD.ConexionBD;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ExtintoresFire extends Application {

    static long time_start, time_end, tiempoCalculoEntrada = 4000;
    static Thread hiloPrincipal, hiloTimer;
    static boolean ok = true;

   
    @Override
    public void start(Stage stage) throws Exception {
        time_start = System.currentTimeMillis();
        ConexionBD.ConexionUnida();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        time_end = System.currentTimeMillis();
        System.out.println(time_end - time_start);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
