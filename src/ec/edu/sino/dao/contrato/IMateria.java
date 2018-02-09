/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.sino.dao.contrato;

import ec.edu.sino.negocios.entidades.Materia;
import javafx.collections.ObservableList;

/**
 *
 * @author alexander
 */
public interface IMateria {

    void loginAdmin();

    void loginProfesor();

    int insertar(Materia materia) throws Exception;

    int modificar(Materia materia) throws Exception;

    int eliminar(Materia materia) throws Exception;

    Materia obtener(int id) throws Exception;

    ObservableList<Materia> obtener() throws Exception;
}
