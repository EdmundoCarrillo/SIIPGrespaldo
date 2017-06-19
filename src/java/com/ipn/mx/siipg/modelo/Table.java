/*
 * This code was developed by Edmundo Carrillo on java technologies.
 * Contact: edmundodev@gmail.com
 * Hope you'll find it useful.
 */
package com.ipn.mx.siipg.modelo;

import java.io.Serializable;
import java.util.List;

public class Table implements Serializable {

    private Integer idTable;
    private List<Row> rowList;
    private Integer type;

    public Table() {
    }

    public Table(Integer idTable, List<Row> rowList) {
        this.idTable = idTable;
        this.rowList = rowList;
    }

    public Table(Integer idTable, List<Row> rowList, Integer type) {
        this.idTable = idTable;
        this.rowList = rowList;
        this.type = type;
    }
    

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getIdTable() {
        return idTable;
    }

    public void setIdTable(Integer idTable) {
        this.idTable = idTable;
    }

    public List<Row> getRowList() {
        return rowList;
    }

    public void setRowList(List<Row> rowList) {
        this.rowList = rowList;
    }

}
