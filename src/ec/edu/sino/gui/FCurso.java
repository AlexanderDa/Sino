/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.sino.gui;

import ec.edu.sino.dao.metodos.MCurso;
import ec.edu.sino.dao.metodos.MDocente;
import ec.edu.sino.dao.metodos.MMAteria;
import ec.edu.sino.dao.metodos.MPeriodo;
import ec.edu.sino.gui.componentes.CellButtons;
import ec.edu.sino.gui.componentes.GodPane;
import ec.edu.sino.negocios.entidades.Curso;
import ec.edu.sino.negocios.entidades.Docente;
import ec.edu.sino.negocios.entidades.Materia;
import ec.edu.sino.negocios.entidades.Periodo;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

/**
 *
 * @author alexander
 */
public final class FCurso {

    private GodPane godPane;
    //Metodo
    private final MCurso mc = new MCurso();
    private final MPeriodo mp = new MPeriodo();
    private final MDocente md = new MDocente();
    private final MMAteria mma = new MMAteria();

    private TableView<Curso> table;
    private TableColumn<Curso, String> colPeriodo;
    private TableColumn<Curso, String> colDocente;
    private TableColumn<Curso, String> colGrado;
    private TableColumn<Curso, String> colParalelo;
    private TableColumn colAcciones;

    private final Label lblPeriodo = new Label("Periodo");
    private final Label lblDocente = new Label("Docente");
    private final Label lblMateria = new Label("Materia");
    private final Label lblGrado = new Label("Grado");
    private final Label lblParalelo = new Label("Paralelo");

    private ComboBox<Periodo> cbInsertCursoPeriodo;
    private ComboBox<Docente> cbInsertCursoDocente;
    private TextField tfInsertCursoGrado;
    private TextField tfInsertCursoParalelo;

    private ComboBox<Periodo> cbUpdateCursoPeriodo;
    private ComboBox<Docente> cbUpdateCursoDocente;
    private TextField tfUpdateCursoParalelo;
    private TextField tfUpdateCursoGrado;

    public FCurso() {
        mc.loginAdmin();
        mp.loginAdmin();
        md.loginAdmin();
        mma.loginAdmin();
    }

    /**
     * CREACION DE GODPANEL PARA INSERTAR MODIFICAR Y ELIMINAR UN CURSO
     */
    private void insertCursoPanel() {
        int width = 300;
        VBox boxInsert = new VBox(15);
        boxInsert.setMaxSize(width, 200);
        boxInsert.setPadding(new Insets(25));

        Label lblTitle = new Label("Insertar Nuevo");
        lblTitle.setAlignment(Pos.CENTER);
        lblTitle.setMinSize(width, 35);

        cbInsertCursoPeriodo = new ComboBox<>();
        cbInsertCursoPeriodo.setMinWidth(width);

        cbInsertCursoDocente = new ComboBox<>();
        cbInsertCursoDocente.setMinWidth(width);

        tfInsertCursoGrado = new TextField();

        tfInsertCursoParalelo = new TextField();

        fullCombobox();

        HBox buttonsPane = new HBox(25);
        buttonsPane.setAlignment(Pos.CENTER);
        Button btnInsertCurso = new Button("Aceptar");
        btnInsertCurso.getStyleClass().add("btn-green");
        btnInsertCurso.setDefaultButton(true);
        btnInsertCurso.setMinSize(125, 30);
        btnInsertCurso.setOnAction(OkInsertCursoActionEvent());

        Button btnCancel = new Button("Cancelar");
        btnCancel.setMinSize(125, 30);
        btnCancel.getStyleClass().add("btn-silver");
        btnCancel.setOnAction(NoInsertCursoActionEvent());

        buttonsPane.getChildren().addAll(btnCancel, btnInsertCurso);

        boxInsert.getChildren().addAll(lblTitle,
                lblPeriodo, cbInsertCursoPeriodo,
                lblDocente, cbInsertCursoDocente,
                lblGrado, tfInsertCursoGrado,
                lblParalelo, tfInsertCursoParalelo,
                buttonsPane);
        godPane.addInsertPane(boxInsert);

    }

