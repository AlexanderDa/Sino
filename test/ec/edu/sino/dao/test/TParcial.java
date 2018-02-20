/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.sino.dao.test;

import ec.edu.sino.dao.metodos.MParcial;
import ec.edu.sino.negocios.entidades.Parcial;
import ec.edu.sino.negocios.entidades.Quimestre;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author paul
 */
public class TParcial {

    @Test

    public void test() {

        MParcial mp = new MParcial();
        mp.loginAdmin();

        Quimestre q = new Quimestre();
        Parcial p = new Parcial();

        q.setId(7);

        p.setId(100);
        p.setDescripcion("PRIMER PARCIAL");
        p.setTarea(8);
        p.setGrupal(5);
        p.setIndividual(10);
        p.setNotaParcial(9);
        p.setQuimestre(q);

        //INSERTAR 
        int insertados = 0;
        try {
            insertados = mp.insertar(p);
            if (insertados != 0) {
                System.err.println("Insertado: " + p.toString());
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        assertTrue(insertados > 0);
        //MODIFICAR
        int modificados = 0;
        try {
            p.setNotaParcial(3);
            modificados = mp.modificar(p);
            if (modificados != 0) {
                System.err.println("Modificado: " + p.toString());
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        assertTrue(modificados > 0);
        
        //OBTENER
        Parcial parcial = null;
        try {
            parcial = mp.obtener(100);
            System.out.println("Buscado: " + parcial.toString());
        } catch (Exception e) {
            System.err.println("ERROR! al buscar " + e.getMessage());
        }
        assertTrue(parcial != null);

        //LISTAR
        List<Parcial> lista = new ArrayList<>();
        try {
            lista = mp.obtener();
            System.out.println("LISTA DE PARCIALES");
            for (Parcial tmp : lista) {
                System.out.println("\t" + tmp.toString());
            }
        } catch (Exception e) {
            System.err.println("ERROR! al listar los parciales " + e.getMessage());
        }
        assertTrue(lista.size() > 0);

        //ELIMINAR
        int eliminados = 0;
        try {
            eliminados = mp.eliminar(p);
            if (eliminados != 0) {
                System.err.println("Eliminado: " + p.toString());
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        assertTrue(eliminados > 0);
    }

}
