/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.sino.gui;

import ec.edu.sino.dao.metodos.MCurso;
import ec.edu.sino.dao.metodos.MDocente;
import ec.edu.sino.dao.metodos.MMAteria;
import ec.edu.sino.dao.metodos.MPeriodo;
import ec.edu.sino.gui.componentes.CellButtons;
import ec.edu.sino.gui.componentes.GodPane;
import ec.edu.sino.negocios.entidades.Curso;
import ec.edu.sino.negocios.entidades.Docente;
import ec.edu.sino.negocios.entidades.Materia;
import ec.edu.sino.negocios.entidades.Periodo;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

/**
 *
 * @author alexander
 */
public final class FCurso {

    private GodPane godPane;
    //Metodo
    private final MCurso mc = new MCurso();
    private final MPeriodo mp = new MPeriodo();
    private final MDocente md = new MDocente();
    private final MMAteria mma = new MMAteria();

    private TableView<Curso> table;
    private TableColumn<Curso, String> colPeriodo;
    private TableColumn<Curso, String> colDocente;
    private TableColumn<Curso, String> colMateria;
    private TableColumn<Curso, String> colGrado;
    private TableColumn<Curso, String> colParalelo;
    private TableColumn colAcciones;

    private final Label lblPeriodo = new Label("Periodo");
    private final Label lblDocente = new Label("Docente");
    private final Label lblMateria = new Label("Materia");
    private final Label lblGrado = new Label("Grado");
    private final Label lblParalelo = new Label("Paralelo");

    private ComboBox<Periodo> cbInsertPeriodo;
    private ComboBox<Docente> cbInsertDocente;
    private TextField tfInsertGrado;
    private TextField tfInsertParalelo;
    private TableView<Materia> tvInsertMateria;

    private ComboBox<Periodo> cbUpdatePeriodo;
    private ComboBox<Docente> cbUpdateDocente;
    private ComboBox<Materia> cbUpdateMateria;
    private TextField tfUpdateGrado;

//DECLARACION DE LOS COMPONENTES PARA LA INSERCION Y MODIFICACION
    public FCurso() {

    }

    public GodPane start() {

        mc.loginAdmin();
        mp.loginAdmin();
        md.loginAdmin();
        mma.loginAdmin();

        godPane = new GodPane();
        godPane.init();
        insertPanel();
        showTable();
        return godPane;
    }

    private void insertPanel() {
        int width = 500;
        VBox boxInsert = new VBox(15);
        boxInsert.setMaxSize(width, width);
        boxInsert.setPadding(new Insets(25));

        Label lblTitle = new Label("Insertar Nuevo");
        lblTitle.setAlignment(Pos.CENTER);
        lblTitle.setMinSize(width, 35);

        //AQUI TODOS LOS ELEMENTOS DE PARA LA INSERCION
        GridPane grid = new GridPane();
        grid.setHgap(40);
        grid.setVgap(15);
        grid.setPadding(new Insets(8, 8, 8, 8));
        ObservableList<Node> content = grid.getChildren();

        GridPane.setConstraints(lblPeriodo, 0, 0);
        GridPane.setHalignment(lblPeriodo, HPos.LEFT);
        content.add(lblPeriodo);

        cbInsertPeriodo = new ComboBox<>();
        cbInsertPeriodo.setMinWidth(250);
        GridPane.setConstraints(cbInsertPeriodo, 0, 1);
        GridPane.setHalignment(cbInsertPeriodo, HPos.LEFT);
        content.add(cbInsertPeriodo);

        GridPane.setConstraints(lblDocente, 1, 0);
        GridPane.setHalignment(lblDocente, HPos.LEFT);
        content.add(lblDocente);

        cbInsertDocente = new ComboBox<>();
        cbInsertDocente.setMinWidth(250);
        GridPane.setConstraints(cbInsertDocente, 1, 1);
        GridPane.setHalignment(cbInsertDocente, HPos.LEFT);
        content.add(cbInsertDocente);

        GridPane.setConstraints(lblGrado, 0, 2);
        GridPane.setHalignment(lblGrado, HPos.LEFT);
        content.add(lblGrado);

        tfInsertGrado = new TextField();
        GridPane.setConstraints(tfInsertGrado, 0, 3);
        GridPane.setHalignment(tfInsertGrado, HPos.LEFT);
        content.add(tfInsertGrado);

        GridPane.setConstraints(lblParalelo, 1, 2);
        GridPane.setHalignment(lblParalelo, HPos.LEFT);
        content.add(lblParalelo);

        tfInsertParalelo = new TextField();
        GridPane.setConstraints(tfInsertParalelo, 1, 3);
        GridPane.setHalignment(tfInsertParalelo, HPos.LEFT);
        content.add(tfInsertParalelo);

        fullCombobox();
        
        tvInsertMateria = new TableView<>();
        tvInsertMateria.setEditable(true);

        TableColumn<Materia, String> colNombre = new TableColumn<>("Nombre");
        TableColumn<Materia, String> colDominio = new TableColumn<>("Dominio");
        TableColumn<Materia, Boolean> colSelected = new TableColumn<>("Single?");

        

        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colDominio.setCellValueFactory(new PropertyValueFactory<>("dominio"));
        
         colSelected.setCellValueFactory((TableColumn.CellDataFeatures<Materia, Boolean> param) -> {
            Materia materia = param.getValue();

            SimpleBooleanProperty booleanProp = new SimpleBooleanProperty();

            // Note: singleCol.setOnEditCommit(): Not work for
            // CheckBoxTableCell.
            // When "Single?" column change.
            booleanProp.addListener(new ChangeListener<Boolean>() {

                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue,
                        Boolean newValue) {
                    //person.setSingle(newValue);
                }
            });
            return booleanProp;
        });

