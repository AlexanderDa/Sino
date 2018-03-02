/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.sino.gui;

import ec.edu.sino.dao.metodos.MAlumno;
import ec.edu.sino.gui.componentes.CellButtons;
import ec.edu.sino.gui.componentes.GodPane;
import ec.edu.sino.negocios.entidades.Alumno;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

/**
 *
 * @author alexander
 */
public final class Falumno {

    private int id;
    private GodPane godPane;
    //Metodo
    private final MAlumno ma = new MAlumno();

    private TableView<Alumno> table;
    private TableColumn<Alumno, String> colCedula;
    private TableColumn<Alumno, String> colNombre;
    private TableColumn<Alumno, String> colApellido;

    private TableColumn colAcciones;

//DECLARACION DE LOS COMPONENTES PARA LA INSERCION Y MODIFICACION
    private final Label lblCedula = new Label("Cédula");
    private final Label lblNombre = new Label("Nombre");
    private final Label lblApellido = new Label("Apellido");

    private TextField tfInsertCedula;
    private TextField tfInsertNombre;
    private TextField tfInsertApellido;

    private TextField tfUpdateCedula;
    private TextField tfUpdateNombre;
    private TextField tfUpdateApellido;
    private RadioButton forName;
    private RadioButton forID;
    private TextField tfSearch;

    public Falumno() {

    }

    public GodPane start() {

        ma.loginAdmin();
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

        tfInsertCedula = new TextField();
        tfInsertNombre = new TextField();
        tfInsertApellido = new TextField();

        HBox buttonsPane = new HBox(25);
        buttonsPane.setAlignment(Pos.CENTER);
        Button btnInsertAlumno = new Button("Aceptar");
        btnInsertAlumno.getStyleClass().add("btn-green");
        btnInsertAlumno.setDefaultButton(true);
        btnInsertAlumno.setMinSize(125, 30);
        btnInsertAlumno.setOnAction(OkInsertActionEvent());

        Button btnCancel = new Button("Cancelar");
        btnCancel.setMinSize(125, 30);
        btnCancel.getStyleClass().add("btn-silver");
        btnCancel.setOnAction(NoInsertActionEvent());

        buttonsPane.getChildren().addAll(btnCancel, btnInsertAlumno);

        boxInsert.getChildren().addAll(lblTitle, lblCedula, tfInsertCedula, lblNombre, tfInsertNombre, lblApellido, tfInsertApellido, buttonsPane);
        godPane.addInsertPane(boxInsert);

    }

    private void updatePanel(Alumno alumno) {
        int width = 300;
        VBox boxUpdate = new VBox(15);
        boxUpdate.setMaxSize(width, 200);
        boxUpdate.setPadding(new Insets(25));

        Label lblTitle = new Label("Modificar");
        lblTitle.setAlignment(Pos.CENTER);
        lblTitle.setMinSize(width, 35);
        HBox.setHgrow(lblTitle, Priority.ALWAYS);

        tfUpdateCedula = new TextField(alumno.getCedula());
        tfUpdateNombre = new TextField(alumno.getNombre());
        tfUpdateApellido = new TextField(alumno.getApellido());

        HBox buttonsPane = new HBox(25);
        buttonsPane.setAlignment(Pos.CENTER);
        Button btnInsertAlumno = new Button("Aceptar");
        btnInsertAlumno.getStyleClass().add("btn-green");
        btnInsertAlumno.setDefaultButton(true);
        btnInsertAlumno.setMinSize(125, 30);
        btnInsertAlumno.setOnAction(OkUpdateActionEvent(alumno));

        Button btnCancel = new Button("Cancelar");
        btnCancel.setMinSize(125, 30);
        btnCancel.getStyleClass().add("btn-silver");
        btnCancel.setOnAction(NoUpdateActionEvent());

        buttonsPane.getChildren().addAll(btnCancel, btnInsertAlumno);

        boxUpdate.getChildren().addAll(lblTitle, lblCedula, tfUpdateCedula, lblNombre, tfUpdateNombre, lblApellido, tfUpdateApellido, buttonsPane);
        godPane.addUpdatePane(boxUpdate);

    }

