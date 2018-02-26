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
public class Ciclo {

    private int id;
    private Curso curso;
    private MateriaAsignada asignada;
    private Alumno alumno;
    private float promedio;

    public Ciclo() {
    }

    public Ciclo(int id, Curso curso, MateriaAsignada asignada, Alumno alumno, float promedio) {
        this.id = id;
        this.curso = curso;
        this.asignada = asignada;
        this.alumno = alumno;
        this.promedio = promedio;
    }

    public float getPromedio() {
        return promedio;
    }

    public void setPromedio(float promedio) {
        this.promedio = promedio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public MateriaAsignada getAsignada() {
        return asignada;
    }

    public void setAsignada(MateriaAsignada asignada) {
        this.asignada = asignada;
    }

    @Override
    public String toString() {
        return curso + " " + alumno + " " + promedio;
    }
}
