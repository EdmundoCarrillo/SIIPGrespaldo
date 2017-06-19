/*
 * This code was developed by Edmundo Carrillo on java technologies.
 * Contact: edmundodev@gmail.com
 * Hope you'll find it useful.
 */
package com.ipn.mx.siipg.beans;

import com.ipn.mx.siipg.modelo.Cell;
import com.ipn.mx.siipg.modelo.Row;
import com.ipn.mx.siipg.modelo.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

@Named
@SessionScoped
public class PruebaJstlController implements Serializable {

    private List<Table> tableList;

    public PruebaJstlController() {

        List<Cell> cellList1 = new ArrayList();
        cellList1.add(new Cell(1, "Header 1"));
        cellList1.add(new Cell(1, "Header 2"));
        cellList1.add(new Cell(1, "Header 3"));
        cellList1.add(new Cell(1, "Header 4"));

        List<Cell> cellList2 = new ArrayList();
        cellList2.add(new Cell(2, "value 1"));
        cellList2.add(new Cell(2, "value 2"));
        cellList2.add(new Cell(2, "value 3"));
        cellList2.add(new Cell(2, "value 4"));

        List<Cell> cellList3 = new ArrayList();
        cellList3.add(new Cell(2, "value 5"));
        cellList3.add(new Cell(2, "value 6"));
        cellList3.add(new Cell(2, "value 7"));
        cellList3.add(new Cell(2, "value 8"));

        List<Cell> cellList4 = new ArrayList();
        cellList4.add(new Cell(2, "value 9"));
        cellList4.add(new Cell(2, "value 10"));
        cellList4.add(new Cell(2, "value 11"));
        cellList4.add(new Cell(2, "value 12"));

        List<Row> rowList = new ArrayList();
        rowList.add(new Row(1, cellList1, 1));
        rowList.add(new Row(2, cellList2, 2));
        rowList.add(new Row(3, cellList3, 2));
        rowList.add(new Row(4, cellList4, 2));
        //°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°
        List<Cell> cellList01 = new ArrayList();
        cellList01.add(new Cell(1, "Header i 1"));
        cellList01.add(new Cell(1, "Header i 2"));
        cellList01.add(new Cell(1, "Header i 3"));
        cellList01.add(new Cell(1, "Header i 4"));

        List<Cell> cellList02 = new ArrayList();
        cellList02.add(new Cell(2, "value i 1"));
        cellList02.add(new Cell(2, "value i 2"));
        cellList02.add(new Cell(2, "value i 3"));
        cellList02.add(new Cell(2, "value i 4"));

        List<Row> rowList2 = new ArrayList();
        rowList2.add(new Row(1, cellList01, 1));
        rowList2.add(new Row(2, cellList02, 2));

        List<Table> tableListLocal = new ArrayList();
        tableListLocal.add(new Table(1, rowList));
        tableListLocal.add(new Table(2, rowList2));

        this.tableList = tableListLocal;
    }

    public List<Table> getTableList() {
        return tableList;
    }

    public void setTableList(List<Table> tableList) {
        this.tableList = tableList;
    }

}
