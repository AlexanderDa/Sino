/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.sino.dao.test;

import ec.edu.sino.dao.metodos.MAdministrador;
import ec.edu.sino.negocios.entidades.Administrador;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author alexander
 */
public class TAdministrador {

    public TAdministrador() {
    }

    @Test
    public void test() {
        MAdministrador ma = new MAdministrador();
        Administrador administrador = new Administrador();
        administrador.setId(1000);
        administrador.setUsuario("carlos");
        administrador.setClave("123");
        administrador.setNombre("carlos");
        administrador.setApellido("palacios");

        //INSERTAR
        int insertados = 0;
        try {
            insertados = ma.insertar(administrador);
            if (insertados != 0) {
                System.out.println("Insertado: " + administrador.toString());
            }
        } catch (Exception e) {
            System.err.println("Error al insertar: " + e.getMessage());
        }
        assertTrue(insertados > 0);

        //MODIFICAR
        int modificados = 0;
        try {
            administrador.setApellido("Paredes");
            modificados = ma.modificar(administrador);
            if (modificados != 0) {
                System.out.println("Modificado: " + administrador.toString());
            }
        } catch (Exception e) {
            System.err.println("Error al modificar: " + e.getMessage());
        }
        assertTrue(modificados > 0);

        //BUSCAR
        Administrador adm = null;
        try {
            adm = ma.obtener("carlos", "123");
            System.out.println("Buscado: " + adm.toString());
        } catch (Exception e) {
            System.err.println("ERROR! al buscar " + e.getMessage());
        }
        assertTrue(adm != null);

        //LISTAR
        List<Administrador> lista = new ArrayList<>();
        try {
            lista = ma.obtener();
            System.out.println("LISTA DE ADMINISTRADORES");
            for (Administrador tmp : lista) {
                System.out.println("\t" + tmp.toString());
            }
        } catch (Exception e) {
            System.err.println("ERROR! al listar administradores " + e.getMessage());
        }
        assertTrue(lista.size() > 0);

        //ELIMINAR
        int eliminados = 0;
        try {
            eliminados = ma.eliminar(administrador);
            if (eliminados > 0) {
                System.out.println("Eliminado: " + administrador.toString());
            }
        } catch (Exception e) {
        }
    }
}
