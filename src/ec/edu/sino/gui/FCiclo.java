/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.sino.gui;

import ec.edu.sino.dao.metodos.MCiclo;
import ec.edu.sino.gui.componentes.CellButtons;
import ec.edu.sino.gui.componentes.GodPane;
import ec.edu.sino.negocios.entidades.Ciclo;
import ec.edu.sino.negocios.entidades.Curso;
import ec.edu.sino.negocios.entidades.Materia;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
public final class FCiclo {

    private final Curso curso;
    private GodPane godPane;

    private final MCiclo mCiclo = new MCiclo();

    private ComboBox<Materia> cbMaterias;
    private TableView<Ciclo> table;
    private TableColumn<Ciclo, String> colAlumno;
    private TableColumn<Ciclo, String> colPromedio;

    private TableColumn colAcciones;

//DECLARACION DE LOS COMPONENTES PARA LA INSERCION Y MODIFICACION
    public FCiclo(Curso curso) {
        this.curso = curso;
    }

    public GodPane start() {

        mCiclo.loginAdmin();
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
        cbMaterias.setMinSize(300, 35);
        cbMaterias.setPromptText("Selecionar Materia");
        try {
            cbMaterias.setItems(mCiclo.obtenerMaterias(curso));
        } catch (Exception e) {
        }
        AnchorPane cbContainer = new AnchorPane(cbMaterias);
        AnchorPane.setTopAnchor(cbMaterias, 0.0);
        AnchorPane.setBottomAnchor(cbMaterias, 0.0);
        AnchorPane.setRightAnchor(cbMaterias, 0.0);
        HBox.setHgrow(cbContainer, Priority.ALWAYS);

        cbMaterias.setOnAction(byMateriaAtcionEvent());

        table = new TableView<>();
        VBox.setVgrow(table, Priority.ALWAYS);
        table.setEditable(true);

        colAlumno = new TableColumn<>("Nomina");
        colPromedio = new TableColumn<>("Promedio");

        colAcciones = new TableColumn("Acciones");

        colAlumno.setCellValueFactory(new PropertyValueFactory<>("alumno"));
        colPromedio.setCellValueFactory(new PropertyValueFactory<>("promedio"));

        colAcciones.setMinWidth(100);

        Callback<TableColumn<Ciclo, String>, TableCell<Ciclo, String>> cellFactory
                = (final TableColumn<Ciclo, String> param) -> {
                    final TableCell<Ciclo, String> cell = new TableCell<Ciclo, String>() {

                final CellButtons buttons = new CellButtons();

                @Override
                public void updateItem(String item, boolean empty) {

                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        Ciclo ciclo = getTableView().getItems().get(getIndex());

//                        buttons.deleteAction(DeleteAtcionEvent(ciclo));
//                        buttons.saveAction(updateActionEvent(ciclo));
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

        table.getColumns().addAll(colAlumno, colPromedio);
        boxTable.getChildren().addAll(cbContainer, table);
        godPane.addCenter(boxTable);

    }

//******************************************************************************
//*                                 EVENTOS                                    *
//******************************************************************************
    private EventHandler byMateriaAtcionEvent() {
        return (t) -> {
            try {
                table.setItems(mCiclo.obtenerNotas(curso, cbMaterias.getSelectionModel().getSelectedItem()));
            } catch (Exception e) {
                godPane.failed("No se puede encontro ningun registro.");
            }
            godPane.hideAlert();
        };
    }

//******************************************************************************
//*                                                                   *
//******************************************************************************    
    private void refreshTable() {
        try {
            table.setItems(mCiclo.obtenerNotas(curso));
//            System.out.println(mCiclo.obtener(1).getAsignada());
        } catch (Exception e) {
            godPane.failed("No se ha podido refrescar la table");
            System.err.println(e.getMessage());
        }
    }
}
