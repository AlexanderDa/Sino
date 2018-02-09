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
public class Administrador {

    private int id;
    private String usuario;
    private String clave;
    private String nombre;
    private String apellido;

    public Administrador() {
    }

    public Administrador(int id, String usuario, String clave, String nombre, String apellido) {
        this.id = id;
        this.usuario = usuario;
        this.clave = clave;
        this.nombre = nombre;
        this.apellido = apellido;
    }
    public Administrador(int id, String usuario, String nombre, String apellido) {
        this.id = id;
        this.usuario = usuario;
        this.nombre = nombre;
        this.apellido = apellido;
    }
    public Administrador(int id, String clave) {
        this.id = id;
        this.clave = clave;
    }

    public Administrador(String usuario, String clave) {
        this.usuario = usuario;
        this.clave = clave;
        
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
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
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return apellido + " " + nombre;
    }

}
