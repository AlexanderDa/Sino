/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.sino.dao.metodos;

import com.sun.org.apache.bcel.internal.generic.AALOAD;
import ec.edu.sino.accesodatos.DBConnection;
import ec.edu.sino.accesodatos.DBObject;
import ec.edu.sino.dao.contrato.IAlumno;
import ec.edu.sino.negocios.entidades.Alumno;
import ec.edu.sino.negocios.entidades.Docente;
import ec.edu.sino.negocios.entidades.Periodo;
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
public class MAlumno implements IAlumno {

    private String usuario;
    private String clave;

    public MAlumno() {
    }

    public MAlumno(String usuario, String clave) {
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
    public int insertar(Alumno alumno) throws Exception {
        int modificados = 0;
        DBConnection connection = new DBConnection(usuario, clave);
        String sql = "INSERT INTO public.alumno(cedula, apellido, nombre) VALUES (?, ?, ?);";
        List<DBObject> dbos = new ArrayList<>();
        dbos.add(new DBObject(1, alumno.getCedula()));
        dbos.add(new DBObject(2, alumno.getApellido()));
        dbos.add(new DBObject(3, alumno.getNombre()));
        try {
            modificados = connection.executeCommand(sql, dbos);
        } catch (Exception e) {
            throw e;
        }
        return modificados;
    }

    @Override
    public int modificar(Alumno alumno) throws Exception {
        int modificados = 0;
        DBConnection connection = new DBConnection(usuario, clave);
        String sql = "UPDATE public.alumno SET cedula=?, apellido=?, nombre=? where cedula = ?;";
        List<DBObject> dbos = new ArrayList<>();
        dbos.add(new DBObject(1, alumno.getCedula()));
        dbos.add(new DBObject(2, alumno.getApellido()));
        dbos.add(new DBObject(3, alumno.getNombre()));
        dbos.add(new DBObject(4, alumno.getCedula()));

        try {
            modificados = connection.executeCommand(sql, dbos);
        } catch (Exception e) {
            throw e;
        }
        return modificados;
    }

    @Override
    public int eliminar(Alumno alumno) throws Exception {
        int eliminados = 0;
        String sql = "DELETE FROM public.alumno	WHERE cedula = ?;";
        List<DBObject> dbos = new ArrayList<>();
        dbos.add(new DBObject(1, alumno.getCedula()));
        DBConnection con = new DBConnection(usuario, clave);
        try {
            eliminados = con.executeCommand(sql, dbos);
        } catch (Exception e) {
            throw e;
        }

        return eliminados;
    }

    @Override
    public Alumno obtener(String cedula) throws Exception {
        Alumno alumno = null;
        String sql = "SELECT cedula, apellido, nombre	FROM public.alumno where cedula = ?;";
        List<DBObject> dbos = new ArrayList<>();
        dbos.add(new DBObject(1, cedula));

        DBConnection con = new DBConnection(usuario, clave);
        try {
            ResultSet rst = con.executeQuery(sql, dbos);
            while (rst.next()) {
                alumno = new Alumno();
                alumno.setCedula(rst.getString("cedula"));
                alumno.setApellido(rst.getString("apellido"));
                alumno.setNombre(rst.getString("nombre"));

            }

        } catch (SQLException e) {
            throw e;
        }
        return alumno;
    }

    @Override
    public ObservableList<Alumno> obtenerCedula(String cedula) throws Exception {
        ObservableList<Alumno> lista = FXCollections.observableArrayList();
        String sql = "SELECT cedula, apellido, nombre	FROM public.alumno where cedula like ?;";
        List<DBObject> dbos = new ArrayList<>();
        dbos.add(new DBObject(1, cedula.concat("%")));
        
        DBConnection con = new DBConnection(usuario, clave);
        try {
            ResultSet rst = con.executeQuery(sql, dbos);
            while (rst.next()) {

                Alumno alumno = new Alumno();
                alumno.setCedula(rst.getString(1));
                alumno.setApellido(rst.getString(2));
                alumno.setNombre(rst.getString(3));
                lista.add(alumno);
            }

        } catch (SQLException e) {
            throw e;
        }
        return lista;

    }

    @Override
    public ObservableList<Alumno> obtenerNombre(String nombre) throws Exception {
       ObservableList<Alumno> lista = FXCollections.observableArrayList();
        String sql = "SELECT cedula, apellido, nombre	FROM public.alumno  where nombre like ? or apellido like ?;";
        List<DBObject> dbos = new ArrayList<>();
        dbos.add(new DBObject(1, nombre.concat("%")));
        dbos.add(new DBObject(2, nombre.concat("%")));
        
        DBConnection con = new DBConnection(usuario, clave);
        try {
            ResultSet rst = con.executeQuery(sql, dbos);
            while (rst.next()) {

                Alumno alumno = new Alumno();
                alumno.setCedula(rst.getString(1));
                alumno.setApellido(rst.getString(2));
                alumno.setNombre(rst.getString(3));
                lista.add(alumno);
            }

        } catch (SQLException e) {
            throw e;
        }
        return lista;

    }

    @Override
    public ObservableList<Alumno> obtenerTodos() throws Exception {
        ObservableList<Alumno> lista = FXCollections.observableArrayList();
        String sql = "SELECT cedula, apellido, nombre	FROM public.alumno order by apellido asc;";
        DBConnection con = new DBConnection(usuario, clave);
        try {
            ResultSet rst = con.executeQuery(sql);
            while (rst.next()) {

                Alumno alumno = new Alumno();
                alumno.setCedula(rst.getString(1));
                alumno.setApellido(rst.getString(2));
                alumno.setNombre(rst.getString(3));
                lista.add(alumno);
            }

        } catch (SQLException e) {
            throw e;
        }
        return lista;
    }

    @Override
    public ObservableList<Alumno> obtenerNomina(Docente docente, Periodo periodo) throws Exception {
        ObservableList<Alumno> lista = FXCollections.observableArrayList();
        String sql = "select cedula, apellido, nombre from nomina_curso where docente=? and periodo = ? order by apellido asc;";
        List<DBObject> dbos = new ArrayList<>();
        dbos.add(new DBObject(1, docente.getCedula()));
        dbos.add(new DBObject(2, periodo.getId()));
        DBConnection con = new DBConnection(usuario, clave);
        try {
            ResultSet rst = con.executeQuery(sql, dbos);
            while (rst.next()) {

                Alumno alumno = new Alumno();
                alumno.setCedula(rst.getString(1));
                alumno.setApellido(rst.getString(2));
                alumno.setNombre(rst.getString(3));
                lista.add(alumno);
            }

        } catch (SQLException e) {
            throw e;
        }
        return lista;
    }

}
