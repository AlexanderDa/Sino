/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.sino.dao.contrato;

import ec.edu.sino.negocios.entidades.Curso;
import ec.edu.sino.negocios.entidades.Materia;
import ec.edu.sino.negocios.entidades.Quimestre;
import javafx.collections.ObservableList;

/**
 *
 * @author alexander
 */
public interface IQuimestre {

    void loginAdmin();

    void loginProfesor();

    int insertar(Quimestre quimestre) throws Exception;

    int modificar(Quimestre quimestre) throws Exception;

    int eliminar(Quimestre quimestre) throws Exception;

    Quimestre obtener(int id) throws Exception;

    ObservableList<Quimestre> obtener(Curso curso, Materia materia, String descripcion) throws Exception;
}
