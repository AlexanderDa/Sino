package ec.edu.sino.gui.componentes;

import javafx.beans.InvalidationListener;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author alexander
 */
public class ValidatorField extends TextField {

    private float value;

    public ValidatorField() {
        getStyleClass().remove(1);
        setStyle("-fx-background-color: transparent;");
        setOnKeyReleased(validatorActionEvent());
    }

    private EventHandler validatorActionEvent() {
        return (t) -> {
            try {
                if (getText().length() <= 5 && Float.parseFloat(getText()) >= 0 && Float.parseFloat(getText()) <= 10) {
                    value = Float.parseFloat(getText());
                    setStyle("-fx-background-color: transparent;");
                } else {
                    value = Float.parseFloat("");
                }

            } catch (NumberFormatException e) {
                setStyle("-fx-background-color: red; -fx-text-fill: white;");
            }
        };
    }

    public float getValue() {

        return value;
    }

}
