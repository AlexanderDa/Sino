/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.sino.gui;

import ec.edu.sino.dao.metodos.MDocente;
import ec.edu.sino.gui.componentes.CellButtons;
import ec.edu.sino.gui.componentes.GodPane;
import ec.edu.sino.gui.componentes.SearchField;
import ec.edu.sino.negocios.entidades.Docente;
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
public final class FDocente {

    private GodPane godPane;
    //Metodo
    private final MDocente mp = new MDocente();

    private TableView<Docente> table;
    private TableColumn<Docente, String> colCedula;
    private TableColumn<Docente, String> colApellido;
    private TableColumn<Docente, String> colNombre;

    private TableColumn colAcciones;

//DECLARACION DE LOS COMPONENTES PARA LA INSERCION Y MODIFICACION
    private final Label lblCedula = new Label("Cédula");
    private final Label lblApellido = new Label("Apellidos");
    private final Label lblNombre = new Label("Nombres");

    private TextField tfInsertCedula;
    private TextField tfInsertApellido;
    private TextField tfInsertNombre;

    private TextField tfUpdateCedula;
    private TextField tfUpdateApellido;
    private TextField tfUpdateNombre;
    private SearchField topPane;

    public FDocente() {

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

        tfInsertCedula = new TextField();
        tfInsertApellido = new TextField();
        tfInsertNombre = new TextField();

        HBox buttonsPane = new HBox(25);
        buttonsPane.setAlignment(Pos.CENTER);
        Button btnInsertDocente = new Button("Aceptar");
        btnInsertDocente.getStyleClass().add("btn-green");
        btnInsertDocente.setDefaultButton(true);
        btnInsertDocente.setMinSize(125, 30);
        btnInsertDocente.setOnAction(OkInsertActionEvent());

        Button btnCancel = new Button("Cancelar");
        btnCancel.setMinSize(125, 30);
        btnCancel.getStyleClass().add("btn-silver");
        btnCancel.setOnAction(NoInsertActionEvent());

        buttonsPane.getChildren().addAll(btnCancel, btnInsertDocente);

        boxInsert.getChildren().addAll(lblTitle,
                lblCedula, tfInsertCedula,
                lblApellido, tfInsertApellido,
                lblNombre, tfInsertNombre,
                buttonsPane);
        godPane.addInsertPane(boxInsert);

    }

    private void updatePanel(Docente docente) {
        int width = 300;
        VBox boxUpdate = new VBox(15);
        boxUpdate.setMaxSize(width, 300);
        boxUpdate.setPadding(new Insets(25));

        Label lblTitle = new Label("Modificar");
        lblTitle.setAlignment(Pos.CENTER);
        lblTitle.setMinSize(width, 35);

        tfUpdateCedula = new TextField(docente.getCedula());
        tfUpdateCedula.setEditable(false);
        tfUpdateApellido = new TextField(docente.getApellido());
        tfUpdateNombre = new TextField(docente.getNombre());

        HBox buttonsPane = new HBox(25);
        buttonsPane.setAlignment(Pos.CENTER);
        Button btnInsertDocente = new Button("Aceptar");
        btnInsertDocente.getStyleClass().add("btn-green");
        btnInsertDocente.setDefaultButton(true);
        btnInsertDocente.setMinSize(125, 30);
        btnInsertDocente.setOnAction(OkUpdateActionEvent(docente));

        Button btnCancel = new Button("Cancelar");
        btnCancel.setMinSize(125, 30);
        btnCancel.getStyleClass().add("btn-silver");
        btnCancel.setOnAction(NoUpdateActionEvent());

        buttonsPane.getChildren().addAll(btnCancel, btnInsertDocente);

        boxUpdate.getChildren().addAll(lblTitle,
                lblCedula, tfUpdateCedula,
                lblApellido, tfUpdateApellido,
                lblNombre, tfUpdateNombre, buttonsPane);
        godPane.addUpdatePane(boxUpdate);

    }

