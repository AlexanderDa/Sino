/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.sino.dao.test;

import ec.edu.sino.dao.metodos.MCurso;
import ec.edu.sino.dao.metodos.MDocente;
import ec.edu.sino.dao.metodos.MPeriodo;
import ec.edu.sino.negocios.entidades.Curso;
import ec.edu.sino.negocios.entidades.Docente;
import ec.edu.sino.negocios.entidades.Periodo;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author paul
 */
public class TCurso {

    @Test
    public void test() {

        MCurso mc = new MCurso();

        mc.loginAdmin();

        Curso curso = new Curso();
        Docente docente = null;
        Periodo periodo = null;

        try {
            MDocente md = new MDocente();
            md.loginAdmin();
            docente = md.obtenerCedula("060405974-1");
            System.out.println("Docente " + docente.toString());
        } catch (Exception e) {
            System.err.println("Docente no encontrado");
        }

        try {
            MPeriodo mp = new MPeriodo();
            mp.loginAdmin();
            periodo = mp.obtener(1);
            System.out.println("Periodo: " + periodo.toString());
        } catch (Exception e) {
            System.err.println("Periodo no encontrado");
        }

        curso.setId(1000);
        curso.setDocente(docente);
        curso.setGrado("Primero");
        curso.setParalelo("C");
        curso.setPeriodo(periodo);

        //INSERTAR
        int insertados = 0;
        try {
            insertados = mc.insertar(curso);
            if (insertados != 0) {
                System.out.println("Insertado: " + curso.toString());
            }
        } catch (Exception e) {
            System.err.println("Error al insertar: " + e.getMessage());
        }
        assertTrue(insertados > 0);

        //MODIFICAR
        int modificados = 0;
        try {
            curso.setParalelo("D");
            modificados = mc.modificar(curso);
            if (modificados != 0) {
                System.out.println("Modificado: " + curso.toString());
            }
        } catch (Exception e) {
            System.err.println("Error al modificar: " + e.getMessage());
        }
        assertTrue(modificados > 0);

        //BUSCAR
        Curso cu = null;
        try {
            cu = mc.obtener(1000);
            System.out.println("Buscado: " + cu.toString());
        } catch (Exception e) {
            System.err.println("ERROR! al buscar " + e.getMessage());
        }
        assertTrue(cu != null);

        //LISTAR
        List<Curso> lista = new ArrayList<>();
        try {
            lista = mc.obtener();
            System.out.println("LISTA DE CURSOS");
            for (Curso tmp : lista) {
                System.out.println("\t" + tmp.toString());
            }
        } catch (Exception e) {
            System.err.println("ERROR! al listar los cursos " + e.getMessage());
        }
        assertTrue(lista.size() > 0);

        //ELIMINAR
//        int eliminados = 0;
//        try {
//            eliminados = mc.eliminar(curso);
//            if (eliminados > 0) {
//                System.out.println("Eliminado: " + curso.toString());
//            }
//        } catch (Exception e) {
//        }
//        assertTrue(eliminados > 0);

    }

}
