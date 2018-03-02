/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.sino.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author alexander
 */
public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FPrincipal root = new FPrincipal();
        Scene scene = new Scene(root, 1000, 650);
        scene.getStylesheets().add(getClass().getResource("css/Principal.css").toExternalForm());
        scene.getStylesheets().add(getClass().getResource("css/Notification.css").toExternalForm());
        scene.getStylesheets().add(getClass().getResource("css/Usuarios.css").toExternalForm());
        scene.getStylesheets().add(getClass().getResource("css/Controles.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Sino");
        stage.setMaximized(true);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("imagenes/icono.png")));
        stage.show();
    }

}
