/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.sino.dao.metodos;

import ec.edu.sino.accesodatos.DBConnection;
import ec.edu.sino.accesodatos.DBObject;
import ec.edu.sino.dao.contrato.IAdministrador;
import ec.edu.sino.negocios.entidades.Administrador;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author alexander
 */
public class MAdministrador implements IAdministrador {

    private final String usuario = "admin";
    private final String clave = "adm!np4$";

    @Override
    public int insertar(Administrador administrador) throws Exception {
        int modificados = 0;
        DBConnection connection = new DBConnection(usuario,clave);
        String sql = "INSERT INTO administrador(usuario,clave,nombre,apellido) values(?,md5(?),?,?);";
        List<DBObject> dbos = new ArrayList<>();
        dbos.add(new DBObject(1, administrador.getUsuario()));
        dbos.add(new DBObject(2, administrador.getClave()));
        dbos.add(new DBObject(3, administrador.getNombre()));
        dbos.add(new DBObject(4, administrador.getApellido()));
        try {
            modificados = connection.executeCommand(sql, dbos);
        } catch (Exception e) {
            throw e;
        }
        return modificados;
    }

    @Override
    public int modificar(Administrador administrador) throws Exception {
        int modificados = 0;
        DBConnection connection = new DBConnection(usuario,clave);
        String sql = "update administrador  set usuario = ?  nombre = ?, apellido = ? where id = ?;";
        List<DBObject> dbos = new ArrayList<>();

        dbos.add(new DBObject(1, administrador.getUsuario()));
        dbos.add(new DBObject(2, administrador.getNombre()));
        dbos.add(new DBObject(3, administrador.getApellido()));
        dbos.add(new DBObject(4, administrador.getId()));
        try {
            modificados = connection.executeCommand(sql, dbos);
        } catch (Exception e) {
            throw e;
        }
        return modificados;
    }

    @Override
    public int modificarClave(Administrador administrador) throws Exception {
        int modificados = 0;
        DBConnection connection = new DBConnection(usuario,clave);
        String sql = "update administrador set clave = ? where id = ?;";
        List<DBObject> dbos = new ArrayList<>();
        dbos.add(new DBObject(1, administrador.getClave()));
        dbos.add(new DBObject(2, administrador.getId()));
        try {
            modificados = connection.executeCommand(sql, dbos);
        } catch (Exception e) {
            throw e;
        }
        return modificados;
    }

    @Override
    public int eliminar(Administrador administrador) throws Exception {
        int eliminados = 0;
        String sql = "DELETE FROM administrador WHERE id=?;";
        List<DBObject> dbos = new ArrayList<>();
        dbos.add(new DBObject(1, administrador.getId()));
        DBConnection con = new DBConnection(usuario,clave);
        try {
            eliminados = con.executeCommand(sql, dbos);
        } catch (Exception e) {
            throw e;
        }

        return eliminados;
    }

    @Override
    public Administrador obtener(String usuario, String clave) throws Exception {
        Administrador administrador = null;
        String sql = "SELECT id,usuario,clave,nombre,apellido FROM administrador where usuario = ? and clave = md5(?);";
        List<DBObject> dbos = new ArrayList<>();
        dbos.add(new DBObject(1, usuario));
        dbos.add(new DBObject(2, clave));
        DBConnection con = new DBConnection(usuario,clave);
        try {
            ResultSet rst = con.executeQuery(sql, dbos);
            while (rst.next()) {
                administrador = new Administrador();
                administrador.setId(rst.getInt(1));
                administrador.setUsuario(rst.getString(2));
                administrador.setClave(rst.getString(3));
                administrador.setNombre(rst.getString(4));
                administrador.setApellido(rst.getString(5));
            }

        } catch (SQLException e) {
            throw e;
        }
        return administrador;
    }

    @Override
    public ObservableList<Administrador> obtener() throws Exception {
        ObservableList<Administrador> lista = FXCollections.observableArrayList();
        String sql = "SELECT id,usuario,clave,nombre,apellido FROM administrador order by apellido asc;";
        DBConnection con = new DBConnection(usuario,clave);
        try {
            ResultSet rst = con.executeQuery(sql);
            while (rst.next()) {

                Administrador administrador = new Administrador();
                administrador.setId(rst.getInt(1));
                administrador.setUsuario(rst.getString(2));
                administrador.setClave(rst.getString(3));
                administrador.setNombre(rst.getString(4));
                administrador.setApellido(rst.getString(5));
                lista.add(administrador);
            }

        } catch (SQLException e) {
            throw e;
        }
        return lista;
    }
    
}
