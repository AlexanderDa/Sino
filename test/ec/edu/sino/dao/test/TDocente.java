/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.sino.dao.test;

import ec.edu.sino.dao.metodos.MDocente;
import ec.edu.sino.negocios.entidades.Docente;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author paul
 */
public class TDocente {

    @Test
    public void test() {
        MDocente md = new MDocente();
        md.loginAdmin();
        Docente docente = new Docente();
        docente.setCedula("123456789-0");
        docente.setUsuario("juanito");
        docente.setClave("123");
        docente.setNombre("Juan");
        docente.setApellido("Garcia");
        //INSERTAR
        int insertados = 0;
        try {
            insertados = md.insertar(docente);
            if (insertados != 0) {
                System.out.println("Insertado: " + docente.toString());
            }
        } catch (Exception e) {
            System.err.println("Error al insertar: " + e.getMessage());
        }
        assertTrue(insertados > 0);

        //MODIFICAR
        int modificados = 0;
        try {
            docente.setNombre("Modificado");
            modificados = md.modificar(docente);
            if (modificados != 0) {
                System.out.println("Modificado: " + docente.toString());
            }
        } catch (Exception e) {
            System.err.println("Error al modificar: " + e.getMessage());
        }
        assertTrue(modificados > 0);
        //CAMBIAR CONTRASENIA
        modificados = 0;
        try {
            docente.setClave("1234");
            modificados = md.modificarClave(docente);
            if (modificados != 0) {
                System.out.println("Contraseña modificada: " + docente.toString());
            }
        } catch (Exception e) {
            System.err.println("Error al modificar: " + e.getMessage());
        }
        assertTrue(modificados > 0);

        //BUSCAR POR CEDULA
        Docente doce = null;
        try {
            doce = md.obtenerCedula("123456789");
            System.out.println("Buscado por cedula: " + doce.toString());
        } catch (Exception e) {
            System.err.println("ERROR! al buscar por cedula " + e.getMessage());
        }
        assertTrue(doce != null);
        

         //BUSCAR USUARIO
        Docente doce2 = null;
        try {
            doce2 = md.obtener("juanito", "1234");
            System.out.println("Login usuario: " + doce2.toString());
        } catch (Exception e) {
            System.err.println("ERROR! en login usuario " + e.getMessage());
        }
        assertTrue(doce2 != null);

        //LISTAR
        List<Docente> lista = new ArrayList<>();
        try {
            lista = md.obtener();
            System.out.println("LISTA DE DOCENTES");
            for (Docente tmp : lista) {
                System.out.println("\t" + tmp.toString());
            }
        } catch (Exception e) {
            System.err.println("ERROR! al listar docentes " + e.getMessage());
        }
        assertTrue(lista.size() > 0);

        //ELIMINAR
        int eliminados = 0;
        try {
            eliminados = md.eliminar(docente);
            if (eliminados > 0) {
                System.out.println("Eliminado: " + docente.toString());
            }
        } catch (Exception e) {
        }
    }
}
