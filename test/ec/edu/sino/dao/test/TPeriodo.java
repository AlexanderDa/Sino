/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.sino.dao.test;

import ec.edu.sino.dao.metodos.MPeriodo;
import ec.edu.sino.negocios.entidades.Materia;
import ec.edu.sino.negocios.entidades.Periodo;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author paul
 */
public class TPeriodo {

    @Test
    public void test() {
        MPeriodo mp = new MPeriodo();
        mp.loginAdmin();
        
        Periodo periodo = new Periodo();
        periodo.setId(1000);
        periodo.setFechaInicio("2018-1-1");
        periodo.setFechaFin("2019-5-20");
        periodo.setDirector("Director");
        periodo.setSubdirector("Subdirector");
        periodo.setCoordinador("Coordinador");
        
        int insertados = 0;
        try {
            insertados = mp.insertar(periodo);
            if (insertados != 0) {
                System.out.println("Insertado: " + periodo.toString());
            }
        } catch (Exception e) {
            System.err.println("Error al insertar: " + e.getMessage());
        }
        assertTrue(insertados > 0);

        //MODIFICAR
        int modificados = 0;
        try {
            periodo.setDirector("Director modificado");
            modificados = mp.modificar(periodo);
            if (modificados != 0) {
                System.out.println("Modificado: " + periodo.toString());
            }
        } catch (Exception e) {
            System.err.println("Error al modificar: " + e.getMessage());
        }
        assertTrue(modificados > 0);

        //BUSCAR
        Periodo mate = null;
        try {
            mate = mp.obtener(1000);
            System.out.println("Buscado: " + mate.toString());
        } catch (Exception e) {
            System.err.println("ERROR! al buscar " + e.getMessage());
        }
        assertTrue(mate != null);

        //LISTAR
        List<Periodo> lista = new ArrayList<>();
        try {
            lista = mp.obtener();
            System.out.println("LISTA DE PERIODO");
            for (Periodo tmp : lista) {
                System.out.println("\t" + tmp.toString());
            }
        } catch (Exception e) {
            System.err.println("ERROR! al listar periodos " + e.getMessage());
        }
        assertTrue(lista.size() > 0);

        //ELIMINAR
        int eliminados = 0;
        try {
            eliminados = mp.eliminar(periodo);
            if (eliminados > 0) {
                System.out.println("Eliminado: " + periodo.toString());
            }
        } catch (Exception e) {
        }
    }
}
