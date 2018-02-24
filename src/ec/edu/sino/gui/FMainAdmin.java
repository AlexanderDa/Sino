/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.sino.gui;

import ec.edu.sino.negocios.entidades.Administrador;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
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
    private ToggleGroup group;
    private final ToggleButton btnAlumno = new ToggleButton("Alumno");
    private final ToggleButton btnCurso = new ToggleButton("Curso");
    private final ToggleButton btnDocente = new ToggleButton("Docente");
    private final ToggleButton btnMateria = new ToggleButton("Matéria");
    private final ToggleButton btnPeriodo = new ToggleButton("Periodo");
    
    private final Falumno fAlumno = new Falumno();
    private final FCurso fCurso = new FCurso();
    //private final FDocente fDocente  = new FDocente();
    private final FMateria fMateria = new FMateria();
    private final FPeriodo fPeriodo = new FPeriodo();
    
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
        taskBar = new VBox(15);
        taskBar.setPrefSize(200, USE_PREF_SIZE);
        taskBar.setAlignment(Pos.CENTER);
        taskBar.getStyleClass().add("task-bar");
        
        group = new ToggleGroup();
        btnAlumno.setToggleGroup(group);
        btnCurso.setToggleGroup(group);
        btnDocente.setToggleGroup(group);
        btnMateria.setToggleGroup(group);
        btnPeriodo.setToggleGroup(group);
        
        btnAlumno.setMinSize(200, 35);
        btnCurso.setMinSize(200, 35);
        btnDocente.setMinSize(200, 35);
        btnMateria.setMinSize(200, 35);
        btnPeriodo.setMinSize(200, 35);
        
        btnAlumno.setOnAction(btnAlumnoActionEvent());
        btnCurso.setOnAction(btnCursoActionEvent());
//        btnDocente.setOnAction(btnDocenteActionEvent());
        btnMateria.setOnAction(btnMateriaActionEvent());
        btnPeriodo.setOnAction(btnPeriodoActionEvent());
        
        taskBar.getChildren().addAll(btnAlumno, btnCurso, btnDocente, btnMateria, btnPeriodo);
        
        getChildren().addAll(taskBar, fCurso.start());
    }

    /**
     * *****************************************************************************
     * CREACION DE EVENTOS *
     * *****************************************************************************
     */
    private EventHandler btnAlumnoActionEvent() {
        return (t) -> {
            getChildren().remove(1);
            getChildren().add(fAlumno.start());
        };
    }
    
    private EventHandler btnCursoActionEvent() {
        return (t) -> {
            getChildren().remove(1);
            getChildren().add(fCurso.start());
        };
    }

//    private EventHandler btnDocenteActionEvent() {
//        return (t) -> {
//            getChildren().remove(1);
//            getChildren().add(fAlumno.start());
//        };
//    }
    private EventHandler btnMateriaActionEvent() {
        return (t) -> {
            getChildren().remove(1);
            getChildren().add(fMateria.start());
        };
    }
    
    private EventHandler btnPeriodoActionEvent() {
        return (t) -> {
            getChildren().remove(1);
            getChildren().add(fPeriodo.start());
        };
    }
}
