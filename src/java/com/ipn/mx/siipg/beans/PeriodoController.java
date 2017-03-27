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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
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
    private int Anio ;
    private List<Periodo> periodos;
    private static Calendar date = null;
    private List<Periodo> admintrator = null;

    public PeriodoController() {
        
    if(date == null){
        date = new GregorianCalendar();
    }
    admintrator = new ArrayList<>();
    Anio = date.get(Calendar.YEAR);
    periodos = getPeriodos();
    if(verifi(periodos, Integer.toString(Anio))){
        int i = periodos.size();
        admintrator.add(periodos.get(i-1));
        admintrator.add(periodos.get(i-2));
    }else{
        String dat = Integer.toString(Anio);
        PeriodoDao pdao = new PeriodoDaoImpl();
        Periodo tem1 = new Periodo();
        tem1.setPeriodo(dat);
        tem1.setEstatus(1);
        pdao.newPeriodo(tem1);
        tem1.setPeriodo(dat+"-2");
        tem1.setEstatus(1);
        pdao.newPeriodo(tem1);
        periodos = getPeriodos();
        int i = periodos.size();
        admintrator.add(periodos.get(i-1));
        admintrator.add(periodos.get(i-2));
    }
    }

    public void setAnio(int Anio) {
        this.Anio = Anio;
    }

    public int getAnio() {
        return Anio;
    }

    public List<Periodo> getAdmintrator() {
        return admintrator;
    }

    public void setAdmintrator(List<Periodo> admintrator) {
        this.admintrator = admintrator;
    }
    
    
    

    public List<Periodo> getPeriodos() {
        PeriodoDao temp = new PeriodoDaoImpl();
        periodos = temp.loadPeriodos();
        return periodos;
    }

    public void setPeriodos(List<Periodo> periodos) {
        this.periodos = periodos;
    }
    
    public boolean verifi(List<Periodo> p, String a){
        boolean result = false;
        for (Periodo periodo : p) {
            if(a.equals(periodo.getPeriodo())){
                result = true;
                break;
            }
        }
        return result;
    }
    
  
    
    
    
    
}
