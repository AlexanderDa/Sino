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
public class MateriaAsignada {

    private int id;
    private Curso curso;
    private Materia materia;

    public MateriaAsignada() {
    }

    public MateriaAsignada(int id, Curso curso, Materia materia) {
        this.id = id;
        this.curso = curso;
        this.materia = materia;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
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

    @Override
    public String toString() {
        return curso.getGrado() + " " + curso.getParalelo();
    }

}
