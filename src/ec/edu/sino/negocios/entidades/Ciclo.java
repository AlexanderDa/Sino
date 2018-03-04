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
    private MateriaAsignada asignada;
    private Alumno alumno;
    private float promedio;

    public Ciclo() {
    }

    public Ciclo(int id, MateriaAsignada asignada, Alumno alumno, float promedio) {
        this.id = id;
        this.asignada = asignada;
        this.alumno = alumno;
        this.promedio = promedio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public MateriaAsignada getAsignada() {
        return asignada;
    }

    public void setAsignada(MateriaAsignada asignada) {
        this.asignada = asignada;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public float getPromedio() {
        return promedio;
    }

    public void setPromedio(float promedio) {
        this.promedio = promedio;
    }

    @Override
    public String toString() {
        return  alumno.toString();
    }
}
