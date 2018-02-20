/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.sino.gui.componentes;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author alexander
 */
public class GodPane extends AnchorPane {

    private final ImageView view = new ImageView(new Image(getClass().getResourceAsStream("imagenes/exclamacion.png")));
    private Label lblTitle;
    private StackPane centerPane;
    private StackPane alertPane;
    private StackPane insertPane;
    private StackPane updatePane;
    private Notification notification;

    public GodPane() {

    }

    public void init() {

        HBox.setHgrow(this, Priority.ALWAYS);
        centerPane = new StackPane();
        AnchorPane.setTopAnchor(centerPane, 0.0);
        AnchorPane.setRightAnchor(centerPane, 0.0);
        AnchorPane.setBottomAnchor(centerPane, 0.0);
        AnchorPane.setLeftAnchor(centerPane, 0.0);

        alertPane = new StackPane();
        AnchorPane.setTopAnchor(alertPane, -45.0);
        AnchorPane.setRightAnchor(alertPane, 0.0);
        AnchorPane.setBottomAnchor(alertPane, 0.0);
        AnchorPane.setLeftAnchor(alertPane, -200.0);
        alertPane.getStyleClass().add("alert-pane");

        insertPane = new StackPane();
        AnchorPane.setTopAnchor(insertPane, -45.0);
        AnchorPane.setRightAnchor(insertPane, 0.0);
        AnchorPane.setBottomAnchor(insertPane, 0.0);
        AnchorPane.setLeftAnchor(insertPane, -200.0);
        insertPane.getStyleClass().add("alert-pane");


        updatePane = new StackPane();
        AnchorPane.setTopAnchor(updatePane, -45.0);
        AnchorPane.setRightAnchor(updatePane, 0.0);
        AnchorPane.setBottomAnchor(updatePane, 0.0);
        AnchorPane.setLeftAnchor(updatePane, -200.0);
        updatePane.getStyleClass().add("alert-pane");

        notification = new Notification();
        //mnotification.rightUpperCorner();
        notification.toFront();
        getChildren().addAll(centerPane, notification);

    }

    public void failed(String mensaje) {
        notification.failed(mensaje);
    }

    public void successful(String mensaje) {
        notification.successful(mensaje);
    }

    public void setTitle(String title) {
        lblTitle = new Label();
        lblTitle.setAlignment(Pos.CENTER);
        lblTitle.setText(title);
        AnchorPane.setTopAnchor(lblTitle, 0.0);
        AnchorPane.setRightAnchor(lblTitle, 0.0);
        AnchorPane.setLeftAnchor(lblTitle, 0.0);
        AnchorPane.setTopAnchor(centerPane, 25.0);
        getChildren().add(lblTitle);

    }

    public void addCenter(Node node) {
        centerPane.getChildren().add(node);
    }

    public void addInsertPane(Node node) {
        node.getStyleClass().add("pane");
        insertPane.getChildren().add(node);
    }

    public void addUpdatePane(Node node) {
        node.getStyleClass().add("pane");
        updatePane.getChildren().add(node);
    }

    public void showAlert(EventHandler handler) {
        VBox pane = new VBox(25);
        pane.setPadding(new Insets(25));
        pane.getStyleClass().add("pane");
        pane.setMaxSize(400, 300);
        pane.setAlignment(Pos.CENTER);

        Label lblSure = new Label("¿Estás seguro de eliminar?");
        lblSure.getStyleClass().add("lbl-sure");
        Label lblMessage = new Label("Una vez que se elimina, no se puede recuperar.");
        lblMessage.getStyleClass().add("lbl-message");
        
        HBox buttonsPane = new HBox(25);
        buttonsPane.setAlignment(Pos.CENTER);
        Button btnAgree = new Button("Aceptar");
        btnAgree.getStyleClass().add("btn-red");
        btnAgree.setDefaultButton(true);
        btnAgree.setMinSize(125, 30);
        btnAgree.setOnAction(handler);

        Button btnCancel = new Button("Cancelar");
        btnCancel.setMinSize(125, 30);
        btnCancel.getStyleClass().add("btn-silver");
        btnCancel.setOnAction((t) -> {
            hideAlert();
        });

        buttonsPane.getChildren().addAll(btnCancel, btnAgree);
        pane.getChildren().addAll(view, lblSure, lblMessage, buttonsPane);
        alertPane.getChildren().add(pane);
        getChildren().add(alertPane);
    }

    public void showInsertPane() {
        getChildren().add(insertPane);
    }

    public void showUpdatePane() {
        getChildren().add(updatePane);
    }

    public void hideAlert() {
        getChildren().remove(alertPane);
    }

    public void hideInsertPane() {
        getChildren().remove(insertPane);
    }

    public void hideUpdatePane() {
        getChildren().remove(updatePane);
    }

    public void padding(double value) {
        AnchorPane.setTopAnchor(centerPane, value);
        AnchorPane.setBottomAnchor(centerPane, value);
        AnchorPane.setRightAnchor(centerPane, value);
        AnchorPane.setLeftAnchor(centerPane, value);
    }

}
