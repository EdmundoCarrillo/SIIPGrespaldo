package com.ipn.mx.siipg.modelo;

import java.util.HashSet;
import java.util.Set;

public class Indicador implements java.io.Serializable {

    private Integer id;
    private Ejetematico ejetematico;
    private String nombre;
    private String rutaPdf;
    private int estatus;
    private Formato formato;    
    private Set indicadorTienePeriodos = new HashSet(0);

    public Indicador() {
    }

    public String getRutaPdf() {
        return rutaPdf;
    }

    public void setRutaPdf(String rutaPdf) {
        this.rutaPdf = rutaPdf;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Ejetematico getEjetematico() {
        return this.ejetematico;
    }

    public void setEjetematico(Ejetematico ejetematico) {
        this.ejetematico = ejetematico;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEstatus() {
        return estatus;
    }

    public void setEstatus(int estatus) {
        this.estatus = estatus;
    }
    
    public Formato getFormato() {
        return this.formato;
    }
    
    public void setFormato(Formato formato) {
        this.formato = formato;
    }
    
    public Set getIndicadorTienePeriodos() {
        return this.indicadorTienePeriodos;
    }
    
    public void setIndicadorTienePeriodos(Set indicadorTienePeriodos) {
        this.indicadorTienePeriodos = indicadorTienePeriodos;
    }

}
