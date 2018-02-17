/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.sino.dao.test;

import ec.edu.sino.dao.metodos.MAlumno;
import ec.edu.sino.negocios.entidades.Alumno;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author paul
 */
public class TAlumno {

    @Test
    public void test() {
        MAlumno ma = new MAlumno();
        ma.loginAdmin();
        Alumno alumno = new Alumno();
        alumno.setCedula("234567890-1");
        alumno.setApellido("Perez");
        alumno.setNombre("Ivan");
        

        //INSERTAR
        int insertados = 0;
        try {
            insertados = ma.insertar(alumno);
            if (insertados != 0) {
                System.out.println("Insertado: " + alumno.toString());
            }
        } catch (Exception e) {
            System.err.println("Error al insertar: " + e.getMessage());
        }
        assertTrue(insertados > 0);

        //MODIFICAR
        int modificados = 0;
        try {
            alumno.setApellido("Paredes");
            modificados = ma.modificar(alumno);
            if (modificados != 0) {
                System.out.println("Modificado: " + alumno.toString());
            }
        } catch (Exception e) {
            System.err.println("Error al modificar: " + e.getMessage());
        }
        assertTrue(modificados > 0);

        //BUSCAR
        Alumno alu = null;
        try {
            alu = ma.obtener("234567890-1");
            System.out.println("Buscado: " + alu.toString());
        } catch (Exception e) {
            System.err.println("ERROR! al buscar " + e.getMessage());
        }
        assertTrue(alu != null);

        //LISTAR
        List<Alumno> lista = new ArrayList<>();
        try {
            lista = ma.obtenerTodos();
            System.out.println("LISTA DE TODOS LOS ALUMNOS REGISTRADOS");
            for (Alumno tmp : lista) {
                System.out.println("\t" + tmp.toString());
            }
        } catch (Exception e) {
            System.err.println("ERROR! al listar alumnos " + e.getMessage());
        }
        assertTrue(lista.size() > 0);

        //ELIMINAR
        int eliminados = 0;
        try {
            eliminados = ma.eliminar(alumno);
            if (eliminados > 0) {
                System.out.println("Eliminado: " + alumno.toString());
            }
        } catch (Exception e) {
        }
    }
}
