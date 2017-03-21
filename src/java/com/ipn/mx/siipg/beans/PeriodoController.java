/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.siipg.beans;

import com.ipn.mx.siipg.dao.PeriodoDao;
import com.ipn.mx.siipg.dao.util.JsfUtil;
import com.ipn.mx.siipg.impl.PeriodoDaoImpl;
import com.ipn.mx.siipg.modelo.Periodo;
import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;


/**
 *
 * @author deo
 */
//@ManagedBean(name = "periodoController")
@Named
@SessionScoped
public class PeriodoController implements Serializable{

    private List<Periodo> periodos;



    public List<Periodo> getPeriodos() {
        PeriodoDao temp = new PeriodoDaoImpl();
        periodos = temp.loadPeriodos();
        return periodos;
    }

    public void setPeriodos(List<Periodo> periodos) {
        this.periodos = periodos;
    }

  
    
    
    
    
}
