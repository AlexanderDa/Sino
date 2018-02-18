/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.sino.gui.componentes;

import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

/**
 *
 * @author alexander
 */
public class Notification extends Label {

    private boolean upperCorner;

    public Notification() {
        upperCorner = true;
    }

    public void failed(String mensaje) {
        setText(mensaje);
        if (getStyleClass().size() > 1) {
            getStyleClass().remove(1);
        }
        getStyleClass().add("failed");
        start();
    }

    public void successful(String mensaje) {
        setText(mensaje);
        if (getStyleClass().size() > 1) {
            getStyleClass().remove(1);
        }
        getStyleClass().add("successful");
        start();
    }

    private void start() {
        if (upperCorner) {
            rightUpperCorner();
        } else {
            lowerRightCorner();
        }
        TranslateTransition salida = new TranslateTransition(Duration.seconds(0.5), this);
        salida.setFromX(0);
        salida.setToX(-(8.28 * getText().length() + 22));

        PauseTransition pausa = new PauseTransition(Duration.seconds(3));

        TranslateTransition entrada = new TranslateTransition(Duration.seconds(0.5), this);
        entrada.setFromX(-(8.28 * getText().length() + 22));
        entrada.setToX(0);

        SequentialTransition animacion = new SequentialTransition(salida, pausa, entrada);
        animacion.play();

    }

    public void rightUpperCorner() {
        AnchorPane.setTopAnchor(this, 10.0);
        AnchorPane.setRightAnchor(this, -(8.28 * getText().length() + 12));
        upperCorner=true;
    }

    public void lowerRightCorner() {
        AnchorPane.setBottomAnchor(this, 10.0);
        AnchorPane.setRightAnchor(this, -(8.28 * getText().length() + 12));
        upperCorner=false;
    }

}
