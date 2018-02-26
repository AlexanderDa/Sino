/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.sino.dao.contrato;

import ec.edu.sino.negocios.entidades.Curso;
import ec.edu.sino.negocios.entidades.MateriaAsignada;
import javafx.collections.ObservableList;

/**
 *
 * @author alexander
 */
public interface IMateriaAsignada {

    void loginAdmin();

    void loginProfesor();

    int insertar(MateriaAsignada materiaAsignada) throws Exception;

    int modificar(MateriaAsignada materiaAsignada) throws Exception;

    int eliminar(MateriaAsignada materiaAsignada) throws Exception;

    MateriaAsignada obtener(int id) throws Exception;

    ObservableList<MateriaAsignada> obtenerPorCurso(Curso curso) throws Exception;

    ObservableList<MateriaAsignada> obtener() throws Exception;
}
