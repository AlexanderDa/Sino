/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.sino.gui;

import ec.edu.sino.dao.metodos.MPeriodo;
import ec.edu.sino.gui.componentes.CellButtons;
import ec.edu.sino.gui.componentes.GodPane;
import ec.edu.sino.negocios.entidades.Periodo;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

/**
 *
 * @author alexander
 */
public final class FPeriodo {

    private int id;
    private GodPane godPane;
    private final MPeriodo mp = new MPeriodo();

    private TableView<Periodo> tabla;
    private TableColumn<Periodo, String> colFechaInicio;
    private TableColumn<Periodo, String> colFechaFin;
    private TableColumn<Periodo, String> colDirector;
    private TableColumn<Periodo, String> colSubdirector;
    private TableColumn<Periodo, String> colCoordinador;
    private TableColumn colAcciones;

    private DatePicker dpFechaInicio;
    private DatePicker dpFechaFin;
    private TextField tfDirector;
    private TextField tfSubdirector;
    private TextField tfCoordinador;

    private DatePicker dpUpdateFechaInicio;
    private DatePicker dpUpdateFechaFin;
    private TextField tfUpdateDirector;
    private TextField tfUpdateSubdirector;
    private TextField tfUpdateCoordinador;

    public FPeriodo() {

    }

    public GodPane start() {

        mp.loginAdmin();
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

        Label lblFechaInicio = new Label("Fecha de inicio");
        dpFechaInicio = new DatePicker();
        dpFechaInicio.setMinWidth(width);

        Label lblFechaFin = new Label("Fecha de finalización");
        dpFechaFin = new DatePicker();
        dpFechaFin.setMinWidth(width);

        Label lblDirector = new Label("Director");
        tfDirector = new TextField();

        Label lblSubdirector = new Label("Subdirector");
        tfSubdirector = new TextField();

        Label lblCoordinador = new Label("Coordinador");
        tfCoordinador = new TextField();

        HBox buttonsPane = new HBox(25);
        buttonsPane.setAlignment(Pos.CENTER);
        Button btnInsertPeriodo = new Button("Aceptar");
        btnInsertPeriodo.getStyleClass().add("btn-green");
        btnInsertPeriodo.setDefaultButton(true);
        btnInsertPeriodo.setMinSize(125, 30);
        btnInsertPeriodo.setOnAction(OkInsertActionEvent());

        Button btnCancel = new Button("Cancelar");
        btnCancel.setMinSize(125, 30);
        btnCancel.getStyleClass().add("btn-silver");
        btnCancel.setOnAction(NoInsertActionEvent());

        buttonsPane.getChildren().addAll(btnCancel, btnInsertPeriodo);

        boxInsert.getChildren().addAll(lblFechaInicio, dpFechaInicio, lblFechaFin, dpFechaFin,
                lblDirector, tfDirector, lblSubdirector, tfSubdirector, lblCoordinador, tfCoordinador, buttonsPane);
        godPane.addInsertPane(boxInsert);

    }

    private void updatePanel(Periodo periodo) {
        int width = 300;
        VBox boxUpdate = new VBox(15);
        boxUpdate.setMaxSize(width, 300);
        boxUpdate.setPadding(new Insets(25));

        Label lblFechaInicio = new Label("Fecha de inicio");
        dpUpdateFechaInicio = new DatePicker();
        dpUpdateFechaInicio.setMinWidth(width);
        // dpUpdateFechaInicio.getEditor().setText(periodo.getFechaInicio().toString());

        Label lblFechaFin = new Label("Fecha de finalización");
        dpUpdateFechaFin = new DatePicker();
        dpUpdateFechaFin.setMinWidth(width);
        //dpUpdateFechaFin.getEditor().setText(periodo.getFechaFin().toString());

        Label lblDirector = new Label("Director");
        tfUpdateDirector = new TextField(periodo.getDirector());

        Label lblSubdirector = new Label("Subdirector");
        tfUpdateSubdirector = new TextField(periodo.getSubdirector());

        Label lblCoordinador = new Label("Coordinador");
        tfUpdateCoordinador = new TextField(periodo.getCoordinador());

        HBox buttonsPane = new HBox(25);
        buttonsPane.setAlignment(Pos.CENTER);
        Button btnInsertPeriodo = new Button("Aceptar");
        btnInsertPeriodo.getStyleClass().add("btn-green");
        btnInsertPeriodo.setDefaultButton(true);
        btnInsertPeriodo.setMinSize(125, 30);
        btnInsertPeriodo.setOnAction(OkUpdateActionEvent(periodo));

        Button btnCancel = new Button("Cancelar");
        btnCancel.setMinSize(125, 30);
        btnCancel.getStyleClass().add("btn-silver");
        btnCancel.setOnAction(NoUpdateActionEvent());

        buttonsPane.getChildren().addAll(btnCancel, btnInsertPeriodo);

        boxUpdate.getChildren().addAll(lblFechaInicio, dpUpdateFechaInicio, lblFechaFin, dpUpdateFechaFin,
                lblDirector, tfUpdateDirector, lblSubdirector, tfUpdateSubdirector, lblCoordinador, tfUpdateCoordinador, buttonsPane);
        godPane.addUpdatePane(boxUpdate);

    }

