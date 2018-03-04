/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.sino.gui;

import ec.edu.sino.dao.metodos.MCiclo;
import ec.edu.sino.dao.metodos.MQuimestre;
import ec.edu.sino.gui.componentes.CellButtons;
import ec.edu.sino.gui.componentes.GodPane;
import ec.edu.sino.gui.componentes.ValidatorField;
import ec.edu.sino.negocios.entidades.Alumno;
import ec.edu.sino.negocios.entidades.Quimestre;
import ec.edu.sino.negocios.entidades.Curso;
import ec.edu.sino.negocios.entidades.Materia;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

/**
 *
 * @author alexander
 */
public final class FQuimestre {

    private final Curso curso;
    private GodPane godPane;

    private final MQuimestre mQuimestre = new MQuimestre();
    private final MCiclo mCiclo = new MCiclo();

    private ComboBox<Materia> cbMaterias;
    ComboBox<String> cbQuimestre;
    private TableView<Quimestre> table;
    //quimestre  | promedio_parcial | nota_parcial | quimestral | nota_quimestral | promedio | nota_cualitativa 
    private TableColumn<Quimestre, Alumno> colAlumno;
    private TableColumn<Quimestre, Float> colParciales;
    private TableColumn<Quimestre, Float> colNotaParciales;
    private TableColumn colQuimestral;
    private TableColumn<Quimestre, Float> colNotaQquimestral;
    private TableColumn<Quimestre, Float> colPromedio;
    private TableColumn<Quimestre, String> colNotaCualitativa;

//DECLARACION DE LOS COMPONENTES PARA LA INSERCION Y MODIFICACION
    public FQuimestre(Curso curso) {
        this.curso = curso;
    }

    public GodPane start() {
        mCiclo.loginProfesor();
        mQuimestre.loginAdmin();
        godPane = new GodPane();
        godPane.init();
        showTable();
        return godPane;
    }

    private void showTable() {
        godPane.setTitle("Nomina de " + curso);

        VBox boxTable = new VBox(15);
        boxTable.setPadding(new Insets(35));
        boxTable.setAlignment(Pos.CENTER_RIGHT);

        cbMaterias = new ComboBox<>();
        cbMaterias.setMinSize(250, 35);
        cbMaterias.setPromptText("Selecionar Materia");
        try {
            //  cbMaterias.setItems(mQuimestre.obtenerMaterias(curso));
        } catch (Exception e) {
        }
        HBox cbContainer = new HBox();
        cbContainer.setAlignment(Pos.CENTER_RIGHT);

        try {
            cbMaterias.setItems(mCiclo.obtenerMaterias(curso));
            cbMaterias.getSelectionModel().select(0);
        } catch (Exception ex) {
        }

        cbQuimestre = new ComboBox<>();
        cbQuimestre.setMinSize(250, 35);
        cbQuimestre.setPromptText("Quimestre");
        cbMaterias.setStyle("");
        try {
            ObservableList<String> quims = FXCollections.observableArrayList();
            quims.add("Primer Quimestre");
            quims.add("Segundo Quimestre");

            cbQuimestre.setItems(quims);
            cbQuimestre.getSelectionModel().select(0);
        } catch (Exception e) {
        }

        Button btnGuardar = new Button("Guardar");
        btnGuardar.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("imagenes/save.png"))));
        btnGuardar.setOnAction(btnGuardarActionEvent());
        btnGuardar.setMinHeight(35);

        Button btnBuscar = new Button();
        btnBuscar.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("componentes/imagenes/search.png"))));
        btnBuscar.setOnAction(byQuimestreActionEvent());
        btnBuscar.setMinHeight(35);
        
        cbContainer.getChildren().addAll(cbMaterias, cbQuimestre, btnBuscar, btnGuardar);

        table = new TableView<>();
        VBox.setVgrow(table, Priority.ALWAYS);
        table.setEditable(true);

        colAlumno = new TableColumn<>("Nomina");
        colParciales = new TableColumn<>("Parciales");
        colNotaParciales = new TableColumn<>("Nota Parciales");
        colQuimestral = new TableColumn<>("Quimestral");
        colNotaQquimestral = new TableColumn<>("Nota Quimestral");
        colPromedio = new TableColumn<>("Promedio");
        colNotaCualitativa = new TableColumn<>("Nota Cualitativa");

        colAlumno.setCellValueFactory(new PropertyValueFactory<>("ciclo"));
        colParciales.setCellValueFactory(new PropertyValueFactory<>("promedioParcial"));
        colNotaParciales.setCellValueFactory(new PropertyValueFactory<>("notaParcial"));
        //colQuimestral.setCellValueFactory(new PropertyValueFactory<>("quimestral"));
        colNotaQquimestral.setCellValueFactory(new PropertyValueFactory<>("notaQuimestral"));
        colPromedio.setCellValueFactory(new PropertyValueFactory<>("promedio"));
        colNotaCualitativa.setCellValueFactory(new PropertyValueFactory<>("notaCualitativa"));

        Callback<TableColumn<Quimestre, String>, TableCell<Quimestre, String>> cellQuimestral
                = (final TableColumn<Quimestre, String> param) -> {
                    final TableCell<Quimestre, String> cell = new TableCell<Quimestre, String>() {

                final ValidatorField field = new ValidatorField();

                @Override
                public void updateItem(String item, boolean empty) {

                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        Quimestre quimestre = getTableView().getItems().get(getIndex());

                        field.setText(quimestre.getQuimestral() + "");
                        field.focusedProperty().addListener((o) -> {
                            if (!field.isFocused()) {
                                getTableView().getItems().get(getIndex()).setQuimestral(field.getValue());
                            }
                        });

                        setGraphic(field);
                        setText(null);
                    }
                }
            };
                    return cell;
                };
        colQuimestral.setCellFactory(cellQuimestral);
        refreshTable();
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.autosize();

        table.getColumns().addAll(colAlumno, colParciales, colNotaParciales, colQuimestral, colNotaQquimestral, colPromedio, colNotaCualitativa);
        boxTable.getChildren().addAll(cbContainer, table);
        godPane.addCenter(boxTable);

    }

//******************************************************************************
//*                                 EVENTOS                                    *
//******************************************************************************
    private EventHandler byQuimestreActionEvent() {
        return (t) -> {
            refreshTable();
        };
    }

    private EventHandler btnGuardarActionEvent() {
        return (t) -> {
            int modificados = 0;
            try {
                for (Quimestre quim : table.getItems()) {
                    modificados += mQuimestre.modificar(quim);
                }
                if (modificados > 0) {
                    godPane.successful("Los cambios se han guardado.");
                }
                refreshTable();
            } catch (Exception e) {
                godPane.failed("No se han guardado los cambios.");
            }
            godPane.hideAlert();
        };
    }

//******************************************************************************
//*                                                                   *
//******************************************************************************    
    private void refreshTable() {
        try {
            table.setItems(mQuimestre.obtener(curso, cbMaterias.getSelectionModel().getSelectedItem(), cbQuimestre.getSelectionModel().getSelectedItem()));
//            System.out.println(mQuimestre.obtener(1).getAsignada());
        } catch (Exception e) {
            godPane.failed("No se ha podido refrescar la table");
            System.err.println(e.getMessage());
        }
    }
}
