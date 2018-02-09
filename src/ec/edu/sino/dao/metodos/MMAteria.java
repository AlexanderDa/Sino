/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.sino.dao.metodos;

import ec.edu.sino.accesodatos.DBConnection;
import ec.edu.sino.accesodatos.DBObject;
import ec.edu.sino.dao.contrato.IMateria;
import ec.edu.sino.negocios.entidades.Materia;
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
public class MMAteria implements IMateria {

    private String usuario;
    private String clave;

    public MMAteria() {
    }

    public MMAteria(String usuario, String clave) {
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
    public int insertar(Materia materia) throws Exception {
        int modificados = 0;
        DBConnection connection = new DBConnection(usuario, clave);
        String sql = "INSERT INTO public.materia(nombre, dominio) VALUES (?, ?);";
        List<DBObject> dbos = new ArrayList<>();
        dbos.add(new DBObject(1, materia.getNombre()));
        dbos.add(new DBObject(2, materia.getDominio()));
        if (materia.getId() != 0) {
            sql = "INSERT INTO public.materia(nombre, dominio, id) VALUES (?, ?, ?);";
            dbos.add(new DBObject(3, materia.getId()));
        }

        try {
            modificados = connection.executeCommand(sql, dbos);
        } catch (Exception e) {
            throw e;
        }
        return modificados;
    }

    @Override
    public int modificar(Materia materia) throws Exception {
        int modificados = 0;
        DBConnection connection = new DBConnection(usuario, clave);
        String sql = "UPDATE public.materia set nombre=?, dominio=? WHERE id=?;";
        List<DBObject> dbos = new ArrayList<>();
        dbos.add(new DBObject(1, materia.getNombre()));
        dbos.add(new DBObject(2, materia.getDominio()));
        dbos.add(new DBObject(3, materia.getId()));
        

        try {
            modificados = connection.executeCommand(sql, dbos);
        } catch (Exception e) {
            throw e;
        }
        return modificados;
    }

    @Override
    public int eliminar(Materia materia) throws Exception {
        int eliminados = 0;
        String sql = "DELETE FROM public.materia WHERE id=?;";
        List<DBObject> dbos = new ArrayList<>();
        dbos.add(new DBObject(1, materia.getId()));
        DBConnection con = new DBConnection(usuario, clave);
        try {
            eliminados = con.executeCommand(sql, dbos);
        } catch (Exception e) {
            throw e;
        }

        return eliminados;
    }

    @Override
    public Materia obtener(int id) throws Exception {
        Materia materia = null;
        String sql = "SELECT id, nombre, dominio FROM public.materia WHERE id=?;";
        List<DBObject> dbos = new ArrayList<>();
        dbos.add(new DBObject(1, id));
        DBConnection con = new DBConnection(usuario, clave);
        try {
            ResultSet rst = con.executeQuery(sql, dbos);
            while (rst.next()) {
                materia = new Materia();
                materia.setId(rst.getInt("id"));
                materia.setNombre(rst.getString("nombre"));
                materia.setDominio(rst.getString("dominio"));

            }

        } catch (SQLException e) {
            throw e;
        }
        return materia;
    }

    @Override
    public ObservableList<Materia> obtener() throws Exception {
        ObservableList<Materia> lista = FXCollections.observableArrayList();
        String sql = "SELECT id, nombre, dominio FROM public.materia order by nombre asc;";
        DBConnection con = new DBConnection(usuario, clave);
        try {
            ResultSet rst = con.executeQuery(sql);
            while (rst.next()) {

                Materia materia = new Materia();
                materia.setId(rst.getInt("id"));
                materia.setNombre(rst.getString("nombre"));
                materia.setDominio(rst.getString("dominio"));
                lista.add(materia);
            }

        } catch (SQLException e) {
            throw e;
        }
        return lista;
    }

}
