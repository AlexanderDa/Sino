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
public class Parcial {

    private int id;
    private Quimestre quimestre;
    private String descripcion;
    private float tarea;
    private float individual;
    private float grupal;
    private float promedioEvaluacion;
    private float notaParcial;
    private float promedio;

    public Parcial() {
    }

    public Parcial(int id, Quimestre quimestre, String descripcion, float tarea, float individual, float grupal, float promedioEvaluacion, float notaParcial, float promedio) {
        this.id = id;
        this.quimestre = quimestre;
        this.descripcion = descripcion;
        this.tarea = tarea;
        this.individual = individual;
        this.grupal = grupal;
        this.promedioEvaluacion = promedioEvaluacion;
        this.notaParcial = notaParcial;
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

    public Quimestre getQuimestre() {
        return quimestre;
    }

    public void setQuimestre(Quimestre quimestre) {
        this.quimestre = quimestre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getTarea() {
        return tarea;
    }

    public void setTarea(float tarea) {
        this.tarea = tarea;
    }

    public float getIndividual() {
        return individual;
    }

    public void setIndividual(float individual) {
        this.individual = individual;
    }

    public float getGrupal() {
        return grupal;
    }

    public void setGrupal(float grupal) {
        this.grupal = grupal;
    }

    public float getPromedioEvaluacion() {
        return promedioEvaluacion;
    }

    public void setPromedioEvaluacion(float promedioEvaluacion) {
        this.promedioEvaluacion = promedioEvaluacion;
    }

    public float getNotaParcial() {
        return notaParcial;
    }

    public void setNotaParcial(float notaParcial) {
        this.notaParcial = notaParcial;
    }
    
    @Override
    public String toString(){
        return descripcion+" "+promedio;
    }
    
    
}
