/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.sino.dao.contrato;

import ec.edu.sino.negocios.entidades.Curso;
import ec.edu.sino.negocios.entidades.Materia;
import ec.edu.sino.negocios.entidades.Parcial;
import javafx.collections.ObservableList;

/**
 *
 * @author alexander
 */
public interface IParcial {

    void loginAdmin();

    void loginProfesor();

    int insertar(Parcial parcial) throws Exception;

    int modificar(Parcial parcial) throws Exception;

    int eliminar(Parcial parcial) throws Exception;

    Parcial obtener(int id) throws Exception;

    ObservableList<Parcial> obtener(Curso curso, Materia materia, String quimestre, String descripcion) throws Exception;
}