    private void updateCursoPanel(Curso curso) {
        int width = 300;
        VBox boxUpdate = new VBox(15);
        boxUpdate.setMaxSize(width, 300);
        boxUpdate.setPadding(new Insets(25));

        Label lblTitle = new Label("Modificar");
        lblTitle.setAlignment(Pos.CENTER);
        lblTitle.setMinSize(width, 35);

//AQUI TODOS LOS ELEMENTOS DE PARA LA MODIFICACION
        cbUpdateCursoDocente = new ComboBox<>();
        cbUpdateCursoDocente.setMinWidth(width);

        HBox buttonsPane = new HBox(25);
        buttonsPane.setAlignment(Pos.CENTER);
        Button btnInsertCurso = new Button("Aceptar");
        btnInsertCurso.getStyleClass().add("btn-green");
        btnInsertCurso.setDefaultButton(true);
        btnInsertCurso.setMinSize(125, 30);
        btnInsertCurso.setOnAction(OkUpdateCursoActionEvent(curso));

        Button btnCancel = new Button("Cancelar");
        btnCancel.setMinSize(125, 30);
        btnCancel.getStyleClass().add("btn-silver");
        btnCancel.setOnAction(NoUpdateCursoActionEvent());

        buttonsPane.getChildren().addAll(btnCancel, btnInsertCurso);

        boxUpdate.getChildren().addAll(lblTitle, lblDocente, cbUpdateCursoDocente,/*Todos los componentes aqui*/ buttonsPane);
        godPane.addUpdatePane(boxUpdate);

    }

    public GodPane create() {
        godPane = new GodPane();
        godPane.init();
        insertCursoPanel();
        godPane.setTitle("Cursos");

        VBox boxTable = new VBox(15);
        boxTable.setPadding(new Insets(35));
        boxTable.setAlignment(Pos.CENTER_RIGHT);

        Button btnInsert = new Button("Insertar");
        btnInsert.getStyleClass().add("btn-green");
        btnInsert.setOnAction(insertActionEvent());
        table = new TableView<>();
        VBox.setVgrow(table, Priority.ALWAYS);
        table.setEditable(true);

        colPeriodo = new TableColumn<>("Periodo");
        colDocente = new TableColumn<>("Docente");
        colGrado = new TableColumn<>("Grado");
        colParalelo = new TableColumn<>("Paralelo");
        colAcciones = new TableColumn("Acciones");

        colPeriodo.setCellValueFactory(new PropertyValueFactory<>("periodo"));
        colDocente.setCellValueFactory(new PropertyValueFactory<>("docente"));
        colGrado.setCellValueFactory(new PropertyValueFactory<>("grado"));
        colParalelo.setCellValueFactory(new PropertyValueFactory<>("paralelo"));

        colAcciones.setMinWidth(100);

        Callback<TableColumn<Curso, String>, TableCell<Curso, String>> cellFactory
                = (final TableColumn<Curso, String> param) -> {
                    final TableCell<Curso, String> cell = new TableCell<Curso, String>() {

                final CellButtons buttons = new CellButtons();

                @Override
                public void updateItem(String item, boolean empty) {

                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        Curso curso = getTableView().getItems().get(getIndex());

                        buttons.deleteAction(DeleteCursoAtcionEvent(curso));
                        buttons.saveAction(updateCursoActionEvent(curso));
                        setGraphic(buttons);
                        setText(null);
                    }
                }
            };
                    return cell;
                };
        colAcciones.setCellFactory(cellFactory);

        refreshTable();
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.autosize();

