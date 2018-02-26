/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.sino.gui;

import ec.edu.sino.dao.metodos.MCurso;
import ec.edu.sino.dao.metodos.MMAteria;
import ec.edu.sino.dao.metodos.MMateriaAsignada;
import ec.edu.sino.gui.componentes.CellButtons;
import ec.edu.sino.gui.componentes.FSearchPane;
import ec.edu.sino.gui.componentes.GodPane;
import ec.edu.sino.gui.componentes.VSearchPane;
import ec.edu.sino.negocios.entidades.Curso;
import ec.edu.sino.negocios.entidades.Materia;
import ec.edu.sino.negocios.entidades.MateriaAsignada;
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
    private final MMateriaAsignada mAsignada = new MMateriaAsignada();
    private final MMAteria mMAteria = new MMAteria();
    private final MCurso mCurso = new MCurso();

    private TableView<MateriaAsignada> table;
    private TableColumn<MateriaAsignada, Materia> colMateria;
    private TableColumn<MateriaAsignada, Curso> colCurso;

    private TableColumn colAcciones;
    private FSearchPane spBuscar;
    

//DECLARACION DE LOS COMPONENTES PARA LA INSERCION Y MODIFICACION
    private VSearchPane spInsertar;
    private ComboBox<Materia> cbMateria;
    public FAsignarMaterias() {

    }

    public GodPane start() {
        mAsignada.loginAdmin();
        mMAteria.loginAdmin();
        mCurso.loginAdmin();
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
          spInsertar = new VSearchPane();
          cbMateria = new ComboBox<>();
          cbMateria.setMinWidth(width);
          try {
            cbMateria.setItems(mMAteria.obtener());
        } catch (Exception e) {
        }
        HBox buttonsPane = new HBox(25);
        buttonsPane.setAlignment(Pos.CENTER);
        Button btnInsertMateriaAsignada = new Button("Aceptar");
        btnInsertMateriaAsignada.getStyleClass().add("btn-green");
        btnInsertMateriaAsignada.setDefaultButton(true);
        btnInsertMateriaAsignada.setMinSize(125, 30);
        btnInsertMateriaAsignada.setOnAction(OkInsertActionEvent());

        Button btnCancel = new Button("Cancelar");
        btnCancel.setMinSize(125, 30);
        btnCancel.getStyleClass().add("btn-silver");
        btnCancel.setOnAction(NoInsertActionEvent());

        buttonsPane.getChildren().addAll(btnCancel, btnInsertMateriaAsignada);

        boxInsert.getChildren().addAll(lblTitle,spInsertar,cbMateria,buttonsPane);
        godPane.addInsertPane(boxInsert);

    }



    private void showTable() {
        godPane.setTitle("Materias por curso");

        VBox boxTable = new VBox(15);
        boxTable.setPadding(new Insets(35));
        boxTable.setAlignment(Pos.CENTER_RIGHT);
        spBuscar = new FSearchPane();

        HBox hbButtons = new HBox();
        hbButtons.setAlignment(Pos.CENTER_RIGHT);
        Button btnSearch = new Button("Buscar");
        btnSearch.getStyleClass().add("btn-green");
        btnSearch.setOnAction(btnSearchActionEvent());

        Button btnInsert = new Button("Insertar");
        btnInsert.getStyleClass().add("btn-green");
        btnInsert.setOnAction(insertActionEvent());
        hbButtons.getChildren().addAll(btnSearch, btnInsert);

        table = new TableView<>();
        VBox.setVgrow(table, Priority.ALWAYS);
        table.setEditable(true);

        colCurso = new TableColumn<>("Curso");
        colMateria = new TableColumn<>("Materia");

        colCurso.setCellValueFactory(new PropertyValueFactory<>("curso"));
        colMateria.setCellValueFactory(new PropertyValueFactory<>("materia"));
        colAcciones = new TableColumn("Acciones");

        colAcciones.setMinWidth(100);

        Callback<TableColumn<MateriaAsignada, String>, TableCell<MateriaAsignada, String>> cellFactory
                = (final TableColumn<MateriaAsignada, String> param) -> {
                    final TableCell<MateriaAsignada, String> cell = new TableCell<MateriaAsignada, String>() {

                final CellButtons buttons = new CellButtons();

                @Override
                public void updateItem(String item, boolean empty) {

                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        MateriaAsignada materiaAsignada = getTableView().getItems().get(getIndex());
                        id = materiaAsignada.getId();

                        buttons.deleteAction(DeleteAtcionEvent(materiaAsignada));
                        buttons.hideEditButton();
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

        table.getColumns().addAll(colCurso, colMateria, colAcciones);
        boxTable.getChildren().addAll(spBuscar, hbButtons, table);
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
            MateriaAsignada materiaAsignada = null;
            if (spInsertar.exists() && cbMateria.getSelectionModel().getSelectedItem()!=null) {
                materiaAsignada = new MateriaAsignada();
                try {
                    materiaAsignada.setCurso(spInsertar.getCurso());
                } catch (Exception ex) {
                    godPane.failed("El curso no existe");
                }
                materiaAsignada.setMateria(cbMateria.getSelectionModel().getSelectedItem());
            } else {
                godPane.failed("Campos sin llenar");
            }
            if (materiaAsignada != null) {
                try {
                    if (mAsignada.insertar(materiaAsignada) > 0) {
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

    
    private EventHandler DeleteAtcionEvent(MateriaAsignada materiaAsignada) {
        return (t) -> {
            godPane.showAlert(OkDeleteAtcionEvent(materiaAsignada));

        };
    }

    private EventHandler OkDeleteAtcionEvent(MateriaAsignada materiaAsignada) {
        return (t) -> {
            try {
                if (mAsignada.eliminar(materiaAsignada) > 0) {
                    refreshTable();
                    godPane.successful("MateriaAsignada eliminado.");
                }
            } catch (Exception e) {
                godPane.failed("No se puede eliminar");
            }
            godPane.hideAlert();
        };
    }

    private EventHandler btnSearchActionEvent() {
        return (t) -> {

            try {
                if (spBuscar.exists()) {
                    table.setItems(mAsignada.obtenerPorCurso(spBuscar.getCurso()));
                } else {
                    godPane.failed("Docente o periodo no seleccionados");
                }
            } catch (Exception ex) {
                godPane.failed("Error al encontrar");
            }
        };
    }

//******************************************************************************
//*                                                                   *
//******************************************************************************    
    private void refreshTable() {
        try {
            table.setItems(mAsignada.obtener());
        } catch (Exception e) {
            godPane.failed("No se ha podido refrescar la table" + e.getMessage());
        }
    }
}
