/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.sino.gui.componentes;

import ec.edu.sino.dao.metodos.MDocente;
import ec.edu.sino.dao.metodos.MPeriodo;
import ec.edu.sino.negocios.entidades.Docente;
import ec.edu.sino.negocios.entidades.Periodo;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**
 *
 * @author alexander
 */
public class SearchPane extends HBox {

    private final int width = 250;
    private final MDocente md = new MDocente();
    private final MPeriodo mp = new MPeriodo();

    private Docente docente = null;

    private ComboBox<Periodo> cbPeriodo;
    private TextField tfDocente;
    private VBox vbPeriodo;
    private VBox vbDocente;
    private Label lblDocente;

    public SearchPane() {
        setAlignment(Pos.CENTER);
        setSpacing(50);
        md.loginAdmin();
        mp.loginAdmin();
        docentePanel();
        periodoPanel();
    }

    private void docentePanel() {
        vbDocente = new VBox(15);
        vbDocente.setMinWidth(width);
        final Label lblTitle = new Label("Docente");
        tfDocente = new TextField();
        tfDocente.setPromptText("Cédula o Nombres");
        tfDocente.setOnKeyReleased(tfDocenteActionEvent());

        lblDocente = new Label();
        vbDocente.getChildren().addAll(lblTitle, tfDocente, lblDocente);

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

    /*
*   CREACION DE EVENTOS    
     */
    private EventHandler tfDocenteActionEvent() {
        return (t) -> {
            System.out.println(tfDocente.getText());
            try {
                if (!"".equals(tfDocente.getText())) {
                    docente = md.obtener(tfDocente.getText());
                    lblDocente.setText(docente.toString());
                } else {
                    lblDocente.setText("");
                }

            } catch (Exception e) {
            }
        };
    }

    public Docente getDocente() {
        return docente;
    }

    public Periodo getPeriodo() {
        return cbPeriodo.getSelectionModel().getSelectedItem();
    }

}
