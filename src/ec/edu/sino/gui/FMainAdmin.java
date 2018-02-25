/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.sino.gui;

import ec.edu.sino.negocios.entidades.Administrador;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**
 *
 * @author alexander
 */
public class FMainAdmin extends HBox {

    private final Administrador admin;
    private VBox taskBar;
    final ImageView user = new ImageView(new Image(getClass().getResourceAsStream("imagenes/user.png")));
    private ToggleGroup group;
    private final ToggleButton btnAlumno = new ToggleButton("Alumno");
    private final ToggleButton btnCurso = new ToggleButton("Curso");
    private final ToggleButton btnCrearCurso = new ToggleButton("Crear");
    private final ToggleButton btnAsignarMaterias = new ToggleButton("Materias");
    private final ToggleButton btnMatricular = new ToggleButton("Matriculas");
    private final ToggleButton btnDocente = new ToggleButton("Docente");
    private final ToggleButton btnMateria = new ToggleButton("Matéria");
    private final ToggleButton btnPeriodo = new ToggleButton("Periodo");

    private final Falumno fAlumno = new Falumno();
    private final FCurso fCurso = new FCurso();
    private final FAsignarMaterias fAsignarMaterias = new FAsignarMaterias();
    private final FMatriculas fMatriculas = new FMatriculas();
    //private final FDocente fDocente  = new FDocente();
    private final FMateria fMateria = new FMateria();
    private final FPeriodo fPeriodo = new FPeriodo();
    private VBox cursoChildrems;

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
        taskBar.getStyleClass().add("task-bar");
        taskBar.setAlignment(Pos.TOP_CENTER);

        group = new ToggleGroup();
        btnAlumno.setToggleGroup(group);
        btnCurso.setToggleGroup(group);
        btnCrearCurso.setToggleGroup(group);
        btnAsignarMaterias.setToggleGroup(group);
        btnMatricular.setToggleGroup(group);
        btnDocente.setToggleGroup(group);
        btnMateria.setToggleGroup(group);
        btnPeriodo.setToggleGroup(group);

        btnAlumno.setMinSize(200, 35);
        btnCurso.setMinSize(200, 35);
        btnCrearCurso.setMinSize(200, 35);
        btnAsignarMaterias.setMinSize(200, 35);
        btnMatricular.setMinSize(200, 35);
        btnDocente.setMinSize(200, 35);
        btnMateria.setMinSize(200, 35);
        btnPeriodo.setMinSize(200, 35);

        btnAlumno.setAlignment(Pos.CENTER_LEFT);
        btnCurso.setAlignment(Pos.CENTER_LEFT);
        btnCrearCurso.setAlignment(Pos.CENTER_LEFT);
        btnAsignarMaterias.setAlignment(Pos.CENTER_LEFT);
        btnMatricular.setAlignment(Pos.CENTER_LEFT);
        btnDocente.setAlignment(Pos.CENTER_LEFT);
        btnMateria.setAlignment(Pos.CENTER_LEFT);
        btnPeriodo.setAlignment(Pos.CENTER_LEFT);

        btnAlumno.setPadding(new Insets(5, 25, 5, 25));
        btnCurso.setPadding(new Insets(5, 25, 5, 25));
        btnCrearCurso.setPadding(new Insets(5, 25, 5, 50));
        btnAsignarMaterias.setPadding(new Insets(5, 25, 5, 50));
        btnMatricular.setPadding(new Insets(5, 25, 5, 50));
        btnDocente.setPadding(new Insets(5, 25, 5, 25));
        btnMateria.setPadding(new Insets(5, 25, 5, 25));
        btnPeriodo.setPadding(new Insets(5, 25, 5, 25));

        cursoChildrems = new VBox(10);
        VBox.setVgrow(btnCrearCurso, Priority.ALWAYS);
        cursoChildrems.getChildren().addAll(btnCrearCurso, btnAsignarMaterias, btnMatricular);

        btnAlumno.setOnAction(btnAlumnoActionEvent());
        btnCurso.setOnAction(btnCursoActionEvent());
        btnCrearCurso.setOnAction(btnCrearCursoActionEvent());
        btnAsignarMaterias.setOnAction(btnAsignarMateriasActionEvent());
        btnMatricular.setOnAction(btnMatriculasActionEvent());
//        btnDocente.setOnAction(btnDocenteActionEvent());
        btnMateria.setOnAction(btnMateriaActionEvent());
        btnPeriodo.setOnAction(btnPeriodoActionEvent());

        taskBar.getChildren().addAll(user, btnAlumno, btnCurso, btnDocente, btnMateria, btnPeriodo);

        getChildren().addAll(taskBar, fAsignarMaterias.start());
    }

    /**
     * *****************************************************************************
     * CREACION DE EVENTOS *
     * ****************************************************************************
     */
    private EventHandler btnAlumnoActionEvent() {
        return (t) -> {
            cursoChildremsShow();
            getChildren().remove(1);
            getChildren().add(fAlumno.start());
        };
    }

    private EventHandler btnCursoActionEvent() {
        return (t) -> {
            cursoChildremsShow();
        };
    }

    private EventHandler btnCrearCursoActionEvent() {
        return (t) -> {
            getChildren().remove(1);
            getChildren().add(fCurso.create());
        };
    }

    private EventHandler btnAsignarMateriasActionEvent() {
        return (t) -> {
            getChildren().remove(1);
            getChildren().add(fAsignarMaterias.start());
        };
    }

    private EventHandler btnMatriculasActionEvent() {
        return (t) -> {
            getChildren().remove(1);
            getChildren().add(fMatriculas.start());
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
            cursoChildremsShow();
            getChildren().remove(1);
            getChildren().add(fMateria.start());
        };
    }

    private EventHandler btnPeriodoActionEvent() {
        return (t) -> {
            cursoChildremsShow();
            getChildren().remove(1);
            getChildren().add(fPeriodo.start());
        };
    }

    /**
     * *****************************************************************************
     * CREACION DE FUNCIONES *
     * ****************************************************************************
     */
    public void cursoChildremsShow() {
        if (btnCurso.isSelected()) {
            taskBar.getChildren().add(3, cursoChildrems);
        } else {
            taskBar.getChildren().remove(cursoChildrems);
        }
    }
}
