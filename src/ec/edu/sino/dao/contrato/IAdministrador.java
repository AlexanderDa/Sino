/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.sino.dao.contrato;

import ec.edu.sino.negocios.entidades.Administrador;
import javafx.collections.ObservableList;

/**
 *
 * @author alexander
 */
public interface IAdministrador {

    int insertar(Administrador administrador) throws Exception;

    int modificar(Administrador administrador) throws Exception;

    int modificarClave(Administrador administrador) throws Exception;

    int eliminar(Administrador administrador) throws Exception;

    Administrador obtener(String usuario, String clave) throws Exception;

    ObservableList<Administrador> obtener() throws Exception;
}
