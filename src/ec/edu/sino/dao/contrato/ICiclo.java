/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.sino.dao.contrato;

import ec.edu.sino.negocios.entidades.Ciclo;
import javafx.collections.ObservableList;

/**
 *
 * @author alexander
 */
public interface ICiclo {

    void loginAdmin();

    void loginProfesor();

    int insertar(Ciclo ciclo) throws Exception;

    int modificar(Ciclo ciclo) throws Exception;

    int eliminar(Ciclo ciclo) throws Exception;

    Ciclo obtener(String id) throws Exception;

    ObservableList<Ciclo> obtener() throws Exception;
}
