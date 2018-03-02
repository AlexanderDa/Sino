/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.sino.dao.contrato;

import ec.edu.sino.negocios.entidades.Alumno;
import ec.edu.sino.negocios.entidades.Docente;
import ec.edu.sino.negocios.entidades.Periodo;
import javafx.collections.ObservableList;

/**
 *
 * @author alexander
 */
public interface IAlumno {

    void loginAdmin();

    void loginProfesor();

    int insertar(Alumno alumno) throws Exception;

    int modificar(Alumno alumno) throws Exception;

    int eliminar(Alumno alumno) throws Exception;

    Alumno obtener(String cedula) throws Exception;

    ObservableList<Alumno> obtenerDato(String dato) throws Exception;

    ObservableList<Alumno> obtenerTodos() throws Exception;

}
