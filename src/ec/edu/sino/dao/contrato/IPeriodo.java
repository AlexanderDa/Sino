/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.sino.dao.contrato;

import ec.edu.sino.negocios.entidades.Periodo;
import javafx.collections.ObservableList;

/**
 *
 * @author alexander
 */
public interface IPeriodo {

    void loginAdmin();

    void loginProfesor();

    int insertar(Periodo periodo) throws Exception;

    int modificar(Periodo periodo) throws Exception;

    int eliminar(Periodo periodo) throws Exception;

    Periodo obtener(int id) throws Exception;

    Periodo obtenerActual() throws Exception;

    ObservableList<Periodo> obtener() throws Exception;
}
