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

    private final FirstUpperCase formater = new FirstUpperCase();
    private String cedula;
    private String nombre;
    private String apellido;

    public Alumno() {
    }

    public Alumno(String cedula, String nombre, String apellido) {
        this.cedula = cedula;
        this.nombre = formater.parce(nombre);
        this.apellido = formater.parce(apellido);
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = formater.parce(nombre);
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = formater.parce(apellido);
    }

    @Override
    public String toString() {
        return apellido + " " + nombre;
    }

}
