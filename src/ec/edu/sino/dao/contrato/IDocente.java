/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.sino.dao.contrato;

import ec.edu.sino.negocios.entidades.Docente;
import javafx.collections.ObservableList;

/**
 *
 * @author alexander
 */
public interface IDocente {

    void loginAdmin();

    void loginProfesor();

    int insertar(Docente docente) throws Exception;

    int modificar(Docente docente) throws Exception;

    int modificarClave(Docente docente) throws Exception;

    int eliminar(Docente docente) throws Exception;

    Docente obtener(String usuario, String clave) throws Exception;

    ObservableList<Docente> obtener() throws Exception;
}
