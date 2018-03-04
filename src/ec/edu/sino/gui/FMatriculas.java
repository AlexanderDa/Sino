/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.sino.gui;

import ec.edu.sino.dao.metodos.MAlumno;
import ec.edu.sino.dao.metodos.MCiclo;
import ec.edu.sino.gui.componentes.FSearchPane;
import ec.edu.sino.gui.componentes.GodPane;
import ec.edu.sino.gui.componentes.VSearchPane;
import ec.edu.sino.negocios.entidades.Alumno;
import ec.edu.sino.negocios.entidades.Ciclo;
import ec.edu.sino.negocios.entidades.MateriaAsignada;
import java.util.List;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**
 *
 * @author alexander
 */
public final class FMatriculas {

    private List<MateriaAsignada> materiaAsignadas;
    private Alumno alumno;
    private GodPane godPane;
    //Metodo
    private final MCiclo mCiclo = new MCiclo();
    private final MAlumno mAlumno = new MAlumno();

    private FSearchPane fspSearch;
    private TableView<Ciclo> table;
    private TableColumn<Ciclo, MateriaAsignada> colAsignada;
    private TableColumn<Ciclo, Alumno> colAlumno;

    private VSearchPane vsInsert;
    private TextField tfAlumno;
    private Label lblShowAlumno;

    public FMatriculas() {

    }

    public GodPane start() {

        mCiclo.loginAdmin();
        mAlumno.loginAdmin();
        godPane = new GodPane();
        godPane.init();
        insertPanel();
        showTable();
        return godPane;
    }

    private void insertPanel() {
        int width = 300;
        VBox boxInsert = new VBox(15);
        boxInsert.setMaxSize(width, 300);
        boxInsert.setPadding(new Insets(25));

        Label lblTitle = new Label("Insertar Nuevo");
        lblTitle.setAlignment(Pos.CENTER);
        lblTitle.setMinSize(width, 35);

        vsInsert = new VSearchPane();

        final Label lblAlumno = new Label("Alumno");
        tfAlumno = new TextField();
        tfAlumno.setPromptText("Cédula o Nombres");
        tfAlumno.setOnKeyReleased(tfAlumnoActionEvent());

        lblShowAlumno = new Label();
        lblShowAlumno.setMinSize(width, 30);
        lblShowAlumno.setAlignment(Pos.CENTER_LEFT);

        HBox buttonsPane = new HBox(25);
        buttonsPane.setAlignment(Pos.CENTER);
        Button btnInsertCiclo = new Button("Aceptar");
        btnInsertCiclo.getStyleClass().add("btn-green");
        btnInsertCiclo.setDefaultButton(true);
        btnInsertCiclo.setMinSize(125, 30);
        btnInsertCiclo.setOnAction(OkInsertActionEvent());

        Button btnCancel = new Button("Cancelar");
        btnCancel.setMinSize(125, 30);
        btnCancel.getStyleClass().add("btn-silver");
        btnCancel.setOnAction(NoInsertActionEvent());

        buttonsPane.getChildren().addAll(btnCancel, btnInsertCiclo);

        boxInsert.getChildren().addAll(lblTitle, vsInsert, lblAlumno, tfAlumno, lblShowAlumno, buttonsPane);
        godPane.addInsertPane(boxInsert);

    }

    private void showTable() {
        godPane.setTitle("Matricular Alumnos");
        fspSearch = new FSearchPane();

        VBox boxTable = new VBox(15);
        boxTable.setPadding(new Insets(35));
        boxTable.setAlignment(Pos.CENTER_RIGHT);

        HBox hbButtons = new HBox();
        hbButtons.setAlignment(Pos.CENTER_RIGHT);
        Button btnSearch = new Button("Buscar");
        btnSearch.getStyleClass().add("btn-green");
        btnSearch.setOnAction(btnSearchActionEvent());

        Button btnInsert = new Button("Insertar");
        btnInsert.getStyleClass().add("btn-green");
        btnInsert.setOnAction(insertActionEvent());
        hbButtons.getChildren().addAll(btnSearch, btnInsert);

        table = new TableView<>();
        VBox.setVgrow(table, Priority.ALWAYS);
        table.setEditable(true);

        colAsignada = new TableColumn<>("Curso");
        colAlumno = new TableColumn<>("Alumno");

        colAsignada.setCellValueFactory(new PropertyValueFactory<>("asignada"));
        colAlumno.setCellValueFactory(new PropertyValueFactory<>("alumno"));

        refreshTable();
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.autosize();

        table.getColumns().addAll(colAsignada, colAlumno);
        boxTable.getChildren().addAll(fspSearch, hbButtons, table);
        godPane.addCenter(boxTable);

    }

//******************************************************************************
//*                                 EVENTOS                                    *
//******************************************************************************
    private EventHandler insertActionEvent() {
        return (t) -> {
            insertPanel();
            godPane.showInsertPane();
        };
    }

    private EventHandler OkInsertActionEvent() {
        return (t) -> {

            Ciclo ciclo;

            if (vsInsert.exists() && alumno != null) {
                for (MateriaAsignada tmp : vsInsert.getListaDeMaterias()) {
                    System.out.println(tmp);
                    ciclo = new Ciclo();
                    ciclo.setAsignada(tmp);
                    ciclo.setAlumno(alumno);

                    if (ciclo.getAlumno() != null) {
                        try {
                            if (mCiclo.insertar(ciclo) > 0) {
                                godPane.successful("Insertado Correctamente");
                                refreshTable();
                                godPane.hideInsertPane();
                            }
                        } catch (Exception e) {
                            godPane.failed("Ciclo no se ha guardado");
                        }
                    }
                }
                godPane.successful("Se puede insertar");
            } else {
                godPane.failed("Campos sin llenar");
            }
        };

    }

    private EventHandler NoInsertActionEvent() {
        return (t) -> {
            godPane.hideInsertPane();
        };
    }

    private EventHandler tfAlumnoActionEvent() {
        return (t) -> {
            try {
                alumno = mAlumno.obtener(tfAlumno.getText());
                if (alumno != null) {
                    lblShowAlumno.setText("Nombre: " + alumno.toString()
                            + "\nCédula: " + alumno.getCedula());
                } else {
                    lblShowAlumno.setText("Alumno no registrado");
                }
            } catch (Exception e) {
            }
        };
    }

    private EventHandler btnSearchActionEvent() {
        return (t) -> {

            try {
                if (fspSearch.exists()) {
                    table.setItems(mCiclo.obtenerAlumnosMatriculados(fspSearch.getCurso()));
                } else {
                    godPane.failed("Docente o periodo no seleccionados");
                }
            } catch (Exception ex) {
                godPane.failed("Error al encontrar");
                System.err.println(ex.getMessage());
            }
        };
    }
//******************************************************************************
//*                                                                   *
//******************************************************************************    

    private void refreshTable() {
        try {
            table.setItems(mCiclo.obtenerAlumnosMatriculados());
            System.out.println("Refrescar");
        } catch (Exception e) {
            godPane.failed("No se ha podido refrescar la table");
            System.err.println(e.getMessage());
        }
    }
}
