package com.ipn.mx.siipg.modelo;
// Generated 12/12/2016 01:27:14 AM by Hibernate Tools 4.3.1

import java.util.HashSet;
import java.util.Set;

public class Menu implements java.io.Serializable {

    private Integer idmenu;
    private Rol rol;
    private String nombre;
    private String ruta;
    private String icon;
    private Set opcionesmenus = new HashSet(0);

    public Menu() {
    }

    public Integer getIdmenu() {
        return this.idmenu;
    }

    public void setIdmenu(Integer idmenu) {
        this.idmenu = idmenu;
    }

    public Rol getRol() {
        return this.rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRuta() {
        return this.ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public Set getOpcionesmenus() {
        return this.opcionesmenus;
    }

    public void setOpcionesmenus(Set opcionesmenus) {
        this.opcionesmenus = opcionesmenus;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

}
