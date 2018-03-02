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
public class Alumno {

    private String cedula;
    private String nombre;
    private String apellido;

    public Alumno() {
    }

    public Alumno(String cedula, String nombre, String apellido) {
        this.cedula = cedula;
        this.nombre = nombre.toLowerCase();
        this.apellido = apellido.toLowerCase();
    }

    public String getApellido() {
        return new FirstUpperCase(apellido).parce();
    }

    public void setApellido(String apellido) {
        this.apellido = apellido.toLowerCase();
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {

        return new FirstUpperCase(nombre).parce();
    }

    public void setNombre(String nombre) {
        this.nombre = nombre.toLowerCase();
    }

    @Override
    public String toString() {
        return apellido + " " + nombre;
    }

}
