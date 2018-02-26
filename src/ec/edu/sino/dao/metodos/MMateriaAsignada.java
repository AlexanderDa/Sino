/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.sino.dao.metodos;

import ec.edu.sino.accesodatos.DBConnection;
import ec.edu.sino.accesodatos.DBObject;
import ec.edu.sino.dao.contrato.IMateriaAsignada;
import ec.edu.sino.negocios.entidades.Curso;
import ec.edu.sino.negocios.entidades.MateriaAsignada;
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
public class MMateriaAsignada implements IMateriaAsignada {

    private String usuario;
    private String clave;

    public MMateriaAsignada() {
    }

    
    public MMateriaAsignada(String usuario, String clave) {
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
    public int insertar(MateriaAsignada materiaAsignada) throws Exception {
        int modificados = 0;
        DBConnection connection = new DBConnection(usuario, clave);
        String sql = "INSERT INTO public.asignatura_curso( curso, materia) VALUES (?, ?);";
        List<DBObject> dbos = new ArrayList<>();
        dbos.add(new DBObject(1, materiaAsignada.getCurso().getId()));
        dbos.add(new DBObject(2, materiaAsignada.getMateria().getId()));
        if (materiaAsignada.getId() != 0) {
            sql = "INSERT INTO public.asignatura_curso( curso, materia, id) VALUES (?, ?, ?);";
            dbos.add(new DBObject(3, materiaAsignada.getId()));
        }

        try {
            modificados = connection.executeCommand(sql, dbos);
        } catch (Exception e) {
            //throw e;
            System.err.println("metodos: "+e.getMessage());
        }
        return modificados;
    }

    @Override
    public int modificar(MateriaAsignada materiaAsignada) throws Exception {
        int modificados = 0;
        DBConnection connection = new DBConnection(usuario, clave);
        String sql = "UPDATE public.asignatura_curso curso=?, materia=? WHERE id =?;";
        List<DBObject> dbos = new ArrayList<>();
        dbos.add(new DBObject(1, materiaAsignada.getCurso().getId()));
        dbos.add(new DBObject(2, materiaAsignada.getMateria().getId()));

        try {
            modificados = connection.executeCommand(sql, dbos);
        } catch (Exception e) {
            throw e;
        }
        return modificados;
    }

    @Override
    public int eliminar(MateriaAsignada materiaAsignada) throws Exception {
        int eliminados = 0;
        String sql = "DELETE FROM public.asignatura_curso WHERE id=?;";
        List<DBObject> dbos = new ArrayList<>();
        dbos.add(new DBObject(1, materiaAsignada.getId()));
        DBConnection con = new DBConnection(usuario, clave);
        try {
            eliminados = con.executeCommand(sql, dbos);
        } catch (Exception e) {
            throw e;
        }

        return eliminados;
    }

    @Override
    public MateriaAsignada obtener(int id) throws Exception {
        MateriaAsignada materiaAsignada = null;
        String sql = "SELECT id, curso, materia FROM public.asignatura_curso WHERE id=?;";
        List<DBObject> dbos = new ArrayList<>();
        dbos.add(new DBObject(1, id));
        DBConnection con = new DBConnection(usuario, clave);
        try {
            ResultSet rst = con.executeQuery(sql, dbos);
            while (rst.next()) {
                materiaAsignada = new MateriaAsignada();
                materiaAsignada.setId(rst.getInt("id"));
                materiaAsignada.setCurso(new MCurso(usuario, clave).obtener(rst.getInt("curso")));
                materiaAsignada.setMateria(new MMAteria(usuario, clave).obtener(rst.getInt("materia")));

            }

        } catch (SQLException e) {
            throw e;
        }
        return materiaAsignada;
    }

    @Override
    public ObservableList<MateriaAsignada> obtenerPorCurso(Curso curso) throws Exception {
        ObservableList<MateriaAsignada> lista = FXCollections.observableArrayList();
        String sql = "select id, curso, materia from  public.asignatura_curso where curso = ?";
         List<DBObject> dbos = new ArrayList<>();
        dbos.add(new DBObject(1, curso.getId()));
        DBConnection con = new DBConnection(usuario, clave);
        try {
            ResultSet rst = con.executeQuery(sql, dbos);
            while (rst.next()) {

                MateriaAsignada materiaAsignada = new MateriaAsignada();
                materiaAsignada.setId(rst.getInt(1));
                materiaAsignada.setCurso(new MCurso(usuario, clave).obtener(rst.getInt(2)));
                materiaAsignada.setMateria(new MMAteria(usuario, clave).obtener(rst.getInt(3)));
                lista.add(materiaAsignada);
            }

        } catch (SQLException e) {
           throw e;
            //System.err.println("Aqui es el error"+e.getMessage());
        }
        return lista;
    }
    
    
      @Override
    public ObservableList<MateriaAsignada> obtener() throws Exception {
        ObservableList<MateriaAsignada> lista = FXCollections.observableArrayList();
        String sql = "SELECT id, curso, materia FROM public.asignatura_curso;";
        DBConnection con = new DBConnection(usuario, clave);
        try {
            ResultSet rst = con.executeQuery(sql);
            while (rst.next()) {

                MateriaAsignada materiaAsignada = new MateriaAsignada();
                materiaAsignada.setId(rst.getInt("id"));
                materiaAsignada.setCurso(new MCurso(usuario, clave).obtener(rst.getInt("curso")));
                materiaAsignada.setMateria(new MMAteria(usuario, clave).obtener(rst.getInt("materia")));
                lista.add(materiaAsignada);
            }

        } catch (SQLException e) {
           throw e;
            //System.err.println("Aqui es el error"+e.getMessage());
        }
        return lista;
    }



}
