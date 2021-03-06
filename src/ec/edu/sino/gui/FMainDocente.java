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
    private final ToggleButton btnCiclo = new ToggleButton("Ciclo");
    private final ToggleButton btnQuimestre = new ToggleButton("Quimestre");
    private final ToggleButton btnParcial = new ToggleButton("Parcial");

    public FMainDocente(Docente docente) {
        MCurso mCurso = new MCurso();
        mCurso.loginAdmin();
        MPeriodo mPeriodo = new MPeriodo();
        mPeriodo.loginAdmin();
        try {
            Periodo periodo = mPeriodo.obtenerActual();
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
        btnCiclo.setToggleGroup(group);
        btnQuimestre.setToggleGroup(group);
        btnParcial.setToggleGroup(group);

        btnCiclo.setMinSize(200, 35);
        btnQuimestre.setMinSize(200, 35);
        btnParcial.setMinSize(200, 35);

        btnCiclo.setAlignment(Pos.CENTER_LEFT);
        btnQuimestre.setAlignment(Pos.CENTER_LEFT);
        btnParcial.setAlignment(Pos.CENTER_LEFT);

        btnCiclo.setPadding(new Insets(5, 25, 5, 25));
        btnQuimestre.setPadding(new Insets(5, 25, 5, 25));
        btnParcial.setPadding(new Insets(5, 25, 5, 25));

        btnCiclo.setOnAction(btnCicloActionEvent());
        btnQuimestre.setOnAction(btnQuimestreActionEvent());
        btnParcial.setOnAction(btnParcialActionEvent());

        taskBar.getChildren().addAll(user, btnCiclo, btnQuimestre,btnParcial);

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

    private EventHandler btnQuimestreActionEvent() {
        return (t) -> {
            getChildren().remove(1);
            getChildren().add(new FQuimestre(curso).start());
        };
    }

    private EventHandler btnParcialActionEvent() {
        return (t) -> {
            getChildren().remove(1);
            getChildren().add(new FParcial(curso).start());
        };
    }

}
