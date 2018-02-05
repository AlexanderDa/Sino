/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.sino.negocios.entidades;

/**
 *
 * @author alexander
 */
public class Curso {

    private int id;
    private Periodo periodo;
    private Docente docente;
    private Materia materia;
    private String grado;
    private String paralelo;

    public Curso() {
    }

    public Curso(int id, Periodo periodo, Docente docente, Materia materia, String grado, String paralelo) {
        this.id = id;
        this.periodo = periodo;
        this.docente = docente;
        this.materia = materia;
        this.grado = grado;
        this.paralelo = paralelo;
    }

    public String getParalelo() {
        return paralelo;
    }

    public void setParalelo(String paralelo) {
        this.paralelo = paralelo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Periodo getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Periodo periodo) {
        this.periodo = periodo;
    }

    public Docente getDocente() {
        return docente;
    }

    public void setDocente(Docente docente) {
        this.docente = docente;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public String getGrado() {
        return grado;
    }

    public void setGrado(String grado) {
        this.grado = grado;
    }

    @Override
    public String toString() {
        return grado + " " + paralelo;
    }

}
