/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.sino.gui;

import ec.edu.sino.dao.metodos.MDocente;
import ec.edu.sino.gui.componentes.CellButtons;
import ec.edu.sino.gui.componentes.NotificationPanel;
import ec.edu.sino.negocios.entidades.Docente;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

/**
 *
 * @author alexander
 */
public class FDocente {

    private final MDocente md = new MDocente();
    private TableView<Docente> tabla;
    private TableColumn<Docente, String> colCedula;
    private TableColumn<Docente, String> colApellido;
    private TableColumn<Docente, String> colNombre;
    private TableColumn colAcciones;

    private TextField tfInsertId;
    private TextField tfInsertName;
    private TextField tfInsertLastName;
    private NotificationPanel InsertPanel;
    private NotificationPanel tablePanel;

    public FDocente() {
        md.loginAdmin();
    }

    public NotificationPanel insertPanel() {
        InsertPanel = new NotificationPanel();
        InsertPanel.setTitle("Insertar Docente");

        VBox boxInsert = new VBox(15);
        boxInsert.setMaxSize(500, 300);

        Label lblCedula = new Label("Cédula");
        tfInsertId = new TextField();
        Label lblNombre = new Label("Nombres");
        tfInsertName = new TextField();
        Label lblApellido = new Label("Apellidos");
        tfInsertLastName = new TextField();

        Button btnSave = new Button("Guardar");
        btnSave.setOnAction(btnSaveActionEvent());

        boxInsert.getChildren().addAll(lblCedula, tfInsertId, lblNombre, tfInsertName, lblApellido, tfInsertLastName, btnSave);
        InsertPanel.add(boxInsert);

        return InsertPanel;
    }

    public NotificationPanel tablePanel() {
        tablePanel = new NotificationPanel();
        tablePanel.setTitle("Nomina de docentes");
        tablePanel.padding(50);

        VBox boxTable = new VBox(15);

        HBox optionSPanel = new HBox(10);
        RadioButton forName = new RadioButton("Nombre");
        ToggleGroup group = new ToggleGroup();
        forName.setToggleGroup(group);
        RadioButton forID = new RadioButton("Cédula");
        forID.setSelected(true);
        forID.setToggleGroup(group);
        optionSPanel.getChildren().addAll(forID, forName);

        TextField input = new TextField();
        input.setPromptText("Búscar por cédula");

        tabla = new TableView<>();
        VBox.setVgrow(tabla, Priority.ALWAYS);
        tabla.setEditable(true);

        colCedula = new TableColumn<>("Cedula");
        colApellido = new TableColumn<>("Apellido");
        colNombre = new TableColumn<>("Nombre");
        colAcciones = new TableColumn("Acciones");

        colCedula.setCellValueFactory(new PropertyValueFactory<>("cedula"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colApellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));

        colAcciones.setMinWidth(100);

        colNombre.setCellFactory(TextFieldTableCell.forTableColumn());
        colApellido.setCellFactory(TextFieldTableCell.forTableColumn());
        colNombre.setOnEditCommit(((t) -> {
            TablePosition<Docente, String> actual = t.getTablePosition();
            String nombre = t.getNewValue();

            int row = actual.getRow();
            Docente docente = t.getTableView().getItems().get(row);

            docente.setNombre(nombre);

        }));

        colApellido.setOnEditCommit(((t) -> {
            TablePosition<Docente, String> actual = t.getTablePosition();
            String apellido = t.getNewValue();

            int row = actual.getRow();
            Docente docente = t.getTableView().getItems().get(row);

            docente.setNombre(apellido);

        }));

        Callback<TableColumn<Docente, String>, TableCell<Docente, String>> cellFactory = (final TableColumn<Docente, String> param) -> {
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
                        buttons.deleteAction(btnDeleteActionEvent(docente));
                        buttons.saveAction(btnUpdateActionEvent(docente));
                        setGraphic(buttons);
                        setText(null);
                    }
                }
            };
            return cell;
        };
        colAcciones.setCellFactory(cellFactory);
        try {
            tabla.setItems(md.obtener());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        forID.setOnAction((t) -> {
            input.setPromptText("Búscar por cédula.");
        });
        forName.setOnAction((t) -> {
            input.setPromptText("Búscar por nombre.");
        });

        input.setOnKeyReleased((t) -> {

            try {
                if (forID.isSelected()) {

                    tabla.setItems(md.obtenerCedulas(input.getText()));
                } else {
                    tabla.setItems(md.obtenerNombres(input.getText()));
                }

            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        });
        tabla.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tabla.autosize();

        tabla.getColumns().addAll(colCedula, colApellido, colNombre, colAcciones);
        boxTable.getChildren().addAll(optionSPanel, input, tabla);
        tablePanel.add(boxTable);
        return tablePanel;

    }

//******************************************************************************
//*                            CREACION DE EVENTOS                             *
//****************************************************************************** 
    private EventHandler btnSaveActionEvent() {
        EventHandler handler = (t) -> {
            Docente docente = null;
            if (!"".equals(tfInsertId.getText()) && !"".equals(tfInsertLastName.getText()) && !"".equals(tfInsertName.getText())) {
                docente = new Docente();
                docente.setCedula(tfInsertId.getText());
                docente.setNombre(tfInsertName.getText());
                docente.setApellido(tfInsertLastName.getText());
                docente.setClave(tfInsertId.getText());
                docente.setUsuario(tfInsertId.getText());
            } else {
                InsertPanel.failed("Campos sin llenar");
            }
            if (docente != null) {
                try {
                    if (md.insertar(docente) > 0) {
                        InsertPanel.successful("Insertado Correctamente");
                        tfInsertId.setText("");
                        tfInsertLastName.setText("");
                        tfInsertName.setText("");
                    }
                } catch (Exception e) {
                    String noCedula = "ERROR: value for domain identification violates check constraint \"identification_check\"";
                    String duplucada = "ERROR: duplicate key value violates unique constraint \"pk_docente\"\n  "
                            + "Detail: Key (cedula)=(" + docente.getCedula() + ") already exists.";
                    if (noCedula.equals(e.getMessage())) {
                        InsertPanel.failed("El campo cédula no está correcta");
                    } else if (duplucada.equals(e.getMessage())) {
                        InsertPanel.failed("Cédula duplicada");
                    }
                }
            }

        };
        return handler;
    }

    private EventHandler btnUpdateActionEvent(Docente docente) {
        return (t) -> {
            try {
                if (md.modificar(docente) > 0) {
                    tablePanel.successful("Cambios guardados.");
                }
            } catch (Exception e) {
                tablePanel.failed("No se guardaron los cambios");
            }

        };
    }

    private EventHandler btnDeleteActionEvent(Docente docente) {
        return (t) -> {
            tablePanel.showAlert(btnAgreeActionEvent(docente));

        };
    }

    private EventHandler btnAgreeActionEvent(Docente docente) {
        return (t) -> {
            try {
                if (md.eliminar(docente) > 0) {
                    tabla.getItems().remove(docente);
                    tablePanel.successful("Docente eliminado.");
                }
            } catch (Exception e) {
                tablePanel.failed("No se puede\n eliminar");
            }
            tablePanel.hideAlert();

        };
    }
}
