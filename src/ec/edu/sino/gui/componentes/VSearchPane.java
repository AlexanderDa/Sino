/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.sino.gui.componentes;

import ec.edu.sino.dao.metodos.MCurso;
import ec.edu.sino.dao.metodos.MDocente;
import ec.edu.sino.dao.metodos.MMateriaAsignada;
import ec.edu.sino.dao.metodos.MPeriodo;
import ec.edu.sino.negocios.entidades.Curso;
import ec.edu.sino.negocios.entidades.Docente;
import ec.edu.sino.negocios.entidades.MateriaAsignada;
import ec.edu.sino.negocios.entidades.Periodo;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author alexander
 */
public class VSearchPane extends VBox {

    private final int width = 300;
    private final MDocente md = new MDocente();
    private final MPeriodo mp = new MPeriodo();

    private Docente docente = null;
    private Curso curso = new Curso();

    private ComboBox<Periodo> cbPeriodo;
    private TextField tfDocente;
    private VBox vbPeriodo;
    private VBox vbDocente;
    private VBox vbDatos;

    private TextField tfShowCurso;
    private TextField tfShowDocente;

    public VSearchPane() {
        setAlignment(Pos.CENTER);
        setSpacing(20);
        md.loginAdmin();
        mp.loginAdmin();
        periodoPanel();
        docentePanel();
        datosPanel();
    }

    private void docentePanel() {
        vbDocente = new VBox(15);
        vbDocente.setMinWidth(width);
        final Label lblTitle = new Label("Docente");
        tfDocente = new TextField();
        tfDocente.setPromptText("Cédula o Nombres");
        tfDocente.setOnKeyReleased(tfDocenteActionEvent());
        vbDocente.getChildren().addAll(lblTitle, tfDocente);

        getChildren().add(vbDocente);

    }

    private void periodoPanel() {
        vbPeriodo = new VBox(15);
        vbPeriodo.setMinWidth(width);
        final Label lblTitle = new Label("Periodo academino");
        cbPeriodo = new ComboBox<>();
        cbPeriodo.setMinWidth(width);
        cbPeriodo.setPromptText("Periodo Academico");
        try {
            cbPeriodo.setItems(mp.obtener());
        } catch (Exception e) {
        }
        vbPeriodo.getChildren().addAll(lblTitle, cbPeriodo);
        getChildren().add(vbPeriodo);
    }

    private void datosPanel() {

        GridPane grid = new GridPane();
        grid.setMinWidth(width);
        grid.setHgap(4);
        grid.setVgap(6);
        grid.setPadding(new Insets(8));
        ObservableList<Node> content = grid.getChildren();

        final Label lblCurso = new Label("Curso");
        final Label lblDocente = new Label("Tutór");
        tfShowCurso = new TextField();
        tfShowDocente = new TextField();

        tfShowCurso.setEditable(false);
        tfShowDocente.setEditable(false);

        GridPane.setConstraints(lblCurso, 0, 0);
        GridPane.setHalignment(lblCurso, HPos.LEFT);
        content.add(lblCurso);

        GridPane.setConstraints(lblDocente, 0, 1);
        GridPane.setHalignment(lblDocente, HPos.LEFT);
        content.add(lblDocente);

        GridPane.setConstraints(tfShowCurso, 1, 0);
        GridPane.setHalignment(tfShowCurso, HPos.LEFT);
        content.add(tfShowCurso);

        GridPane.setConstraints(tfShowDocente, 1, 1);
        GridPane.setHalignment(tfShowDocente, HPos.LEFT);
        content.add(tfShowDocente);

        getChildren().add(grid);
    }

    /*
*   CREACION DE EVENTOS    
     */
    private EventHandler tfDocenteActionEvent() {
        return (t) -> {
            System.out.println(tfDocente.getText());
            try {
                docente = md.obtener(tfDocente.getText());
                MCurso mCurso = new MCurso();
                mCurso.loginAdmin();
                curso = mCurso.obtenerPorDocenteAndPeriodo(getDocente().getCedula(), getPeriodo().getId());
                if (curso != null) {
                    tfShowDocente.setText(docente.toString());
                    tfShowCurso.setText(curso.toString());
                } else {
                    tfShowDocente.setText("Docente no asignado");
                    tfShowCurso.setText("No existe curso");
                    docente = null;
                }

            } catch (Exception e) {
            }
        };
    }

    private Docente getDocente() {
        return docente;
    }

    private Periodo getPeriodo() {
        return cbPeriodo.getSelectionModel().getSelectedItem();
    }

    public Curso getCurso() {
        return curso;
    }

    public List<MateriaAsignada> getListaDeMaterias() {
        List<MateriaAsignada> lista = new ArrayList<>();
        MMateriaAsignada mAsignada = new MMateriaAsignada();
        mAsignada.loginAdmin();
        try {
            lista = mAsignada.obtenerPorCurso(curso);
        } catch (Exception e) {
        }
        for(MateriaAsignada tmp: lista){
            System.out.println(tmp.getMateria());
        }
        
        return lista;
    }

    public boolean exists() {
        return curso != null;
    }
}
