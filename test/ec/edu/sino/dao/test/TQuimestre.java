/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.sino.dao.test;

import ec.edu.sino.dao.metodos.MQuimestre;
import ec.edu.sino.negocios.entidades.Ciclo;
import ec.edu.sino.negocios.entidades.Quimestre;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author paul
 */
public class TQuimestre {
    
    @Test
    
    public void test(){
        
        MQuimestre mq = new MQuimestre();
        mq.loginAdmin();
        
        Quimestre q = new Quimestre();
        Ciclo c = new Ciclo();
        
        c.setId(4);
        
        q.setAtrasos(0);
        q.setCiclo(c);
        q.setDescripcion("PRIMER QUIMESTRE");
        q.setId(16);
        q.setInjustificados(0);
        q.setJustificados(0);
        q.setLaborado(0);
        q.setPromedioParcial(8);
        q.setQuimestral(5);
        
        //INSERTAR
        int insertados=0;
        try {
            insertados=mq.insertar(q);
            if (insertados != 0) {
                System.err.println("Insertado: " + q.toString());
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        assertTrue(insertados > 0);
        
        //OBTENER
        Quimestre quimestre = null;
        try {
            quimestre = mq.obtener(16);
            System.out.println("Buscado: " + quimestre.toString());
        } catch (Exception e) {
            System.err.println("ERROR! al buscar " + e.getMessage());
        }
        assertTrue(quimestre != null);

        //LISTAR
        List<Quimestre> lista = new ArrayList<>();
        try {
            lista = mq.obtener();
            System.out.println("LISTA DE QUIMESTRES");
            for (Quimestre tmp : lista) {
                System.out.println("\t" + tmp.toString());
            }
        } catch (Exception e) {
            System.err.println("ERROR! al listar los quimestres " + e.getMessage());
        }
        assertTrue(lista.size() > 0);
        
         //ELIMINAR
        int eliminados = 0;
        try {
            eliminados = mq.eliminar(q);
            if (eliminados != 0) {
                System.err.println("Eliminado: " + q.toString());
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        assertTrue(eliminados > 0);
        
       
    }
    
}
