/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.sino.dao.metodos;

import ec.edu.sino.accesodatos.DBConnection;
import ec.edu.sino.accesodatos.DBObject;
import ec.edu.sino.dao.contrato.ICurso;
import ec.edu.sino.negocios.entidades.Curso;
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
public class MCurso implements ICurso {

    private String usuario;
    private String clave;

    public MCurso() {
    }

    public MCurso(String usuario, String clave) {
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
    public int insertar(Curso curso) throws Exception {
        int modificados = 0;
        DBConnection connection = new DBConnection(usuario, clave);
        String sql = "INSERT INTO public.curso(periodo, docente, materia, grado, paralelo) VALUES (?, ?, ?, ?, ?);";
        List<DBObject> dbos = new ArrayList<>();
        dbos.add(new DBObject(1, curso.getPeriodo().getId()));
        dbos.add(new DBObject(2, curso.getDocente().getCedula()));
        dbos.add(new DBObject(3, curso.getMateria().getId()));
        dbos.add(new DBObject(4, curso.getGrado()));
        dbos.add(new DBObject(5, curso.getParalelo()));
        try {
            modificados = connection.executeCommand(sql, dbos);
        } catch (Exception e) {
            throw e;
        }
        return modificados;
    }

    @Override
    public int modificar(Curso curso) throws Exception {
        int modificados = 0;
        DBConnection connection = new DBConnection(usuario, clave);
        String sql = "UPDATE public.curso SET  periodo=?, docente=?, materia=?, grado=?, paralelo=? WHERE id=?;";
        List<DBObject> dbos = new ArrayList<>();
        dbos.add(new DBObject(1, curso.getPeriodo().getId()));
        dbos.add(new DBObject(2, curso.getDocente().getCedula()));
        dbos.add(new DBObject(3, curso.getMateria().getId()));
        dbos.add(new DBObject(4, curso.getGrado()));
        dbos.add(new DBObject(5, curso.getParalelo()));
        dbos.add(new DBObject(6, curso.getId()));

        try {
            modificados = connection.executeCommand(sql, dbos);
        } catch (Exception e) {
            throw e;
        }
        return modificados;
    }

    @Override
    public int eliminar(Curso curso) throws Exception {
        int eliminados = 0;
        String sql = "DELETE FROM publiccurso WHERE id=?;";
        List<DBObject> dbos = new ArrayList<>();
        dbos.add(new DBObject(1, curso.getId()));
        DBConnection con = new DBConnection(usuario, clave);
        try {
            eliminados = con.executeCommand(sql, dbos);
        } catch (Exception e) {
            throw e;
        }

        return eliminados;
    }

    @Override
    public Curso obtener(int id) throws Exception {
        Curso curso = null;
        String sql = "SELECT id, periodo, docente, materia, grado, paralelo FROM public.curso WHERE id=?;";
        List<DBObject> dbos = new ArrayList<>();
        dbos.add(new DBObject(1, id));
        DBConnection con = new DBConnection(usuario, clave);
        try {
            ResultSet rst = con.executeQuery(sql, dbos);
            while (rst.next()) {
                curso = new Curso();
                curso.setId(rst.getInt("id"));
                try {
                    curso.setPeriodo(new MPeriodo(usuario, clave).obtener(rst.getInt("periodo")));
                } catch (Exception e) {
                    System.err.println("Periodo" + e.getMessage());
                }
                try {
                    curso.setDocente(new MDocente(usuario, clave).obtener(rst.getString("docente")));
                } catch (Exception e) {
                    System.err.println("Docente " + e.getMessage());
                }
                try {
                    curso.setMateria(new MMAteria(usuario, clave).obtener(rst.getInt("materia")));
                } catch (Exception e) {
                    System.err.println("Materia " + e.getMessage());
                }

                curso.setGrado(rst.getString("grado"));
                curso.setParalelo(rst.getString("paralelo"));
            }

        } catch (SQLException e) {
            throw e;
        }
        return curso;
    }

    @Override
    public ObservableList<Curso> obtener() throws Exception {
        ObservableList<Curso> lista = FXCollections.observableArrayList();
        String sql = "by apellido asc;";
        DBConnection con = new DBConnection(usuario, clave);
        try {
            ResultSet rst = con.executeQuery(sql);
            while (rst.next()) {

                Curso curso = new Curso();
                lista.add(curso);
            }

        } catch (SQLException e) {
            throw e;
        }
        return lista;
    }
}
