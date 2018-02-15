package ec.edu.sino.dao.test;

import ec.edu.sino.dao.metodos.MAlumno;
import ec.edu.sino.dao.metodos.MCiclo;
import ec.edu.sino.dao.metodos.MCurso;
import ec.edu.sino.negocios.entidades.Alumno;
import ec.edu.sino.negocios.entidades.Ciclo;
import ec.edu.sino.negocios.entidades.Curso;
import ec.edu.sino.negocios.entidades.Materia;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author paul
 */

public class TCiclo {
    
    @Test
    public void test() {
        MCiclo mc = new MCiclo();
        mc.loginAdmin();
        
        Ciclo ciclo = new Ciclo();
        Alumno alumno = new Alumno();
        Curso curso = new Curso();
        
        alumno.setCedula("456897345-7");
        alumno.setNombre("Adriano");
        alumno.setApellido("Inca");
        
        curso.setId(2);
        curso.setParalelo("A");
        curso.setGrado("Semptimo");

        ciclo.setId(12);
        ciclo.setAlumno(alumno);
        ciclo.setCurso(curso);
        ciclo.setPromedio(4);
        
        //INSERTAR
        int insertados = 0;
        try {
            insertados = mc.insertar(ciclo);
            if (insertados != 0) {
                System.out.println("Insertado: " + ciclo.toString());
            }
        } catch (Exception e) {
            System.err.println("Error al insertar: " + e.getMessage());
        }
        assertTrue(insertados > 0);
        
         //MODIFICAR
        int modificados = 0;
        try {  
            ciclo.setPromedio(5);
            modificados = mc.modificar(ciclo);
            System.out.println("Modificado: " + ciclo.toString());            
        } catch (Exception e) {
            System.err.println("Error al modificar: " + e.getMessage());
        }
        assertTrue(modificados > 0);
        
       
        //BUSCAR
        Ciclo ci = null;
        try {
            ci = mc.obtener(12);
            System.out.println("Buscado: " + ci.toString());
        } catch (Exception e) {
            System.err.println("ERROR! al buscar " + e.getMessage());
        }
        assertTrue(ci != null);

        //LISTAR
        List<Ciclo> lista = new ArrayList<>();
        try {
            lista = mc.obtener();
            System.out.println("LISTA DE CICLOS");
            for (Ciclo tmp : lista) {
                System.out.println("\t" + tmp.toString());
            }
        } catch (Exception e) {
            System.err.println("ERROR! al listar los ciclos " + e.getMessage());
        }
        assertTrue(lista.size() > 0);
       //ELIMINAR
        int eliminados = 0;
        try {
            eliminados = mc.eliminar(ciclo);
            if (eliminados > 0) {
                System.out.println("Eliminado: " + ciclo.toString());
            }
        } catch (Exception e) {
        }
        
        assertTrue(eliminados > 0);
        
        
        
        
    }
}
