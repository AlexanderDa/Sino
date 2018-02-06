/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.sino.dao.contrato;

import ec.edu.sino.negocios.entidades.Curso;
import javafx.collections.ObservableList;

/**
 *
 * @author alexander
 */
public interface ICurso {

    void loginAdmin();

    void loginProfesor();

    int insertar(Curso curso) throws Exception;

    int modificar(Curso curso) throws Exception;

    int eliminar(Curso curso) throws Exception;

    Curso obtener(String cedula) throws Exception;

    ObservableList<Curso> obtener() throws Exception;
}
