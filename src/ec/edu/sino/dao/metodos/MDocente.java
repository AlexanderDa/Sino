/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.sino.dao.metodos;

import ec.edu.sino.accesodatos.DBConnection;
import ec.edu.sino.accesodatos.DBObject;
import ec.edu.sino.dao.contrato.IDocente;
import ec.edu.sino.negocios.entidades.Docente;
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
public class MDocente implements IDocente {

    private String usuario;
    private String clave;

    public MDocente() {
    }

    public MDocente(String usuario, String clave) {
        this.usuario = usuario;
        this.clave = clave;
    }

    @Override
    public void loginAdmin() {
        usuario = "admin";
        clave = "adm!np4$";
    }

    @Override
    public void loginProfesor() {
        usuario = "profesor";
        clave = "prop4$";
    }

    @Override
    public int insertar(Docente docente) throws Exception {
        int modificados = 0;
        DBConnection connection = new DBConnection(usuario, clave);
        String sql = "INSERT INTO docente(cedula,usuario,clave,nombre,apellido) values(?, ?, md5(?),?,?);";
        List<DBObject> prts = new ArrayList<>();
        prts.add(new DBObject(1, docente.getCedula()));
        prts.add(new DBObject(2, docente.getUsuario()));
        prts.add(new DBObject(3, docente.getClave()));
        prts.add(new DBObject(4, docente.getNombre()));
        prts.add(new DBObject(5, docente.getApellido()));
        try {
            modificados = connection.executeCommand(sql, prts);
        } catch (Exception e) {
            throw e;
        }
        return modificados;
    }

    @Override
    public int modificar(Docente docente) throws Exception {
        int modificados = 0;
        DBConnection connection = new DBConnection(usuario, clave);
        String sql = "update docente set  usuario = ?, nombre = ?, apellido = ? where cedula = ?;";
        List<DBObject> prts = new ArrayList<>();
        prts.add(new DBObject(1, docente.getUsuario()));
        prts.add(new DBObject(2, docente.getNombre()));
        prts.add(new DBObject(3, docente.getApellido()));
        prts.add(new DBObject(4, docente.getCedula()));
        try {
            modificados = connection.executeCommand(sql, prts);
        } catch (Exception e) {
            throw e;
        }
        return modificados;
    }

    @Override
    public int modificarClave(Docente docente) throws Exception {
        int modificados = 0;
        DBConnection connection = new DBConnection(usuario, clave);
        String sql = "update docente set  clave = md5(?) where cedula = ?;";
        List<DBObject> prts = new ArrayList<>();

        prts.add(new DBObject(1, docente.getClave()));
        prts.add(new DBObject(2, docente.getCedula()));
        try {
            modificados = connection.executeCommand(sql, prts);
        } catch (Exception e) {
            throw e;
        }
        return modificados;
    }

    @Override
    public int eliminar(Docente docente) throws Exception {
        int eliminados = 0;
        String sql = "DELETE FROM docente WHERE cedula=?;";
        List<DBObject> prts = new ArrayList<>();
        prts.add(new DBObject(1, docente.getCedula()));
        DBConnection con = new DBConnection(usuario, clave);
        try {
            eliminados = con.executeCommand(sql, prts);
        } catch (Exception e) {
            throw e;
        }

        return eliminados;
    }

    @Override
    public Docente obtenerCedula(String cedula) throws Exception {
        Docente docente = null;
        String sql = "SELECT cedula,usuario,clave,nombre,apellido FROM docente where cedula like ?;";
        List<DBObject> prts = new ArrayList<>();
        prts.add(new DBObject(1, cedula.concat("%")));
        DBConnection con = new DBConnection(usuario, this.clave);
        try {
            ResultSet rst = con.executeQuery(sql, prts);
            while (rst.next()) {
                docente = new Docente();
                docente.setCedula(rst.getString(1));
                docente.setUsuario(rst.getString(2));
                docente.setClave(rst.getString(3));
                docente.setNombre(rst.getString(4));
                docente.setApellido(rst.getString(5));
            }

        } catch (SQLException e) {
            throw e;
        }
        return docente;
    }

    @Override
    public Docente obtenerNombre(String nombre) throws Exception {
        Docente docente = null;
        String sql = "SELECT cedula,usuario,clave,nombre,apellido FROM docente where nombre like ? or apellido like ?;";
        List<DBObject> prts = new ArrayList<>();
        prts.add(new DBObject(1, nombre.concat("%")));
        prts.add(new DBObject(2, nombre.concat("%")));
        DBConnection con = new DBConnection(usuario, this.clave);
        try {
            ResultSet rst = con.executeQuery(sql, prts);
            while (rst.next()) {
                docente = new Docente();
                docente.setCedula(rst.getString(1));
                docente.setUsuario(rst.getString(2));
                docente.setClave(rst.getString(3));
                docente.setNombre(rst.getString(4));
                docente.setApellido(rst.getString(5));
            }

        } catch (SQLException e) {
            throw e;
        }
        return docente;
    }

    @Override
    public Docente obtener(String usuario, String clave) throws Exception {
        Docente docente = null;
        String sql = "SELECT cedula,usuario,clave,nombre,apellido FROM docente where usuario = ? and clave = md5(?);";
        List<DBObject> prts = new ArrayList<>();
        prts.add(new DBObject(1, usuario));
        prts.add(new DBObject(2, clave));
        DBConnection con = new DBConnection(this.usuario, this.clave);
        try {
            ResultSet rst = con.executeQuery(sql, prts);
            while (rst.next()) {
                docente = new Docente();
                docente.setCedula(rst.getString(1));
                docente.setUsuario(rst.getString(2));
                docente.setClave(rst.getString(3));
                docente.setNombre(rst.getString(4));
                docente.setApellido(rst.getString(5));
            }

        } catch (SQLException e) {
            throw e;
        }
        return docente;
    }

    @Override
    public ObservableList<Docente> obtener() throws Exception {
        ObservableList<Docente> lista = FXCollections.observableArrayList();
        String sql = "SELECT cedula,usuario,clave,nombre,apellido FROM docente order by apellido asc;";
        DBConnection con = new DBConnection(usuario, clave);
        try {
            ResultSet rst = con.executeQuery(sql);
            while (rst.next()) {

                Docente docente = new Docente();
                docente.setCedula(rst.getString(1));
                docente.setUsuario(rst.getString(2));
                docente.setClave(rst.getString(3));
                docente.setNombre(rst.getString(4));
                docente.setApellido(rst.getString(5));
                lista.add(docente);
            }

        } catch (SQLException e) {
            throw e;
        }
        return lista;
    }

}
