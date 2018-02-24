/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.sino.gui;

import ec.edu.sino.dao.metodos.MMAteria;
import ec.edu.sino.gui.componentes.CellButtons;
import ec.edu.sino.gui.componentes.GodPane;
import ec.edu.sino.negocios.entidades.Materia;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
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
public final class FMateria {

    private int id;
    private GodPane godPane;
    //Metodo
    private final MMAteria mma = new MMAteria();

    private TableView<Materia> table;
    private TableColumn<Materia, String> colNombre;
    private TableColumn<Materia, String> colDominio;

    private TableColumn colAcciones;

//DECLARACION DE LOS COMPONENTES PARA LA INSERCION Y MODIFICACION
    private final Label lblNombre = new Label("Nombre");
    private final Label lblDominio = new Label("Domínio");

    private TextField tfInsertNombre;
    private TextField tfInsertDominio;

    private TextField tfUpdateNombre;
    private TextField tfUpdateDominio;

    public FMateria() {

    }

    public GodPane start() {

        mma.loginAdmin();
        godPane = new GodPane();
        godPane.init();
        insertPanel();
        showTable();
        return godPane;
    }

    private void insertPanel() {
        int width = 300;
        VBox boxInsert = new VBox(15);
        boxInsert.setMaxSize(width, 200);
        boxInsert.setPadding(new Insets(25));

        Label lblTitle = new Label("Insertar Nuevo");
        lblTitle.setAlignment(Pos.CENTER);
        lblTitle.setMinSize(width, 35);

        tfInsertNombre = new TextField();
        tfInsertDominio = new TextField();

        HBox buttonsPane = new HBox(25);
        buttonsPane.setAlignment(Pos.CENTER);
        Button btnInsertMateria = new Button("Aceptar");
        btnInsertMateria.getStyleClass().add("btn-green");
        btnInsertMateria.setDefaultButton(true);
        btnInsertMateria.setMinSize(125, 30);
        btnInsertMateria.setOnAction(OkInsertActionEvent());

        Button btnCancel = new Button("Cancelar");
        btnCancel.setMinSize(125, 30);
        btnCancel.getStyleClass().add("btn-silver");
        btnCancel.setOnAction(NoInsertActionEvent());

        buttonsPane.getChildren().addAll(btnCancel, btnInsertMateria);

        boxInsert.getChildren().addAll(lblTitle, lblNombre, tfInsertNombre, lblDominio, tfInsertDominio, buttonsPane);
        godPane.addInsertPane(boxInsert);

    }

    private void updatePanel(Materia materia) {
        int width = 300;
        VBox boxUpdate = new VBox(15);
        boxUpdate.setMaxSize(width, 200);
        boxUpdate.setPadding(new Insets(25));

        Label lblTitle = new Label("Modificar");
        lblTitle.setAlignment(Pos.CENTER);
        lblTitle.setMinSize(width, 35);
        HBox.setHgrow(lblTitle, Priority.ALWAYS);

        tfUpdateNombre = new TextField(materia.getNombre());
        tfUpdateDominio = new TextField(materia.getDominio());

        HBox buttonsPane = new HBox(25);
        buttonsPane.setAlignment(Pos.CENTER);
        Button btnInsertMateria = new Button("Aceptar");
        btnInsertMateria.getStyleClass().add("btn-green");
        btnInsertMateria.setDefaultButton(true);
        btnInsertMateria.setMinSize(125, 30);
        btnInsertMateria.setOnAction(OkUpdateActionEvent(materia));

        Button btnCancel = new Button("Cancelar");
        btnCancel.setMinSize(125, 30);
        btnCancel.getStyleClass().add("btn-silver");
        btnCancel.setOnAction(NoUpdateActionEvent());

        buttonsPane.getChildren().addAll(btnCancel, btnInsertMateria);

        boxUpdate.getChildren().addAll(lblTitle, lblNombre, tfUpdateNombre, lblDominio, tfUpdateDominio, buttonsPane);
        godPane.addUpdatePane(boxUpdate);

    }