    private void showTable() {
        godPane.setTitle("Periodo Academico");

        VBox boxTable = new VBox(15);
        boxTable.setPadding(new Insets(35));
        boxTable.setAlignment(Pos.CENTER_RIGHT);

        Button btnInsert = new Button("Insertar");
        btnInsert.getStyleClass().add("btn-green");
        btnInsert.setOnAction(insertActionEvent());
        tabla = new TableView<>();
        VBox.setVgrow(tabla, Priority.ALWAYS);
        tabla.setEditable(true);

        colFechaInicio = new TableColumn<>("Fecha de Apertura");
        colFechaFin = new TableColumn<>("Fecha de Cierre");
        colDirector = new TableColumn<>("Director");
        colSubdirector = new TableColumn<>("Subdirector");
        colCoordinador = new TableColumn<>("Coordinador");
        colAcciones = new TableColumn("Acciones");

        colFechaInicio.setCellValueFactory(new PropertyValueFactory<>("fechaInicio"));
        colFechaFin.setCellValueFactory(new PropertyValueFactory<>("fechaFin"));
        colDirector.setCellValueFactory(new PropertyValueFactory<>("director"));
        colSubdirector.setCellValueFactory(new PropertyValueFactory<>("subdirector"));
        colCoordinador.setCellValueFactory(new PropertyValueFactory<>("coordinador"));

        colAcciones.setMinWidth(100);

        Callback<TableColumn<Periodo, String>, TableCell<Periodo, String>> cellFactory
                = (final TableColumn<Periodo, String> param) -> {
                    final TableCell<Periodo, String> cell = new TableCell<Periodo, String>() {

                final CellButtons buttons = new CellButtons();

                @Override
                public void updateItem(String item, boolean empty) {

                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        Periodo periodo = getTableView().getItems().get(getIndex());
                        id = periodo.getId();

                        buttons.deleteAction(DeleteAtcionEvent(periodo));
                        buttons.saveAction(updateActionEvent(periodo));
                        setGraphic(buttons);
                        setText(null);
                    }
                }
            };
                    return cell;
                };
        colAcciones.setCellFactory(cellFactory);

        refreshTable();
        tabla.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tabla.autosize();

        tabla.getColumns().addAll(colFechaInicio, colFechaFin, colDirector, colSubdirector, colCoordinador, colAcciones);
        boxTable.getChildren().addAll(btnInsert, tabla);
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
            Periodo periodo = null;
            if (!"".equals(dpFechaInicio.getEditor().getText())
                    && !"".equals(dpFechaFin.getEditor().getText())
                    && !"".equals(tfDirector.getText())
                    && !"".equals(tfSubdirector.getText())
                    && !"".equals(tfCoordinador.getText())) {
                periodo = new Periodo();
                periodo.setFechaInicio(dpFechaInicio.getValue().toString());
                periodo.setFechaFin(dpFechaFin.getValue().toString());
                periodo.setDirector(tfDirector.getText());
                periodo.setSubdirector(tfSubdirector.getText());
                periodo.setCoordinador(tfCoordinador.getText());
            } else {
                godPane.failed("Campos sin llenar");
            }
            if (periodo != null) {
                try {
                    if (mp.insertar(periodo) > 0) {
                        godPane.successful("Insertado Correctamente");
                        refreshTable();
                        godPane.hideInsertPane();
                    }
                } catch (Exception e) {
                    godPane.failed("Periodo no se ha guardado");
                }
            }
        };

    }

    private EventHandler NoInsertActionEvent() {
        return (t) -> {
            godPane.hideInsertPane();
        };
    }

    private EventHandler OkUpdateActionEvent(Periodo periodo) {
        return (t) -> {
            if (!"".equals(dpUpdateFechaInicio.getEditor().getText())
                    && !"".equals(dpUpdateFechaFin.getEditor().getText())
                    && !"".equals(tfUpdateDirector.getText())
                    && !"".equals(tfUpdateSubdirector.getText())
                    && !"".equals(tfUpdateCoordinador.getText())) {
                periodo.setFechaInicio(dpUpdateFechaInicio.getValue().toString());
                periodo.setFechaFin(dpUpdateFechaFin.getValue().toString());
                periodo.setDirector(tfUpdateDirector.getText());
                periodo.setSubdirector(tfUpdateSubdirector.getText());
                periodo.setCoordinador(tfUpdateCoordinador.getText());
            } else {
                godPane.failed("Campos sin llenar");
            }

            if (periodo != null) {
                try {
                    if (mp.modificar(periodo) > 0) {
                        godPane.successful("Insertado Correctamente");
                        refreshTable();
                        godPane.hideUpdatePane();
                    }
                } catch (Exception e) {
                    godPane.failed("Periodo no se ha guardado");
                }
            }
        };

    }

    private EventHandler NoUpdateActionEvent() {
        return (t) -> {
            godPane.hideUpdatePane();
        };
    }

    private EventHandler updateActionEvent(Periodo periodo) {
        return (t) -> {
            updatePanel(periodo);
            godPane.showUpdatePane();
        };
    }

    private EventHandler DeleteAtcionEvent(Periodo periodo) {
        return (t) -> {
            godPane.showAlert(OkDeleteAtcionEvent(periodo));

        };
    }

    private EventHandler OkDeleteAtcionEvent(Periodo periodo) {
        return (t) -> {
            try {
                if (mp.eliminar(periodo) > 0) {
                    refreshTable();
                    godPane.successful("Periodo eliminado.");
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
            tabla.setItems(mp.obtener());
        } catch (Exception e) {
            godPane.failed("No se ha podido refrescar la tabla");
        }
    }
}
