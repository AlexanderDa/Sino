/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.sino.gui;

import ec.edu.sino.dao.metodos.MCurso;
import ec.edu.sino.dao.metodos.MPeriodo;
import ec.edu.sino.negocios.entidades.Curso;
import ec.edu.sino.negocios.entidades.Docente;
import ec.edu.sino.negocios.entidades.Periodo;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author alexander
 */
public class FMainDocente extends AnchorPane {

    private Curso curso;
    private VBox taskBar;
    private VBox dropDownMenu;
    final StackPane wallpaper = new StackPane();
    final ImageView user = new ImageView(new Image(getClass().getResourceAsStream("imagenes/user.png")));
    private ToggleGroup group;
    private final ToggleButton btnAlumno = new ToggleButton("Alumno");

    public FMainDocente(Docente docente) {
        MCurso mCurso = new MCurso();
        mCurso.loginAdmin();
        MPeriodo mPeriodo = new MPeriodo();
        mPeriodo.loginAdmin();
        try {
            Periodo periodo  = mPeriodo.obtenerActual();
            curso = mCurso.obtenerPorDocenteAndPeriodo(docente.getCedula(), periodo.getId());
        } catch (Exception e) {
        }
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

    private void initDropDownMenu() {
        dropDownMenu = new VBox();
        dropDownMenu.toFront();
        dropDownMenu.setMinSize(200, 200);
        dropDownMenu.setStyle("-fx-background-color:red");
        AnchorPane.setTopAnchor(dropDownMenu, 10.0);
        AnchorPane.setRightAnchor(dropDownMenu, 10.0);
        dropDownMenu.toFront();

        getChildren().add(dropDownMenu);
    }

    private void initTaskBar() {
        taskBar = new VBox(15);
        taskBar.setPrefSize(200, USE_PREF_SIZE);
        taskBar.getStyleClass().add("task-bar");
        taskBar.setAlignment(Pos.TOP_CENTER);
        AnchorPane.setTopAnchor(taskBar, 0.0);
        AnchorPane.setBottomAnchor(taskBar, 0.0);

        group = new ToggleGroup();
        btnAlumno.setToggleGroup(group);

        btnAlumno.setMinSize(200, 35);

        btnAlumno.setAlignment(Pos.CENTER_LEFT);

        btnAlumno.setPadding(new Insets(5, 25, 5, 25));

        btnAlumno.setOnAction(btnCicloActionEvent());

        taskBar.getChildren().addAll(user, btnAlumno);

        wallpaper.getStyleClass().add("wallpaper");
        AnchorPane.setTopAnchor(wallpaper, 0.0);
        AnchorPane.setLeftAnchor(wallpaper, 200.0);
        AnchorPane.setRightAnchor(wallpaper, 0.0);
        AnchorPane.setBottomAnchor(wallpaper, 0.0);
        getChildren().addAll(taskBar, wallpaper);
    }

    /**
     * *****************************************************************************
     * CREACION DE EVENTOS *
     * ****************************************************************************
     */
    private EventHandler btnCicloActionEvent() {
        return (t) -> {

            getChildren().remove(1);
            getChildren().add(new FCiclo(curso).start());
        };
    }

}