    private void showTable() {
        godPane.setTitle("Lista de docentes");

        VBox boxTable = new VBox(15);
        boxTable.setPadding(new Insets(35));
        boxTable.setAlignment(Pos.CENTER_RIGHT);


        
        topPane = new SearchField(10);
        topPane.setPromtText("Cédula o Nombre");
        topPane.addButtonText("Nuevo Docente");
        topPane.setOnActionSearch(SearchActionEvent());
        topPane.setOnActionInsert(insertActionEvent());
        
        
        table = new TableView<>();
        VBox.setVgrow(table, Priority.ALWAYS);
        table.setEditable(true);

        colCedula = new TableColumn<>("Cédula");
        colApellido = new TableColumn<>("Apellidos");
        colNombre = new TableColumn<>("Nombres");

        colAcciones = new TableColumn("Acciones");

        colCedula.setCellValueFactory(new PropertyValueFactory<>("cedula"));
        colApellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        colAcciones.setMinWidth(100);

        Callback<TableColumn<Docente, String>, TableCell<Docente, String>> cellFactory
                = (final TableColumn<Docente, String> param) -> {
                    final TableCell<Docente, String> cell = new TableCell<Docente, String>() {

                final CellButtons buttons = new CellButtons();

                @Override
                public void updateItem(String item, boolean empty) {

                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        Docente docente = getTableView().getItems().get(getIndex());

                        buttons.deleteAction(DeleteAtcionEvent(docente));
                        buttons.saveAction(updateActionEvent(docente));
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

        table.getColumns().addAll(colCedula, colApellido, colNombre, colAcciones);
        boxTable.getChildren().addAll(topPane, table);
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
            Docente docente = null;
            if (!"".equals(tfInsertCedula.getText())
                    && !"".equals(tfInsertApellido.getText())
                    && !"".equals(tfInsertNombre.getText())) {

                docente = new Docente();
                docente.setCedula(tfInsertCedula.getText());
                docente.setUsuario(tfInsertCedula.getText());
                docente.setClave(tfInsertCedula.getText());
                docente.setApellido(tfInsertApellido.getText());
                docente.setNombre(tfInsertNombre.getText());
            } else {
                godPane.failed("Campos sin llenar");
            }
            if (docente != null) {
                try {
                    if (mp.insertar(docente) > 0) {
                        godPane.successful("Insertado Correctamente");
                        refreshTable();
                        godPane.hideInsertPane();
                    }
                } catch (Exception e) {
                    godPane.failed("Docente no se ha guardado");
                }
            }
        };

    }

    private EventHandler NoInsertActionEvent() {
        return (t) -> {
            godPane.hideInsertPane();
        };
    }

    private EventHandler OkUpdateActionEvent(Docente docente) {
        return (t) -> {
            if (!"".equals(tfUpdateApellido.getText()) && !"".equals(tfUpdateNombre.getText())) {
                docente.setApellido(tfUpdateApellido.getText());
                docente.setNombre(tfUpdateNombre.getText());

            } else {
                godPane.failed("Campos sin llenar");
            }

            if (docente != null) {
                try {
                    if (mp.modificar(docente) > 0) {
                        godPane.successful("Editado Correctamente");
                        refreshTable();
                        godPane.hideUpdatePane();
                    }
                } catch (Exception e) {
                    godPane.failed("Docente no se ha guardado");
                }
            }
        };

    }

    private EventHandler NoUpdateActionEvent() {
        return (t) -> {
            godPane.hideUpdatePane();
        };
    }

    private EventHandler updateActionEvent(Docente docente) {
        return (t) -> {

            updatePanel(docente);
            godPane.showUpdatePane();
        };
    }

    private EventHandler DeleteAtcionEvent(Docente docente) {
        return (t) -> {
            godPane.showAlert(OkDeleteAtcionEvent(docente));

        };
    }

    private EventHandler OkDeleteAtcionEvent(Docente docente) {
        return (t) -> {
            try {
                if (mp.eliminar(docente) > 0) {
                    refreshTable();
                    godPane.successful("Docente eliminado.");
                }
            } catch (Exception e) {
                godPane.failed("No se puede eliminar");
            }
            godPane.hideAlert();
        };
    }

    private EventHandler SearchActionEvent() {
        return (t) -> {

            try {
                if (!"".equals(topPane.getText())) {
                    table.setItems(mp.obtenerDato(topPane.getText()));
                } else {
                    refreshTable();
                }
            } catch (Exception e) {
            }

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
