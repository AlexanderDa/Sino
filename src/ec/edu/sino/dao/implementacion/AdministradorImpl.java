/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.sino.dao.implementacion;

import ec.edu.sino.accesodatos.DBConnection;
import ec.edu.sino.accesodatos.DBObject;
import ec.edu.sino.dao.contrato.IAdministrador;
import ec.edu.sino.negocios.entidades.Administrador;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author paul
 */
public class AdministradorImpl implements IAdministrador{

    @Override
    public int insertar(Administrador administrador) throws SQLException, Exception
    {
        int numFilasAfectadas = 0;
        String sql = "INSERT INTO administrador(\n"
                + "            id, usuario,clave, nombre, apellido)\n"
                + "    VALUES (?, ?)";
        List<DBObject> lstPar = new ArrayList<>();
        lstPar.add(new DBObject(1, administrador.getId()));
        lstPar.add(new DBObject(2, administrador.getUsuario()));
        lstPar.add(new DBObject(3, administrador.getClave()));
        lstPar.add(new DBObject(4, administrador.getNombre()));
        lstPar.add(new DBObject(5, administrador.getApellido()));
        
        DBConnection con = new DBConnection();
        con.connect();
        try {
            numFilasAfectadas = con.ejecutaComando(sql, lstPar);
        } catch (Exception e) {
            throw e;
        } finally {
            con.disconnect();
        }
        return numFilasAfectadas;
        
    }

    @Override
    public int modificar(Administrador administrador) throws Exception {
        return 0;
    }

    @Override
    public int modificarClave(Administrador administrador) throws Exception {
    return 0;
    }

    @Override
    public int eliminar(Administrador administrador) throws Exception {
    return 0;
    }

    @Override
    public Administrador obtener(String usuario, String clave) throws Exception {
    return null;
    }

    @Override
    public ObservableList<Administrador> obtener() throws Exception {
        return null;
     
    }
    
}
