/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.sino.gui;

import ec.edu.sino.dao.metodos.MPeriodo;
import ec.edu.sino.gui.componentes.CellButtons;
import ec.edu.sino.gui.componentes.GodPane;
import ec.edu.sino.gui.componentes.SearchPane;
import ec.edu.sino.negocios.entidades.Periodo;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

/**
 *
 * @author alexander
 */
public final class FAsignarMaterias {

    private int id;
    private GodPane godPane;
    //Metodo
    private final MPeriodo mp = new MPeriodo();

    private TableView<Periodo> table;
    private TableColumn<Periodo, String> colName;

    private TableColumn colAcciones;
    private RadioButton forID;
    private RadioButton forName;
    private TextField tfSearchDocente;
    private SearchPane spBuscar;

//DECLARACION DE LOS COMPONENTES PARA LA INSERCION Y MODIFICACION
    public FAsignarMaterias() {

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

        Label lblTitle = new Label("Insertar Nuevo");
        lblTitle.setAlignment(Pos.CENTER);
        lblTitle.setMinSize(width, 35);

        //AQUI TODOS LOS ELEMENTOS DE PARA LA INSERCION
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

        boxInsert.getChildren().addAll(lblTitle,/*Todos los elementos aqui*/ buttonsPane);
        godPane.addInsertPane(boxInsert);

    }

    private void updatePanel(Periodo periodo) {
        int width = 300;
        VBox boxUpdate = new VBox(15);
        boxUpdate.setMaxSize(width, 300);
        boxUpdate.setPadding(new Insets(25));

        Label lblTitle = new Label("Modificar");
        lblTitle.setAlignment(Pos.CENTER);
        lblTitle.setMinSize(width, 35);

//AQUI TODOS LOS ELEMENTOS DE PARA LA MODIFICACION
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

        boxUpdate.getChildren().addAll(lblTitle,/*Todos los componentes aqui*/ buttonsPane);
        godPane.addUpdatePane(boxUpdate);

    }

    private void showTable() {
        godPane.setTitle("Materias por curso");

        VBox boxTable = new VBox(15);
        boxTable.setPadding(new Insets(35));
        boxTable.setAlignment(Pos.CENTER_RIGHT);
        spBuscar = new SearchPane();

        // tfSearchDocente.setOnKeyReleased(SearchActionEvent());
        Button btnInsert = new Button("Insertar");
        btnInsert.getStyleClass().add("btn-green");
        btnInsert.setOnAction(insertActionEvent());

        table = new TableView<>();
        VBox.setVgrow(table, Priority.ALWAYS);
        table.setEditable(true);

        colName = new TableColumn<>("");

        colAcciones = new TableColumn("Acciones");

        colName.setCellValueFactory(new PropertyValueFactory<>(""));

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
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.autosize();

        table.getColumns().addAll(/*Todas las columnas*/colAcciones);
        boxTable.getChildren().addAll(spBuscar, btnInsert, table);
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
            if (true/*Comprobar si los componentes estan llenos*/) {
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
            if (true/*Verificar que los componentes esten vacios*/) {
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
            table.setItems(mp.obtener());
        } catch (Exception e) {
            godPane.failed("No se ha podido refrescar la table");
        }
    }
}