    private void showTable() {
        godPane.setTitle("Materias");

        VBox boxTable = new VBox(15);
        boxTable.setPadding(new Insets(35));
        boxTable.setAlignment(Pos.CENTER_RIGHT);

        Button btnInsert = new Button("Insertar");
        btnInsert.getStyleClass().add("btn-green");
        btnInsert.setOnAction(insertActionEvent());
        table = new TableView<>();
        VBox.setVgrow(table, Priority.ALWAYS);
        table.setEditable(true);

        colNombre = new TableColumn<>("Nombre");
        colDominio = new TableColumn<>("Dominio");

        colAcciones = new TableColumn("Acciones");

        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colDominio.setCellValueFactory(new PropertyValueFactory<>("dominio"));

        colAcciones.setMinWidth(100);

        Callback<TableColumn<Materia, String>, TableCell<Materia, String>> cellFactory
                = (final TableColumn<Materia, String> param) -> {
                    final TableCell<Materia, String> cell = new TableCell<Materia, String>() {

                final CellButtons buttons = new CellButtons();

                @Override
                public void updateItem(String item, boolean empty) {

                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        Materia materia = getTableView().getItems().get(getIndex());
                        id = materia.getId();

                        buttons.deleteAction(DeleteAtcionEvent(materia));
                        buttons.saveAction(updateActionEvent(materia));
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

        table.getColumns().addAll(colNombre, colDominio, colAcciones);
        boxTable.getChildren().addAll(btnInsert, table);
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
            Materia materia = null;
            if (!"".equals(tfInsertNombre.getText()) && !"".equals(tfInsertDominio.getText())) {
                materia = new Materia();
                materia.setNombre(tfInsertNombre.getText());
                materia.setDominio(tfInsertDominio.getText());
            } else {
                godPane.failed("Campos sin llenar");
            }
            if (materia != null) {
                try {
                    if (mma.insertar(materia) > 0) {
                        godPane.successful("Insertado Correctamente");
                        refreshTable();
                        godPane.hideInsertPane();
                    }
                } catch (Exception e) {
                    godPane.failed("Materia no se ha guardado");
                }
            }
        };

    }

    private EventHandler NoInsertActionEvent() {
        return (t) -> {
            godPane.hideInsertPane();
        };
    }

    private EventHandler OkUpdateActionEvent(Materia materia) {
        return (t) -> {
            if (!"".equals(tfUpdateNombre.getText()) && !"".equals(tfUpdateDominio.getText())) {
                materia.setNombre(tfUpdateNombre.getText());
                materia.setDominio(tfUpdateDominio.getText());
            } else {
                godPane.failed("Campos sin llenar");
            }

            if (materia != null) {
                try {
                    if (mma.modificar(materia) > 0) {
                        godPane.successful("Insertado Correctamente");
                        refreshTable();
                        godPane.hideUpdatePane();
                    }
                } catch (Exception e) {
                    godPane.failed("Materia no se ha guardado");
                }
            }
        };

    }

    private EventHandler NoUpdateActionEvent() {
        return (t) -> {
            godPane.hideUpdatePane();
        };
    }

    private EventHandler updateActionEvent(Materia materia) {
        return (t) -> {
            updatePanel(materia);
            godPane.showUpdatePane();
        };
    }

    private EventHandler DeleteAtcionEvent(Materia materia) {
        return (t) -> {
            godPane.showAlert(OkDeleteAtcionEvent(materia));

        };
    }

    private EventHandler OkDeleteAtcionEvent(Materia materia) {
        return (t) -> {
            try {
                if (mma.eliminar(materia) > 0) {
                    refreshTable();
                    godPane.successful("Materia eliminado.");
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
            table.setItems(mma.obtener());
        } catch (Exception e) {
            godPane.failed("No se ha podido refrescar la table");
        }
    }
}
