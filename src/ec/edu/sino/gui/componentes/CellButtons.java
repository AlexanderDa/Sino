/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.sino.gui.componentes;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

/**
 *
 * @author alexnder
 */
public class CellButtons extends HBox {

    public final Button Delete;
    private final Button Save;

    public CellButtons() {
        setSpacing(10);
        setAlignment(Pos.CENTER);
        setPadding(new Insets(0, 10, 0, 10));
        //Image imageOk = new Image( this.getClass().getResourceAsStream("../imagenes/save.png"));
        Delete = new Button("Delete");
        Save = new Button("Save");
        Delete.setMinWidth(75);
        Save.setMinWidth(75);

        getChildren().addAll(Save, Delete);
        Save.getStyleClass().add("btn-green");
        Delete.getStyleClass().add("btn-red");

    }

    public void deleteAction(EventHandler handler) {
        Delete.setOnAction(handler);
    }

    public void saveAction(EventHandler handler) {
        Save.setOnAction(handler);
    }

}