        table.getColumns().addAll(colPeriodo, colDocente, colGrado, colParalelo, colAcciones);
        boxTable.getChildren().addAll(btnInsert, table);
        godPane.addCenter(boxTable);
        return godPane;
    }

    /**
     * EVENTOS PARA INSERTAR MODIFICAR Y ELIMINAR UN CURSO
     */
    private EventHandler insertActionEvent() {
        return (t) -> {
            insertCursoPanel();
            godPane.showInsertPane();
        };
    }

    private EventHandler OkInsertCursoActionEvent() {
        return (t) -> {
            Curso curso = null;

            if (cbInsertCursoPeriodo.getSelectionModel().getSelectedItem() != null
                    && cbInsertCursoDocente.getSelectionModel().getSelectedItem() != null
                    && !"".equals(tfInsertCursoGrado.getText())
                    && !"".equals(tfInsertCursoParalelo.getText())) {
                curso = new Curso();
                curso.setPeriodo(cbInsertCursoPeriodo.getSelectionModel().getSelectedItem());
                curso.setDocente(cbInsertCursoDocente.getSelectionModel().getSelectedItem());
                curso.setGrado(tfInsertCursoGrado.getText());
                curso.setParalelo(tfInsertCursoParalelo.getText());

            } else {
                godPane.failed("Campos sin llenar");
            }
            if (curso != null) {
                try {
                    int insertados = 0;

                    if (mc.insertar(curso) > 0) {
                        godPane.successful("Insertado Correctamente");
                        refreshTable();
                        godPane.hideInsertPane();
                    }

                } catch (Exception e) {
                    godPane.failed("Curso no se ha guardado");
                    System.err.println(e.getMessage());
                }
            }
        };

    }

    private EventHandler NoInsertCursoActionEvent() {
        return (t) -> {
            godPane.hideInsertPane();
        };
    }

    private EventHandler OkUpdateCursoActionEvent(Curso curso) {
        return (t) -> {
            if (true/*Verificar que los componentes esten vacios*/) {
            } else {
                godPane.failed("Campos sin llenar");
            }

            if (curso != null) {
                try {
                    if (mc.modificar(curso) > 0) {
                        godPane.successful("Insertado Correctamente");
                        refreshTable();
                        godPane.hideUpdatePane();
                    }
                } catch (Exception e) {
                    godPane.failed("Curso no se ha guardado");
                }
            }
        };

    }

    private EventHandler NoUpdateCursoActionEvent() {
        return (t) -> {
            godPane.hideUpdatePane();
        };
    }

    private EventHandler updateCursoActionEvent(Curso curso) {
        return (t) -> {
            updateCursoPanel(curso);
            godPane.showUpdatePane();
        };
    }

    private EventHandler DeleteCursoAtcionEvent(Curso curso) {
        return (t) -> {
            godPane.showAlert(OkDeleteCursoAtcionEvent(curso));

        };
    }

    private EventHandler OkDeleteCursoAtcionEvent(Curso curso) {
        return (t) -> {
            try {
                if (mc.eliminar(curso) > 0) {
                    refreshTable();
                    godPane.successful("Curso eliminado.");
                }
            } catch (Exception e) {
                godPane.failed("No se puede eliminar");
            }
            godPane.hideAlert();
        };
    }

//******************************************************************************
//*                                                                   *
//******************************************************************************    
    private void refreshTable() {
        try {
            table.setItems(mc.obtener());
        } catch (Exception e) {
            godPane.failed("No se ha podido refrescar la table");
        }
    }

    private void fullCombobox() {
        try {
            cbInsertCursoPeriodo.setItems(mp.obtener());
            //cbUpdateCursoPeriodo.setItems(mp.obtener());

        } catch (Exception e) {
            System.err.println("obtener periodo: " + e.getMessage());
        }
        try {
            cbInsertCursoDocente.setItems(md.obtener());
            //cbUpdateCursoDocente.setItems(md.obtener());

        } catch (Exception e) {
            System.err.println("obtener docente: " + e.getMessage());

        }

    }
}
