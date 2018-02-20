/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.sino.gui;

import ec.edu.sino.negocios.entidades.Docente;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author alexander
 */
public class FMainDocente extends HBox {

    private final Docente docente;
    private VBox taskBar;
    private StackPane container;

    public FMainDocente(Docente docente) {
        this.docente = docente;
        init();

    }

    private void init() {
        AnchorPane.setTopAnchor(this, 45.0);
        AnchorPane.setRightAnchor(this, 0.0);
        AnchorPane.setBottomAnchor(this, 0.0);
        AnchorPane.setLeftAnchor(this, 0.0);

        initTaskBar();
        initContainer();
        toBack();

    }

    private void initTaskBar() {
        taskBar = new VBox(10);
        taskBar.setPrefSize(200, USE_PREF_SIZE);
        taskBar.getStyleClass().add("task-bar");
        getChildren().add(taskBar);
    }

    private void initContainer() {
        container = new StackPane();
        container.setPrefSize(USE_PREF_SIZE, USE_PREF_SIZE);
        HBox.setHgrow(container, Priority.ALWAYS);
        
        getChildren().add(container);

    }

}
