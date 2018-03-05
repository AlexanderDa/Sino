/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.sino.gui;

import ec.edu.sino.accesodatos.DBConnection;
import ec.edu.sino.dao.metodos.MCiclo;
import ec.edu.sino.dao.metodos.MParcial;
import ec.edu.sino.gui.componentes.GodPane;
import ec.edu.sino.gui.componentes.ValidatorField;
import ec.edu.sino.negocios.entidades.Alumno;
import ec.edu.sino.negocios.entidades.Parcial;
import ec.edu.sino.negocios.entidades.Curso;
import ec.edu.sino.negocios.entidades.Materia;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import javax.swing.WindowConstants;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author alexander
 */
public final class FParcial {

    private final Curso curso;
    private GodPane godPane;

    private final MParcial mParcial = new MParcial();
    private final MCiclo mCiclo = new MCiclo();

    private ComboBox<Materia> cbMaterias;
    ComboBox<String> cbQuimestre;
    private ComboBox<String> cbParcial;
    private TableView<Parcial> table;
//     id | quimestre |   descripcion   | tarea | individual | grupal | promedio_evaluacion | nota_parcial | promedio 

    private TableColumn<Parcial, String> colAlumno;
    private TableColumn colTareas;
    private TableColumn colIndividuales;
    private TableColumn colGrupal;
    private TableColumn colNotaParcial;
    private TableColumn<Parcial, Float> colPromedioEvaluacion;
    private TableColumn<Parcial, String> colPromedio;

//DECLARACION DE LOS COMPONENTES PARA LA INSERCION Y MODIFICACION
    public FParcial(Curso curso) {
        this.curso = curso;
    }

    public GodPane start() {
        mCiclo.loginProfesor();
        mParcial.loginAdmin();
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
            //  cbMaterias.setItems(mParcial.obtenerMaterias(curso));
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
        cbQuimestre.setPromptText("Parcial");
        try {
            ObservableList<String> quims = FXCollections.observableArrayList();
            quims.add("Primer Quimestre");
            quims.add("Segundo Quimestre");

            cbQuimestre.setItems(quims);
            cbQuimestre.getSelectionModel().select(0);
        } catch (Exception e) {
        }

        cbParcial = new ComboBox<>();
        cbParcial.setMinSize(250, 35);
        cbParcial.setPromptText("Parcial");
        try {
            ObservableList<String> parcial = FXCollections.observableArrayList();
            parcial.add("Primer Parcial");
            parcial.add("Segundo Parcial");
            parcial.add("Tercer Parcial");

            cbParcial.setItems(parcial);
            cbParcial.getSelectionModel().select(0);
        } catch (Exception e) {
        }

        cbMaterias.setOnAction(byParcialActionEvent());
        cbQuimestre.setOnAction(byParcialActionEvent());
        cbParcial.setOnAction(byParcialActionEvent());

        Button btnGuardar = new Button("Guardar");
        btnGuardar.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("imagenes/save.png"))));
        btnGuardar.setOnAction(btnGuardarActionEvent());
        btnGuardar.setMinHeight(35);

        Button btnBuscar = new Button();
        btnBuscar.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("componentes/imagenes/search.png"))));
        btnBuscar.setOnAction(reportesActionEvent());
        btnBuscar.setMinHeight(35);

        cbContainer.getChildren().addAll(cbMaterias, cbQuimestre, cbParcial, btnBuscar, btnGuardar);

        table = new TableView<>();
        VBox.setVgrow(table, Priority.ALWAYS);
        table.setEditable(true);

        colAlumno = new TableColumn<>("Nomina");
        colTareas = new TableColumn<>("Tareas");
        colIndividuales = new TableColumn<>("Individual");
        colGrupal = new TableColumn<>("Grupales");
        colNotaParcial = new TableColumn<>("Parcial");
        colPromedioEvaluacion = new TableColumn<>("Promedio Evaluaciones");
        colPromedio = new TableColumn<>("Promedio");

        colAlumno.setCellValueFactory(new PropertyValueFactory<>("quimestre"));
        colPromedioEvaluacion.setCellValueFactory(new PropertyValueFactory<>("promedioEvaluacion"));
        colPromedio.setCellValueFactory(new PropertyValueFactory<>("promedio"));

        Callback<TableColumn<Parcial, String>, TableCell<Parcial, String>> cellQuimestral
                = (final TableColumn<Parcial, String> param) -> {
                    final TableCell<Parcial, String> cell = new TableCell<Parcial, String>() {

                final ValidatorField field = new ValidatorField();

                @Override
                public void updateItem(String item, boolean empty) {

                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        Parcial parcial = getTableView().getItems().get(getIndex());

                        field.setText(parcial.getTarea() + "");
                        field.focusedProperty().addListener((o) -> {
                            if (!field.isFocused()) {
                                getTableView().getItems().get(getIndex()).setTarea(field.getValue());
                            }
                        });

                        setGraphic(field);
                        setText(null);
                    }
                }
            };
                    return cell;
                };
        colTareas.setCellFactory(cellQuimestral);

        Callback<TableColumn<Parcial, String>, TableCell<Parcial, String>> cellIndividual
                = (final TableColumn<Parcial, String> param) -> {
                    final TableCell<Parcial, String> cell = new TableCell<Parcial, String>() {

                final ValidatorField field = new ValidatorField();

                @Override
                public void updateItem(String item, boolean empty) {

                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        Parcial parcial = getTableView().getItems().get(getIndex());

                        field.setText(parcial.getIndividual() + "");
                        field.focusedProperty().addListener((o) -> {
                            if (!field.isFocused()) {
                                getTableView().getItems().get(getIndex()).setIndividual(field.getValue());
                            }
                        });

                        setGraphic(field);
                        setText(null);
                    }
                }
            };
                    return cell;
                };
        colIndividuales.setCellFactory(cellIndividual);

        Callback<TableColumn<Parcial, String>, TableCell<Parcial, String>> cellGrupal
                = (final TableColumn<Parcial, String> param) -> {
                    final TableCell<Parcial, String> cell = new TableCell<Parcial, String>() {

                final ValidatorField field = new ValidatorField();

                @Override
                public void updateItem(String item, boolean empty) {

                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        Parcial parcial = getTableView().getItems().get(getIndex());

                        field.setText(parcial.getGrupal() + "");
                        field.focusedProperty().addListener((o) -> {
                            if (!field.isFocused()) {
                                getTableView().getItems().get(getIndex()).setGrupal(field.getValue());
                            }
                        });

                        setGraphic(field);
                        setText(null);
                    }
                }
            };
                    return cell;
                };
        colGrupal.setCellFactory(cellGrupal);

        Callback<TableColumn<Parcial, String>, TableCell<Parcial, String>> cellParcial
                = (final TableColumn<Parcial, String> param) -> {
                    final TableCell<Parcial, String> cell = new TableCell<Parcial, String>() {

                final ValidatorField field = new ValidatorField();

                @Override
                public void updateItem(String item, boolean empty) {

                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        Parcial parcial = getTableView().getItems().get(getIndex());

                        field.setText(parcial.getNotaParcial() + "");
                        field.focusedProperty().addListener((o) -> {
                            if (!field.isFocused()) {
                                getTableView().getItems().get(getIndex()).setNotaParcial(field.getValue());
                            }
                        });

                        setGraphic(field);
                        setText(null);
                    }
                }
            };
                    return cell;
                };
        colNotaParcial.setCellFactory(cellParcial);

        refreshTable();
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.autosize();
// tarea | individual | grupal | promedio_evaluacion | nota_parcial | promedio 
        table.getColumns().addAll(colAlumno, colTareas, colIndividuales, colGrupal, colNotaParcial, colPromedioEvaluacion, colPromedio);
        boxTable.getChildren().addAll(cbContainer, table);
        godPane.addCenter(boxTable);

    }

