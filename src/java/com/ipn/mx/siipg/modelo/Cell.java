/*
 * This code was developed by Edmundo Carrillo on java technologies.
 * Contact: edmundodev@gmail.com
 * Hope you'll find it useful.
 */
package com.ipn.mx.siipg.modelo;

import java.io.Serializable;

public class Cell implements Serializable {

    private Integer idCell;
    private String value;

    public Cell() {
    }

    public Cell(Integer idCell, String value) {
        this.idCell = idCell;
        this.value = value;
    }

    public Cell(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getIdCell() {
        return idCell;
    }

    public void setIdCell(Integer idCell) {
        this.idCell = idCell;
    }

}