        colSelected.setCellFactory(new Callback<TableColumn<Materia, Boolean>, TableCell<Materia, Boolean>>() {
            @Override
            public TableCell<Materia, Boolean> call(TableColumn<Materia, Boolean> p) {
                CheckBoxTableCell<Materia, Boolean> cell = new CheckBoxTableCell<>();
                cell.setAlignment(Pos.CENTER);
                return cell;
            }
        });


       

        try {
            tvInsertMateria.setItems(mma.obtener());
        } catch (Exception e) {
        }
        tvInsertMateria.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tvInsertMateria.autosize();

        tvInsertMateria.getColumns().addAll(colNombre, colDominio,colSelected);
        

        HBox buttonsPane = new HBox(25);
        buttonsPane.setAlignment(Pos.CENTER);
        Button btnInsertCurso = new Button("Aceptar");
        btnInsertCurso.getStyleClass().add("btn-green");
        btnInsertCurso.setDefaultButton(true);
        btnInsertCurso.setMinSize(125, 30);
        btnInsertCurso.setOnAction(OkInsertActionEvent());

        Button btnCancel = new Button("Cancelar");
        btnCancel.setMinSize(125, 30);
        btnCancel.getStyleClass().add("btn-silver");
        btnCancel.setOnAction(NoInsertActionEvent());

        buttonsPane.getChildren().addAll(btnCancel, btnInsertCurso);

