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
                ciclo.setAsignada(new MMateriaAsignada(usuario,clave).obtener(rst.getInt("asignatura_curso")));
                ciclo.setPromedio(rst.getFloat("promedio"));

            }

        } catch (SQLException e) {
            throw e;
        }
        return ciclo;
    }

    @Override
    public ObservableList<Ciclo> obtener() throws Exception {
        ObservableList<Ciclo> lista = FXCollections.observableArrayList();
        String sql = "SELECT id, asignatura_curso, alumno, promedio FROM public.ciclo;";
        DBConnection con = new DBConnection(usuario, clave);
        try {
            ResultSet rst = con.executeQuery(sql);
            while (rst.next()) {

                Ciclo ciclo = new Ciclo();
                MCurso mc = new MCurso();
                MAlumno ma = new MAlumno();
                ciclo.setId(rst.getInt("id"));
                ciclo.setAlumno(new MAlumno(usuario, clave).obtener(rst.getString("alumno")));
                ciclo.setAsignada(new MMateriaAsignada(usuario,clave).obtener(rst.getInt("asignatura_curso")));
                ciclo.setPromedio(rst.getFloat("promedio"));
                lista.add(ciclo);
            }

        } catch (SQLException e) {
            throw e;
        }
        return lista;
    }

}
