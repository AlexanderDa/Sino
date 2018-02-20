/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.sino.gui;

import ec.edu.sino.gui.componentes.GodPane;
import ec.edu.sino.negocios.entidades.Administrador;
import javafx.event.EventHandler;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author alexander
 */
public class FMainAdmin extends HBox {

    private final Administrador admin;
    private VBox taskBar;

    public FMainAdmin(Administrador admin) {
        this.admin = admin;
        init();

    }

    private void init() {
        AnchorPane.setTopAnchor(this, 45.0);
        AnchorPane.setRightAnchor(this, 0.0);
        AnchorPane.setBottomAnchor(this, 0.0);
        AnchorPane.setLeftAnchor(this, 0.0);

        initTaskBar();
        toBack();

    }

    private void initTaskBar() {
        taskBar = new VBox(10);
        taskBar.setPrefSize(200, USE_PREF_SIZE);
        taskBar.getStyleClass().add("task-bar");
        FPeriodo pane = new FPeriodo();
        
     
        getChildren().addAll(taskBar,pane.start());
    }

    private EventHandler handler(){
    return (t) -> {
        System.err.println("Hola");
        };
    }

}
