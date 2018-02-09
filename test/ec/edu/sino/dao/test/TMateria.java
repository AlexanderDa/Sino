/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.sino.dao.test;

import ec.edu.sino.dao.metodos.MMAteria;
import ec.edu.sino.negocios.entidades.Materia;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author paul
 */
public class TMateria {

    @Test
    public void test() {
        MMAteria mma = new MMAteria();
        mma.loginAdmin();
        Materia materia = new Materia();
        materia.setId(1000);
        materia.setNombre("Música");
        materia.setDominio("Habilidades");
        //INSERTAR
        int insertados = 0;
        try {
            insertados = mma.insertar(materia);
            if (insertados != 0) {
                System.out.println("Insertado: " + materia.toString());
            }
        } catch (Exception e) {
            System.err.println("Error al insertar: " + e.getMessage());
        }
        assertTrue(insertados > 0);

        //MODIFICAR
        int modificados = 0;
        try {
            materia.setDominio("Dominio musical");
            modificados = mma.modificar(materia);
            if (modificados != 0) {
                System.out.println("Modificado: " + materia.toString());
            }
        } catch (Exception e) {
            System.err.println("Error al modificar: " + e.getMessage());
        }
        assertTrue(modificados > 0);

        //BUSCAR
        Materia mate = null;
        try {
            mate = mma.obtener(1000);
            System.out.println("Buscado: " + mate.toString());
        } catch (Exception e) {
            System.err.println("ERROR! al buscar " + e.getMessage());
        }
        assertTrue(mate != null);

        //LISTAR
        List<Materia> lista = new ArrayList<>();
        try {
            lista = mma.obtener();
            System.out.println("LISTA DE ADMINISTRADORES");
            for (Materia tmp : lista) {
                System.out.println("\t" + tmp.toString());
            }
        } catch (Exception e) {
            System.err.println("ERROR! al listar materias " + e.getMessage());
        }
        assertTrue(lista.size() > 0);

        //ELIMINAR
        int eliminados = 0;
        try {
            eliminados = mma.eliminar(materia);
            if (eliminados > 0) {
                System.out.println("Eliminado: " + materia.toString());
            }
        } catch (Exception e) {
        }
    }
}
