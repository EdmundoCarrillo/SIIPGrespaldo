/*
 * This code was developed by Edmundo Carrillo on java technologies.
 * Contact: edmundodev@gmail.com
 * Hope you'll find it useful.
 */

package com.ipn.mx.siipg.modelo;

import java.io.Serializable;
import java.util.List;


public class NestedTable implements Serializable {
    
    private List<Table> customTable;

    public NestedTable(List<Table> customTable) {
        this.customTable = customTable;
    }

    public List<Table> getCustomTable() {
        return customTable;
    }

    public void setCustomTable(List<Table> customTable) {
        this.customTable = customTable;
    }

   
    

}