        boxInsert.getChildren().addAll(lblTitle,
                //                lblPeriodo, cbInsertPeriodo,
                //                lblDocente, cbInsertDocente,
                //                lblMateria, cbInsertMateria,
                //                lblGrado, tfInsertGrado,
                //                lblParalelo, tfInsertParalelo,
                grid,
                tvInsertMateria,
                buttonsPane);
        godPane.addInsertPane(boxInsert);

    }

    private void updatePanel(Curso curso) {
        int width = 300;
        VBox boxUpdate = new VBox(15);
        boxUpdate.setMaxSize(width, 300);
        boxUpdate.setPadding(new Insets(25));

        Label lblTitle = new Label("Modificar");
        lblTitle.setAlignment(Pos.CENTER);
        lblTitle.setMinSize(width, 35);

//AQUI TODOS LOS ELEMENTOS DE PARA LA MODIFICACION
        cbUpdateDocente = new ComboBox<>();
        cbUpdateDocente.setMinWidth(width);

        HBox buttonsPane = new HBox(25);
        buttonsPane.setAlignment(Pos.CENTER);
        Button btnInsertCurso = new Button("Aceptar");
        btnInsertCurso.getStyleClass().add("btn-green");
        btnInsertCurso.setDefaultButton(true);
        btnInsertCurso.setMinSize(125, 30);
        btnInsertCurso.setOnAction(OkUpdateActionEvent(curso));

        Button btnCancel = new Button("Cancelar");
        btnCancel.setMinSize(125, 30);
        btnCancel.getStyleClass().add("btn-silver");
        btnCancel.setOnAction(NoUpdateActionEvent());

        buttonsPane.getChildren().addAll(btnCancel, btnInsertCurso);

        boxUpdate.getChildren().addAll(lblTitle, lblDocente, cbUpdateDocente,/*Todos los componentes aqui*/ buttonsPane);
        godPane.addUpdatePane(boxUpdate);

    }

    private void showTable() {
        godPane.setTitle("");

        VBox boxTable = new VBox(15);
        boxTable.setPadding(new Insets(35));
        boxTable.setAlignment(Pos.CENTER_RIGHT);

        Button btnInsert = new Button("Insertar");
        btnInsert.getStyleClass().add("btn-green");
        btnInsert.setOnAction(insertActionEvent());
        table = new TableView<>();
        VBox.setVgrow(table, Priority.ALWAYS);
        table.setEditable(true);

        colPeriodo = new TableColumn<>("Periodo");
        colDocente = new TableColumn<>("Docente");
        colMateria = new TableColumn<>("Materia");
        colGrado = new TableColumn<>("Grado");
        colParalelo = new TableColumn<>("Paralelo");
        colAcciones = new TableColumn("Acciones");

        colPeriodo.setCellValueFactory(new PropertyValueFactory<>("periodo"));
        colDocente.setCellValueFactory(new PropertyValueFactory<>("docente"));
        colMateria.setCellValueFactory(new PropertyValueFactory<>("materia"));
        colGrado.setCellValueFactory(new PropertyValueFactory<>("grado"));
        colParalelo.setCellValueFactory(new PropertyValueFactory<>("paralelo"));

        colAcciones.setMinWidth(100);

        Callback<TableColumn<Curso, String>, TableCell<Curso, String>> cellFactory
                = (final TableColumn<Curso, String> param) -> {
                    final TableCell<Curso, String> cell = new TableCell<Curso, String>() {

                final CellButtons buttons = new CellButtons();

                @Override
                public void updateItem(String item, boolean empty) {

                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        Curso curso = getTableView().getItems().get(getIndex());

                        buttons.deleteAction(DeleteAtcionEvent(curso));
                        buttons.saveAction(updateActionEvent(curso));
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

        table.getColumns().addAll(colPeriodo, colDocente, colMateria, colGrado, colParalelo, colAcciones);
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
            Curso curso = null;
            if (true/*Comprobar si los componentes estan llenos*/) {
            } else {
                godPane.failed("Campos sin llenar");
            }
            if (curso != null) {
                try {
                    if (mc.insertar(curso) > 0) {
                        godPane.successful("Insertado Correctamente");
                        refreshTable();
                        godPane.hideInsertPane();
                    }
                } catch (Exception e) {
                    godPane.failed("Curso no se ha guardado");
                }
            }
        };

    }

    private EventHandler NoInsertActionEvent() {
        return (t) -> {
            godPane.hideInsertPane();
        };
    }

    private EventHandler OkUpdateActionEvent(Curso curso) {
        return (t) -> {
            if (true/*Verificar que los componentes esten vacios*/) {
            } else {
                godPane.failed("Campos sin llenar");
            }

            if (curso != null) {
                try {
                    if (mc.modificar(curso) > 0) {
                        godPane.successful("Insertado Correctamente");
                        refreshTable();
                        godPane.hideUpdatePane();
                    }
                } catch (Exception e) {
                    godPane.failed("Curso no se ha guardado");
                }
            }
        };

    }

    private EventHandler NoUpdateActionEvent() {
        return (t) -> {
            godPane.hideUpdatePane();
        };
    }

    private EventHandler updateActionEvent(Curso curso) {
        return (t) -> {
            updatePanel(curso);
            godPane.showUpdatePane();
        };
    }

    private EventHandler DeleteAtcionEvent(Curso curso) {
        return (t) -> {
            godPane.showAlert(OkDeleteAtcionEvent(curso));

        };
    }

    private EventHandler OkDeleteAtcionEvent(Curso curso) {
        return (t) -> {
            try {
                if (mc.eliminar(curso) > 0) {
                    refreshTable();
                    godPane.successful("Curso eliminado.");
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
            table.setItems(mc.obtener());
        } catch (Exception e) {
            godPane.failed("No se ha podido refrescar la table");
        }
    }

    private void fullCombobox() {
        try {
            cbInsertPeriodo.setItems(mp.obtener());
            //cbUpdatePeriodo.setItems(mp.obtener());

        } catch (Exception e) {
            System.err.println("obtener periodo: " + e.getMessage());
        }
        try {
            cbInsertDocente.setItems(md.obtener());
            //cbUpdateDocente.setItems(md.obtener());

        } catch (Exception e) {
            System.err.println("obtener docente: " + e.getMessage());

        }

    }
}
