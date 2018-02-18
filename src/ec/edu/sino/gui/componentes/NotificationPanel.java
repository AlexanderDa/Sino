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
public class NotificationPanel extends AnchorPane {

    private final ImageView view = new ImageView(new Image(getClass().getResourceAsStream("imagenes/exclamacion.png")));
    private Label lblTitle;
    private StackPane centePane;
    private StackPane alertPane;
    private Notification notification;

    public NotificationPanel() {
        init();
    }

    private void init() {

        HBox.setHgrow(this, Priority.ALWAYS);
        centePane = new StackPane();
        AnchorPane.setTopAnchor(centePane, 0.0);
        AnchorPane.setRightAnchor(centePane, 0.0);
        AnchorPane.setBottomAnchor(centePane, 0.0);
        AnchorPane.setLeftAnchor(centePane, 0.0);

        alertPane = new StackPane();
        AnchorPane.setTopAnchor(alertPane, -45.0);
        AnchorPane.setRightAnchor(alertPane, 0.0);
        AnchorPane.setBottomAnchor(alertPane, 0.0);
        AnchorPane.setLeftAnchor(alertPane, -200.0);

        notification = new Notification();
        //mnotification.rightUpperCorner();
        notification.toFront();
        getChildren().addAll(centePane, notification);

    }

    public void add(Node node) {
        centePane.getChildren().add(node);
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
        AnchorPane.setTopAnchor(centePane, 25.0);
        getChildren().add(lblTitle);

    }

    public void showAlert(EventHandler handler) {
        alertPane.getStyleClass().add("alert-pane");

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

    public void hideAlert() {
        getChildren().remove(alertPane);
    }

    public void padding(double value) {
        AnchorPane.setTopAnchor(centePane, value);
        AnchorPane.setBottomAnchor(centePane, value);
        AnchorPane.setRightAnchor(centePane, value);
        AnchorPane.setLeftAnchor(centePane, value);
    }

}
