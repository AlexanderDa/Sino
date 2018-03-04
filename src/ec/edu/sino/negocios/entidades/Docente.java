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
public class Docente {

    private final FirstUpperCase formater = new FirstUpperCase();

    private String cedula;
    private String usuario;
    private String clave;
    private String nombre;
    private String apellido;

    public Docente() {
    }

    public Docente(String cedula, String usuario, String clave, String nombre, String apellido) {
        this.cedula = cedula;
        this.usuario = usuario.toLowerCase();
        this.clave = clave;
        this.nombre = formater.parce(nombre);
        this.apellido = formater.parce(apellido);
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario.toLowerCase();
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
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
