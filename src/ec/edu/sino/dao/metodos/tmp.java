/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.sino.dao.metodos;

import ec.edu.sino.negocios.entidades.Alumno;
import ec.edu.sino.negocios.entidades.Ciclo;
import ec.edu.sino.negocios.entidades.Curso;
import ec.edu.sino.negocios.entidades.Docente;
import ec.edu.sino.negocios.entidades.Materia;
import ec.edu.sino.negocios.entidades.Parcial;
import ec.edu.sino.negocios.entidades.Periodo;
import ec.edu.sino.negocios.entidades.Quimestre;

/**
 *
 * @author alexander
 */
public class tmp {

    public static void main(String[] args) {

        try {
            MPeriodo mp = new MPeriodo();
            mp.loginAdmin();
            Periodo p = mp.obtener(1);
            System.out.println(p.toString());
        } catch (Exception e) {
            System.err.println("Obtener Periodo: " + e.getMessage());
        }

        try {
            MDocente md = new MDocente();
            md.loginAdmin();
            Docente d = md.obtener("060405974-1");
            System.out.println(d.toString());
        } catch (Exception e) {
            System.err.println("Obtener Docente: " + e.getMessage());
        }

        try {
            MMAteria mm = new MMAteria();
            mm.loginAdmin();
            Materia m = mm.obtener(1);
            System.out.println(m.toString());
        } catch (Exception e) {
            System.err.println("Obtener materia: " + e.getMessage());
        }

        try {
            MCurso mc = new MCurso();
            mc.loginAdmin();
            Curso c = mc.obtener(1);
            System.out.println(c.toString());
        } catch (Exception e) {
            System.err.println("Obtener Curso: " + e.getMessage());
        }

        try {
            MAlumno ma = new MAlumno();
            ma.loginAdmin();
            Alumno a = ma.obtener("065838557-6");
            System.out.println(a.toString());
        } catch (Exception e) {
            System.err.println("Obtener alumno: " + e.getMessage());
        }

        try {
            MCiclo mc = new MCiclo();
            mc.loginAdmin();
            Ciclo c = mc.obtener(1);
            System.out.println(c.getPromedio());
        } catch (Exception e) {
            System.err.println("Obtener ciclo: " + e.getMessage());
        }
        
        
        try {
            MQuimestre mq = new MQuimestre();
            mq.loginAdmin();
            Quimestre q = mq.obtener(1);
            System.out.println(q.getPromedio());
        } catch (Exception e) {
            System.err.println("Obtener quimestre: " + e.getMessage());
        }
        
        
        try {
            MParcial mp = new MParcial();
            mp.loginAdmin();
            Parcial p = mp.obtener(1);
            System.out.println(p.getPromedio());
        } catch (Exception e) {
            System.err.println("Obtener parcial: " + e.getMessage());
        }
    }
}