    private void showTable() {
        godPane.setTitle("Alumnos");

        VBox boxTable = new VBox(15);
        boxTable.setPadding(new Insets(35));
        boxTable.setAlignment(Pos.CENTER_RIGHT);

        HBox boxSearchOptions = new HBox(10);
        ToggleGroup group = new ToggleGroup();
        forName = new RadioButton("Nombre");
        forName.setToggleGroup(group);
        forName.setOnAction((t) -> {
            tfSearch.setPromptText("Búscar por nombre.");
        });

        forID = new RadioButton("Cédula");
        forID.setSelected(true);
        forID.setToggleGroup(group);
        forID.setOnAction((t) -> {
            tfSearch.setPromptText("Búscar por Cédula.");
        });

        boxSearchOptions.getChildren().addAll(forID, forName);

        tfSearch = new TextField();
        tfSearch.setPromptText("Búscar por cédula");
        tfSearch.setOnKeyReleased(SearchActionEvent());

        Button btnInsert = new Button("Insertar");
        btnInsert.getStyleClass().add("btn-green");
        btnInsert.setOnAction(insertActionEvent());
        table = new TableView<>();
        VBox.setVgrow(table, Priority.ALWAYS);
        table.setEditable(true);

        colCedula = new TableColumn<>("Cédula");
        colNombre = new TableColumn<>("Nombre");
        colApellido = new TableColumn<>("Apellido");

        colAcciones = new TableColumn("Acciones");

        colCedula.setCellValueFactory(new PropertyValueFactory<>("cedula"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colApellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));

        colAcciones.setMinWidth(100);

        Callback<TableColumn<Alumno, String>, TableCell<Alumno, String>> cellFactory
                = (final TableColumn<Alumno, String> param) -> {
                    final TableCell<Alumno, String> cell = new TableCell<Alumno, String>() {

                final CellButtons buttons = new CellButtons();

                @Override
                public void updateItem(String item, boolean empty) {

                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        Alumno alumno = getTableView().getItems().get(getIndex());

                        buttons.deleteAction(DeleteAtcionEvent(alumno));
                        buttons.saveAction(updateActionEvent(alumno));
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

        table.getColumns().addAll(colCedula, colNombre, colApellido, colAcciones);
        boxTable.getChildren().addAll(btnInsert, boxSearchOptions, tfSearch, table);
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
            Alumno alumno = null;
            if (!"".equals(tfInsertCedula.getText()) && !"".equals(tfInsertNombre.getText()) && !"".equals(tfInsertApellido.getText())) {
                alumno = new Alumno();
                alumno.setCedula(tfInsertCedula.getText());
                alumno.setNombre(tfInsertNombre.getText());
                alumno.setApellido(tfInsertApellido.getText());
            } else {
                godPane.failed("Campos sin llenar");
            }
            if (alumno != null) {
                try {
                    if (ma.insertar(alumno) > 0) {
                        godPane.successful("Insertado Correctamente");
                        refreshTable();
                        godPane.hideInsertPane();
                    }
                } catch (Exception e) {
                    godPane.failed("Alumno no se ha guardado");
                }
            }
        };

    }

    private EventHandler NoInsertActionEvent() {
        return (t) -> {
            godPane.hideInsertPane();
        };
    }

    private EventHandler OkUpdateActionEvent(Alumno alumno) {
        return (t) -> {
            if (!"".equals(tfUpdateCedula.getText()) && !"".equals(tfUpdateNombre.getText()) && !"".equals(tfUpdateApellido.getText())) {
                alumno.setCedula(tfUpdateCedula.getText());
                alumno.setNombre(tfUpdateNombre.getText());
                alumno.setApellido(tfUpdateApellido.getText());
            } else {
                godPane.failed("Campos sin llenar");
            }

            if (alumno != null) {
                try {
                    if (ma.modificar(alumno) > 0) {
                        godPane.successful("Insertado Correctamente");
                        refreshTable();
                        godPane.hideUpdatePane();
                    }
                } catch (Exception e) {
                    godPane.failed("Alumno no se ha guardado");
                }
            }
        };

    }

    private EventHandler NoUpdateActionEvent() {
        return (t) -> {
            godPane.hideUpdatePane();
        };
    }

    private EventHandler updateActionEvent(Alumno alumno) {
        return (t) -> {
            updatePanel(alumno);
            godPane.showUpdatePane();
        };
    }

    private EventHandler DeleteAtcionEvent(Alumno alumno) {
        return (t) -> {
            godPane.showAlert(OkDeleteAtcionEvent(alumno));

        };
    }

    private EventHandler OkDeleteAtcionEvent(Alumno alumno) {
        return (t) -> {
            try {
                if (ma.eliminar(alumno) > 0) {
                    refreshTable();
                    godPane.successful("Alumno eliminado.");
                }
            } catch (Exception e) {
                godPane.failed("No se puede eliminar");
            }
            godPane.hideAlert();
        };
    }

    private EventHandler SearchActionEvent() {
        return (t) -> {
            if (forID.isSelected()) {

                try {
                    table.setItems(ma.obtenerCedula(tfSearch.getText()));
                } catch (Exception e) {
                }
            }

            if (forName.isSelected()) {

                try {
                    table.setItems(ma.obtenerNombre(tfSearch.getText()));
                } catch (Exception e) {
                }
            }
        };
    }

//******************************************************************************
//*                                                                   *
//******************************************************************************    
    private void refreshTable() {
        try {
            table.setItems(ma.obtenerTodos());
        } catch (Exception e) {
            godPane.failed("No se ha podido refrescar la table");
        }
    }
}
