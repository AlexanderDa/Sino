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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 *
 * @author alexnder
 */
public class CellButtons extends HBox {

    private final ImageView ivPencil = new ImageView(new Image(getClass().getResourceAsStream("imagenes/pencil.png")));
    private final ImageView ivTrash = new ImageView(new Image(getClass().getResourceAsStream("imagenes/trash.png")));
    public final Button btnDelete;
    private final Button btnEdit;

    public CellButtons() {
        setSpacing(10);
        setAlignment(Pos.CENTER);
        setPadding(new Insets(0, 10, 0, 10));
        //Image imageOk = new Image( this.getClass().getResourceAsStream("../imagenes/save.png"));
        btnDelete = new Button();
        btnDelete.setGraphic(ivTrash);
        btnEdit = new Button();
        btnEdit.setGraphic(ivPencil);


        getChildren().addAll(btnEdit, btnDelete);
        btnEdit.getStyleClass().add("btn-green");
        btnDelete.getStyleClass().add("btn-red");

    }

    public void deleteAction(EventHandler handler) {
        btnDelete.setOnAction(handler);
    }

    public void saveAction(EventHandler handler) {
        btnEdit.setOnAction(handler);
    }

}
