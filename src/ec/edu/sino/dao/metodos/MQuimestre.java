/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.sino.dao.metodos;

import ec.edu.sino.accesodatos.DBConnection;
import ec.edu.sino.accesodatos.DBObject;
import ec.edu.sino.dao.contrato.IQuimestre;
import ec.edu.sino.negocios.entidades.Curso;
import ec.edu.sino.negocios.entidades.Materia;
import ec.edu.sino.negocios.entidades.Quimestre;
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
public class MQuimestre implements IQuimestre {

    private String usuario;
    private String clave;

    public MQuimestre() {
    }

    public MQuimestre(String usuario, String clave) {
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
    public int insertar(Quimestre quimestre) throws Exception {
        int modificados = 0;
        DBConnection connection = new DBConnection(usuario, clave);
        String sql = "INSERT INTO public.quimestre("
                + "id, ciclo, descripcion, promedio_parcial,"
                + "quimestral, laborados, justificados, injustificados, atrasos) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
        List<DBObject> dbos = new ArrayList<>();
        dbos.add(new DBObject(1, quimestre.getId()));
        dbos.add(new DBObject(2, quimestre.getCiclo().getId()));
        dbos.add(new DBObject(3, quimestre.getDescripcion()));
        dbos.add(new DBObject(4, quimestre.getPromedioParcial()));
        dbos.add(new DBObject(5, quimestre.getQuimestral()));
        dbos.add(new DBObject(6, quimestre.getLaborado()));
        dbos.add(new DBObject(7, quimestre.getJustificados()));
        dbos.add(new DBObject(8, quimestre.getInjustificados()));
        dbos.add(new DBObject(9, quimestre.getAtrasos()));

        try {
            modificados = connection.executeCommand(sql, dbos);
        } catch (Exception e) {
            throw e;
        }
        return modificados;
    }

    @Override
    public int modificar(Quimestre quimestre) throws Exception {
        int modificados = 0;
        DBConnection connection = new DBConnection(usuario, clave);
        String sql = "UPDATE public.quimestre SET  "
                + "ciclo=?, descripcion=?, quimestral=?, "
                + "laborados=?, justificados=?, injustificados=?, atrasos=? WHERE id=?;";
        List<DBObject> dbos = new ArrayList<>();
        dbos.add(new DBObject(1, quimestre.getCiclo().getId()));
        dbos.add(new DBObject(2, quimestre.getDescripcion()));
        dbos.add(new DBObject(3, quimestre.getQuimestral()));
        dbos.add(new DBObject(4, quimestre.getLaborado()));
        dbos.add(new DBObject(5, quimestre.getJustificados()));
        dbos.add(new DBObject(6, quimestre.getInjustificados()));
        dbos.add(new DBObject(7, quimestre.getAtrasos()));
        dbos.add(new DBObject(8, quimestre.getId()));

        try {
            modificados = connection.executeCommand(sql, dbos);
        } catch (Exception e) {
            throw e;
        }
        return modificados;
    }

    @Override
    public int eliminar(Quimestre quimestre) throws Exception {
        int eliminados = 0;
        String sql = "DELETE FROM public.quimestre WHERE id=?;";
        List<DBObject> dbos = new ArrayList<>();
        dbos.add(new DBObject(1, quimestre.getId()));
        DBConnection con = new DBConnection(usuario, clave);
        try {
            eliminados = con.executeCommand(sql, dbos);
        } catch (Exception e) {
            throw e;
        }

        return eliminados;
    }

    @Override
    public Quimestre obtener(int id) throws Exception {
        Quimestre quimestre = null;
        String sql = "SELECT id, ciclo, descripcion, promedio_parcial, nota_parcial, "
                + "quimestral, nota_quimestral, promedio, nota_cualitativa, laborados, "
                + "justificados, injustificados, atrasos FROM public.quimestre WHERE id=?;";
        List<DBObject> dbos = new ArrayList<>();
        dbos.add(new DBObject(1, id));
        DBConnection con = new DBConnection(usuario, clave);
        try {
            ResultSet rst = con.executeQuery(sql, dbos);
            while (rst.next()) {

                quimestre = new Quimestre();
                quimestre.setId(rst.getInt("id"));
                quimestre.setCiclo(new MCiclo(usuario, clave).obtener(rst.getInt("ciclo")));
                quimestre.setDescripcion(rst.getString("descripcion"));
                quimestre.setPromedioParcial(rst.getFloat("promedio_parcial"));
                quimestre.setNotaParcial(rst.getFloat("nota_parcial"));
                quimestre.setQuimestral(rst.getFloat("quimestral"));
                quimestre.setNotaQuimestral(rst.getFloat("nota_quimestral"));
                quimestre.setPromedio(rst.getFloat("promedio"));
                quimestre.setNotaCualitativa(rst.getString("nota_cualitativa"));
                quimestre.setLaborado(rst.getInt("laborados"));
                quimestre.setJustificados(rst.getInt("justificados"));
                quimestre.setInjustificados(rst.getInt("injustificados"));
                quimestre.setAtrasos(rst.getInt("atrasos"));
            }

        } catch (SQLException e) {
            throw e;
        }
        return quimestre;
    }

    

    @Override
    public ObservableList<Quimestre> obtener(Curso curso, Materia materia, String descripcion) throws Exception {
        ObservableList<Quimestre> lista = FXCollections.observableArrayList();
        String sql = "select q.id, q.ciclo, q.descripcion, q.promedio_parcial, q.nota_parcial, q.quimestral,"
                + "	q.nota_quimestral, q.promedio, q.nota_cualitativa,  q.laborados,"
                + "	q.justificados, q.injustificados, q.atrasos	from quimestre q"
                + "	inner join ciclo ci on ci.id= q.ciclo"
                + "	inner join alumno al on ci.alumno = al.cedula"
                + "	inner join asignatura_curso ac on  ac.id=ci.asignatura_curso"
                + "	where ac.curso=? and ac.materia=? and q.descripcion=? order by al.apellido;";
        DBConnection con = new DBConnection(usuario, clave);
        List<DBObject> dbos = new ArrayList<>();
        dbos.add(new DBObject(1, curso.getId()));
        dbos.add(new DBObject(2, materia.getId()));
        dbos.add(new DBObject(3, descripcion));
        try {
            ResultSet rst = con.executeQuery(sql, dbos);
            while (rst.next()) {

                Quimestre quimestre = new Quimestre();
                quimestre.setId(rst.getInt("id"));
                quimestre.setCiclo(new MCiclo(usuario, clave).obtener(rst.getInt("ciclo")));
                quimestre.setDescripcion(rst.getString("descripcion"));
                quimestre.setPromedioParcial(rst.getFloat("promedio_parcial"));
                quimestre.setNotaParcial(rst.getFloat("nota_parcial"));
                quimestre.setQuimestral(rst.getFloat("quimestral"));
                quimestre.setNotaQuimestral(rst.getFloat("nota_quimestral"));
                quimestre.setPromedio(rst.getFloat("promedio"));
                quimestre.setNotaCualitativa(rst.getString("nota_cualitativa"));
                quimestre.setLaborado(rst.getInt("laborados"));
                quimestre.setJustificados(rst.getInt("justificados"));
                quimestre.setInjustificados(rst.getInt("injustificados"));
                quimestre.setAtrasos(rst.getInt("atrasos"));
                lista.add(quimestre);
            }

        } catch (SQLException e) {
            throw e;
        }
        return lista;
    }

}
