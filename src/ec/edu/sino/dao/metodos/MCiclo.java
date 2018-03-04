/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.sino.dao.metodos;

import ec.edu.sino.accesodatos.DBConnection;
import ec.edu.sino.accesodatos.DBObject;
import ec.edu.sino.dao.contrato.ICiclo;
import ec.edu.sino.negocios.entidades.Ciclo;
import ec.edu.sino.negocios.entidades.Curso;
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
public class MCiclo implements ICiclo {

    private String usuario;
    private String clave;

    public MCiclo() {
    }

    public MCiclo(String usuario, String clave) {
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
    public int insertar(Ciclo ciclo) throws Exception {
        int modificados = 0;
        DBConnection connection = new DBConnection(usuario, clave);
        String sql = "INSERT INTO public.ciclo(asignatura_curso, alumno) VALUES (?, ?);";
        List<DBObject> dbos = new ArrayList<>();
        dbos.add(new DBObject(1, ciclo.getAsignada().getId()));
        dbos.add(new DBObject(2, ciclo.getAlumno().getCedula()));
        if (ciclo.getId() != 0) {
            sql = "INSERT INTO public.ciclo(masignatura_curso, alumno, id) VALUES (?, ?, ?);";
            dbos.add(new DBObject(3, ciclo.getId()));
        }

        try {
            modificados = connection.executeCommand(sql, dbos);
        } catch (Exception e) {
            throw e;
        }
        return modificados;
    }

    @Override
    public int modificar(Ciclo ciclo) throws Exception {
        int modificados = 0;
        DBConnection connection = new DBConnection(usuario, clave);
        String sql = "UPDATE public.ciclo set asignatura_curso=?, alumno=? WHERE id=?;";
        List<DBObject> dbos = new ArrayList<>();
        dbos.add(new DBObject(1, ciclo.getAsignada().getId()));
        dbos.add(new DBObject(2, ciclo.getAlumno().getCedula()));
        dbos.add(new DBObject(3, ciclo.getId()));

        try {
            modificados = connection.executeCommand(sql, dbos);
        } catch (Exception e) {
            throw e;
        }
        return modificados;
    }

    @Override
    public int eliminar(Ciclo ciclo) throws Exception {
        int eliminados = 0;
        String sql = "DELETE FROM public.ciclo WHERE id=?;";
        List<DBObject> dbos = new ArrayList<>();
        dbos.add(new DBObject(1, ciclo.getId()));
        DBConnection con = new DBConnection(usuario, clave);
        try {
            eliminados = con.executeCommand(sql, dbos);
        } catch (Exception e) {
            throw e;
        }

        return eliminados;
    }

    @Override
    public Ciclo obtener(int id) throws Exception {
        Ciclo ciclo = null;
        String sql = "SELECT id, asignatura_curso, alumno, promedio FROM public.ciclo WHERE id=?";
        List<DBObject> dbos = new ArrayList<>();
        dbos.add(new DBObject(1, id));
        DBConnection con = new DBConnection(usuario, clave);
        try {
            ResultSet rst = con.executeQuery(sql, dbos);
            while (rst.next()) {
                ciclo = new Ciclo();
                ciclo.setId(rst.getInt("id"));
                ciclo.setAlumno(new MAlumno(usuario, clave).obtener(rst.getString("alumno")));
                ciclo.setAsignada(new MMateriaAsignada(usuario, clave).obtener(rst.getInt("asignatura_curso")));
                ciclo.setPromedio(rst.getFloat("promedio"));

            }

        } catch (SQLException e) {
            throw e;
        }
        return ciclo;
    }
    //select distinct on(ci.alumno) ci.alumno,ci.id, ci.asignatura_curso, ci.promedio  from ciclo ci inner join asignatura_curso ac on  ac.id= ci.asignatura_curso where ac.curso=1;

    //PARA OBTENER LISTA DE ESTUDIANTES MATRICULADOS
    @Override
    public ObservableList<Ciclo> obtenerAlumnosMatriculados() throws Exception {
        ObservableList<Ciclo> lista = FXCollections.observableArrayList();
        String sql = "SELECT distinct on(alumno) alumno, id, asignatura_curso, promedio FROM public.ciclo;";
        DBConnection con = new DBConnection(usuario, clave);
        try {
            ResultSet rst = con.executeQuery(sql);
            while (rst.next()) {

                Ciclo ciclo = new Ciclo();
                MCurso mc = new MCurso();
                MAlumno ma = new MAlumno();
                ciclo.setId(rst.getInt("id"));
                ciclo.setAlumno(new MAlumno(usuario, clave).obtener(rst.getString("alumno")));
                ciclo.setAsignada(new MMateriaAsignada(usuario, clave).obtener(rst.getInt("asignatura_curso")));
                ciclo.setPromedio(rst.getFloat("promedio"));
                lista.add(ciclo);
            }

        } catch (SQLException e) {
            throw e;
        }
        return lista;
    }

    @Override
    public ObservableList<Ciclo> obtenerAlumnosMatriculados(Curso curso) throws Exception {
        ObservableList<Ciclo> lista = FXCollections.observableArrayList();
        String sql = "select distinct on(ci.alumno) ci.alumno,ci.id, ci.asignatura_curso, ci.promedio  from ciclo ci "
                + "inner join asignatura_curso ac on  ac.id=ci.asignatura_curso "
                + "where ac.curso=?;";
        List<DBObject> dbos = new ArrayList<>();
        dbos.add(new DBObject(1, curso.getId()));
        DBConnection con = new DBConnection(usuario, clave);
        try {
            ResultSet rst = con.executeQuery(sql, dbos);
            while (rst.next()) {

                Ciclo ciclo = new Ciclo();
                MCurso mc = new MCurso();
                MAlumno ma = new MAlumno();
                ciclo.setId(rst.getInt("id"));
                ciclo.setAlumno(new MAlumno(usuario, clave).obtener(rst.getString("alumno")));
                ciclo.setAsignada(new MMateriaAsignada(usuario, clave).obtener(rst.getInt("asignatura_curso")));
                ciclo.setPromedio(rst.getFloat("promedio"));
                lista.add(ciclo);
            }

        } catch (SQLException e) {
            throw e;
        }
        return lista;
    }
//PARA OBTETNER NOTAS DE LAS MATERIAS DE UN CURSO

    @Override
    public ObservableList<Ciclo> obtenerNotas(Curso curso) throws Exception {
        ObservableList<Ciclo> lista = FXCollections.observableArrayList();
        String sql = "select ci.alumno,ci.id, ci.asignatura_curso, ci.promedio  from ciclo ci "
                + "inner join asignatura_curso ac on  ac.id=ci.asignatura_curso "
                + "where ac.curso=?;";
        List<DBObject> dbos = new ArrayList<>();
        dbos.add(new DBObject(1, curso.getId()));
        DBConnection con = new DBConnection(usuario, clave);
        try {
            ResultSet rst = con.executeQuery(sql, dbos);
            while (rst.next()) {

                Ciclo ciclo = new Ciclo();
                MCurso mc = new MCurso();
                MAlumno ma = new MAlumno();
                ciclo.setId(rst.getInt("id"));
                ciclo.setAlumno(new MAlumno(usuario, clave).obtener(rst.getString("alumno")));
                ciclo.setAsignada(new MMateriaAsignada(usuario, clave).obtener(rst.getInt("asignatura_curso")));
                ciclo.setPromedio(rst.getFloat("promedio"));
                lista.add(ciclo);
            }

        } catch (SQLException e) {
            throw e;
        }
        return lista;
    }

    @Override
    public ObservableList<Ciclo> obtenerNotas(Curso curso, Materia materia) throws Exception {
        ObservableList<Ciclo> lista = FXCollections.observableArrayList();
        String sql = "select ci.alumno,ci.id, ci.asignatura_curso, ci.promedio  from ciclo ci "
                + "inner join asignatura_curso ac on  ac.id=ci.asignatura_curso "
                + "where ac.curso=? and ac.materia=?;";
        List<DBObject> dbos = new ArrayList<>();
        dbos.add(new DBObject(1, curso.getId()));
        dbos.add(new DBObject(2, materia.getId()));
        DBConnection con = new DBConnection(usuario, clave);
        try {
            ResultSet rst = con.executeQuery(sql, dbos);
            while (rst.next()) {

                Ciclo ciclo = new Ciclo();
                MCurso mc = new MCurso();
                MAlumno ma = new MAlumno();
                ciclo.setId(rst.getInt("id"));
                ciclo.setAlumno(new MAlumno(usuario, clave).obtener(rst.getString("alumno")));
                ciclo.setAsignada(new MMateriaAsignada(usuario, clave).obtener(rst.getInt("asignatura_curso")));
                ciclo.setPromedio(rst.getFloat("promedio"));
                lista.add(ciclo);
            }

        } catch (SQLException e) {
            throw e;
        }
        return lista;
    }

    public ObservableList<Materia> obtenerMaterias(Curso curso) throws Exception {

        ObservableList<Materia> lista = FXCollections.observableArrayList();
        String sql = "SELECT distinct on (m.id) m.id, m.nombre, m.dominio from ciclo c "
                + "inner join asignatura_curso ma on c.asignatura_curso = ma.id "
                + "inner join materia m on ma.materia = m.id "
                + "where ma.curso=?";
        List<DBObject> dbos = new ArrayList<>();
        dbos.add(new DBObject(1, curso.getId()));
        DBConnection con = new DBConnection(usuario, clave);
        try {
            ResultSet rst = con.executeQuery(sql, dbos);
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
