/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.sino.negocios.entidades;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author alexander
 */
public class Periodo {

    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private int id;
    private Date fechaInicio;
    private Date fechaFin;
    private String director;
    private String subdirector;
    private String coordinador;

    public Periodo() {
    }

    public Periodo(int id, Date fechaInicio, Date fechaFin, String director, String subdirector, String coordinador) {
        this.id = id;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.director = director;
        this.subdirector = subdirector;
        this.coordinador = coordinador;
    }

    public Periodo(int id, String fechaInicio, String fechaFin, String director, String subdirector, String coordinador) {
        this.id = id;
        try {
            this.fechaInicio = sdf.parse(fechaInicio);
            this.fechaFin = sdf.parse(fechaFin);
        } catch (ParseException e) {
            System.err.println("No se ha podido definir las fechas. " + e.getMessage());
        }

        this.director = director;
        this.subdirector = subdirector;
        this.coordinador = coordinador;
    }

    public String getCoordinador() {
        return coordinador;
    }

    public void setCoordinador(String coordinador) {
        this.coordinador = coordinador;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        try {
            this.fechaInicio = sdf.parse(fechaInicio);
        } catch (ParseException e) {
            System.err.println("No se ha podido definir la fecha de inicio. " + e.getMessage());
        }

    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        try {
            this.fechaFin = sdf.parse(fechaFin);
        } catch (ParseException ex) {
            System.err.println("No se ha podido definir la fecha de finalizacion.");
        }
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getSubdirector() {
        return subdirector;
    }

    public void setSubdirector(String subdirector) {
        this.subdirector = subdirector;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf2 = new SimpleDateFormat("MMMM yyyy", new Locale("es", "es"));
        return sdf2.format(fechaInicio) + " - " + sdf2.format(fechaFin);
    }

}
