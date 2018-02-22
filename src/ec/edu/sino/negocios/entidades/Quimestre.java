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
public class Quimestre {

    private int id;
    private String descripcion;
    private Ciclo ciclo;
    private float promedioParcial;
    private float notaParcial;
    private float quimestral;
    private float notaQuimestral;
    private float promedio;
    private String notaCualitativa;
    private int laborado;
    private int justificados;
    private int injustificados;
    private int atrasos;

    public Quimestre() {
    }

    public Quimestre(int id, String descripcion, Ciclo ciclo, float promedioParcial, float notaParcial, float quimestral, float notaQuimestral, float promedio, String notaCualitativa, int laborado, int justificados, int injustificados, int atrasos) {
        this.id = id;
        this.descripcion = descripcion;
        this.ciclo = ciclo;
        this.promedioParcial = promedioParcial;
        this.notaParcial = notaParcial;
        this.quimestral = quimestral;
        this.notaQuimestral = notaQuimestral;
        this.promedio = promedio;
        this.notaCualitativa = notaCualitativa;
        this.laborado = laborado;
        this.justificados = justificados;
        this.injustificados = injustificados;
        this.atrasos = atrasos;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Ciclo getCiclo() {
        return ciclo;
    }

    public void setCiclo(Ciclo ciclo) {
        this.ciclo = ciclo;
    }

    public float getPromedioParcial() {
        return promedioParcial;
    }

    public void setPromedioParcial(float promedioParcial) {
        this.promedioParcial = promedioParcial;
    }

    public float getNotaParcial() {
        return notaParcial;
    }

    public void setNotaParcial(float notaParcial) {
        this.notaParcial = notaParcial;
    }

    public float getQuimestral() {
        return quimestral;
    }

    public void setQuimestral(float quimestral) {
        this.quimestral = quimestral;
    }

    public float getNotaQuimestral() {
        return notaQuimestral;
    }

    public void setNotaQuimestral(float notaQuimestral) {
        this.notaQuimestral = notaQuimestral;
    }

    public float getPromedio() {
        return promedio;
    }

    public void setPromedio(float promedio) {
        this.promedio = promedio;
    }

    public String getNotaCualitativa() {
        return notaCualitativa;
    }

    public void setNotaCualitativa(String notaCualitativa) {
        this.notaCualitativa = notaCualitativa;
    }

    public int getLaborado() {
        return laborado;
    }

    public void setLaborado(int laborado) {
        this.laborado = laborado;
    }

    public int getJustificados() {
        return justificados;
    }

    public void setJustificados(int justificados) {
        this.justificados = justificados;
    }

    public int getInjustificados() {
        return injustificados;
    }

    public void setInjustificados(int injustificados) {
        this.injustificados = injustificados;
    }

    public int getAtrasos() {
        return atrasos;
    }

    public void setAtrasos(int atrasos) {
        this.atrasos = atrasos;
    }
    
    @Override
    public String toString(){
        return descripcion+" "+promedio;
    }
    
}
