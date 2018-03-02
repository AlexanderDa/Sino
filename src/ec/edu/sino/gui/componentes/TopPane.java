/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.sino.gui.componentes;

import javafx.beans.InvalidationListener;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

/**
 *
 * @author alexander
 */
public class TopPane extends HBox {
    
    private final ImageView ivSearch = new ImageView();
    private final ImageView ivInsert = new ImageView();
    private final TextField field = new TextField();
    private final ToggleButton btnSearch = new ToggleButton();
    private final Button btnInsert = new Button();
    
    public TopPane() {
        init();
    }
    
    public TopPane(double spacing) {
        super(spacing);
        init();
    }
    
    private void init() {
        setAlignment(Pos.CENTER_RIGHT);
        ivInsert.setImage(new Image(getClass().getResourceAsStream("imagenes/add.png")));
        ivSearch.setImage(new Image(getClass().getResourceAsStream("imagenes/search.png")));
        
        HBox hbButtons = new HBox();
        btnInsert.setGraphic(ivInsert);
        btnSearch.setGraphic(ivSearch);
        
        btnInsert.setMinSize(40, 35);
        btnSearch.setMinSize(40, 35);
        field.setMinHeight(35);
        
        
        btnInsert.getStyleClass().add("right-pill");
        btnSearch.getStyleClass().add("left-pill");
        
        btnSearch.setOnAction(showField());
        field.focusedProperty().addListener(hideField());
        
        hbButtons.getChildren().addAll(btnSearch, btnInsert);
        
        HBox.setHgrow(field, Priority.ALWAYS);
        getChildren().addAll(hbButtons);
        
    }
    
    private EventHandler showField() {
        return (t) -> {
            if (!btnSearch.isSelected()) {
                getChildren().remove(field);
            } else {
                getChildren().add(0, field);
            }
        };
    }
    
    private InvalidationListener hideField() {
        return (t) -> {
            if (!field.isFocused()) {
                getChildren().remove(field);
                btnSearch.setSelected(false);
            }
        };
    }
    
    public void setOnActionInsert(EventHandler handler) {
        btnInsert.setOnAction(handler);
    }
    
    public void setOnActionSearch(EventHandler handler) {
        field.setOnKeyReleased(handler);
    }
    
    public String getText() {
        return field.getText();
    }
    
    public void setPromtText(String text) {
        field.setPromptText(text);
    }

    public void addButtonText(String text) {
        btnInsert.setText(text);
    }
    
}
