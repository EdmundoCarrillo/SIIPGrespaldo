/*
 * This code was developed by Edmundo Carrillo on java technologies.
 * Contact: edmundodev@gmail.com
 * Hope you'll find it useful.
 */
package com.ipn.mx.siipg.modelo;

import java.io.Serializable;
import java.util.List;

public class Row implements Serializable {

    private Integer idRow;
    private List<Cell> cellList;
    private Integer type;

    public Row(Integer idRow, List<Cell> cellList, Integer type) {
        this.idRow = idRow;
        this.cellList = cellList;
        this.type = type;
    }

    public Row(Integer idRow, List<Cell> cellList) {
        this.idRow = idRow;
        this.cellList = cellList;
    }

    public Row() {
    }

    public Integer getIdRow() {
        return idRow;
    }

    public void setIdRow(Integer idRow) {
        this.idRow = idRow;
    }

    public List<Cell> getCellList() {
        return cellList;
    }

    public void setCellList(List<Cell> cellList) {
        this.cellList = cellList;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

}