//******************************************************************************
//*                                 EVENTOS                                    *
//******************************************************************************
    private EventHandler byParcialActionEvent() {
        return (t) -> {
            refreshTable();
        };
    }

    private EventHandler btnGuardarActionEvent() {
        return (t) -> {
            int modificados = 0;
            try {
                for (Parcial parcial : table.getItems()) {
                    modificados += mParcial.modificar(parcial);
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

    private EventHandler reportesActionEvent() {
        return (t) -> {
            
            try {
                DBConnection con = new DBConnection("admin", "adm!np4$");
                Connection conn = null;
                try {
                    conn = con.connect();
                } catch (SQLException | ClassNotFoundException ex) {
                    System.err.println(ex.getMessage());
                }

                JasperReport reporte = null;
                String path = "src/ec/edu/sino/gui/reportes/RParciales.jasper";

                Map parametro = new HashMap();
                parametro.put("curso", curso.getId());
                parametro.put("materia",cbMaterias.getSelectionModel().getSelectedItem().getId());
                parametro.put("quimestre",cbQuimestre.getSelectionModel().getSelectedItem().toUpperCase());
                parametro.put("parcial",cbParcial.getSelectionModel().getSelectedItem().toUpperCase());

                reporte = (JasperReport) JRLoader.loadObjectFromFile(path);

                JasperPrint jprint = JasperFillManager.fillReport(reporte, parametro, conn);

                JasperViewer view = new JasperViewer(jprint, false);

                view.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

                view.setVisible(true);

            } catch (JRException ex) {

            }
        };
    }
//******************************************************************************
//*                                                                   *
//******************************************************************************    

    private void refreshTable() {
        try {
            // table.setItems(mParcial.obtener(curso, cbMaterias.getSelectionModel().getSelectedItem(), cbQuimestre.getSelectionModel().getSelectedItem()));
            table.setItems(mParcial.obtener(curso, cbMaterias.getSelectionModel().getSelectedItem(),
                    cbQuimestre.getSelectionModel().getSelectedItem().toUpperCase(),
                    cbParcial.getSelectionModel().getSelectedItem().toUpperCase()));
        } catch (Exception e) {
            godPane.failed("No se ha podido refrescar la table");
            System.err.println(e.getMessage());
        }
    }
}
