/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.siipg.beans;


import com.ipn.mx.siipg.dao.FormatoDao;
import com.ipn.mx.siipg.dao.util.JsfUtil;
import com.ipn.mx.siipg.impl.EjeTematicoDaoImpl;
import com.ipn.mx.siipg.impl.FormatoDaoImpl;

import com.ipn.mx.siipg.modelo.Indicador;
import com.ipn.mx.siipg.modelo.IndicadorTienePeriodo;
import com.ipn.mx.siipg.modelo.Periodo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedProperty;
import javax.inject.Named;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.BarChartSeries;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import org.primefaces.model.chart.PieChartModel;


/**
 *
 * @author deo
 */
//@ManagedBean(name = "dataScrollerIndicador")
@Named
@SessionScoped
public class DataScrollerIndicador implements Serializable{
    private static final String [] tipos = {"bar","line","line","pie"};
    private List<Indicador> list ;
    private int indice;
    private int indice2;
    private boolean procesa = false;
    private String index;
    private Map<Integer,List<IndicadorTienePeriodo>> temp = null;

    

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public boolean isProcesa() {
        return procesa;
    }

    public void setProcesa(boolean procesa) {
        this.procesa = procesa;
    }
    
    public int getIndice() {
        return indice;
    }

    public void setIndice(int indice) {
       
        this.indice = indice;
    }

    public void setIndice2(int indice2) {
        this.indice2 = indice2;
    }

    public int getIndice2() {
        return indice2;
    }
    
    public void generaindicador(){
        temp = new HashMap<>();
        EjeTematicoDaoImpl temp1 = new EjeTematicoDaoImpl();
        list = temp1.check(index);
        
        List<IndicadorTienePeriodo> listperiodo;
        for (Indicador indicador : list) {
            
            Object [] per =  indicador.getIndicadorTienePeriodos().toArray();
            
            if(per != null && per.length > 0){
                listperiodo = new ArrayList<IndicadorTienePeriodo>();
            for (Object peri : per) {
                IndicadorTienePeriodo r = (IndicadorTienePeriodo) peri;
                int ban = r.getPeriodo().getId();
                if(ban >= indice && ban <=indice2 ){
                listperiodo.add(r);               
                }
            }
            temp.put(indicador.getId(), listperiodo);
            
            }
            else{
                System.out.println("on hay periodos");
            }
        }
        
    }
    public List<IndicadorTienePeriodo> getPeriodos(Integer i){
        System.out.println("llamadas "+ i);
        List<IndicadorTienePeriodo> resul = null;
        if(temp != null){
            resul =temp.get(i);
        }
        return resul;
    }
    public Object data(int i,int f){
        
        Object data = null;
        Axis xAxis = null;
        Axis yAxis = null;
        List<IndicadorTienePeriodo> l = null;
        if(temp != null){
            l =temp.get(f);
            if(l != null){
        switch(i){
            case 1:
                
                
                LineChartModel areaModel = new LineChartModel();
                for (IndicadorTienePeriodo indicadorTienePeriodo : l) {
                    LineChartSeries te3 = new LineChartSeries();
                    
                    String p = indicadorTienePeriodo.getPeriodo().getPeriodo();
                    te3.setLabel(p);
                    te3.set(p, indicadorTienePeriodo.getResultado()*100.0);
                    te3.setFill(true);
                    areaModel.addSeries(te3);
                }
                areaModel.setTitle(l.get(0).getIndicador().getNombre());
                areaModel.setLegendPosition("ne");
                areaModel.setStacked(true);
                areaModel.setShowPointLabels(true);
                Axis xAxis1 = new CategoryAxis("Periodos");
                areaModel.getAxes().put(AxisType.X, xAxis1);
                Axis yAxis1 = areaModel.getAxis(AxisType.Y);
                yAxis1.setLabel("Porcentajes");
                yAxis1.setMin(0);
                yAxis1.setMax(100);
                data= areaModel;
                break;
            case 2:
                LineChartModel lineModel = new LineChartModel();
                
                 for (IndicadorTienePeriodo indicadorTienePeriodo : l) {
                    LineChartSeries ser = new LineChartSeries();
                    String per =indicadorTienePeriodo.getPeriodo().getPeriodo();
                    ser.setLabel(per);
                    
                    ser.set(per,indicadorTienePeriodo.getResultado() * 100.0);
                    lineModel.addSeries(ser);
                 }
                lineModel.setTitle(l.get(0).getIndicador().getNombre());
                lineModel.setLegendPosition("e");
                lineModel.setShowPointLabels(true);
                lineModel.getAxes().put(AxisType.X, new CategoryAxis("Periodos"));
                yAxis = lineModel.getAxis(AxisType.Y);
                yAxis.setLabel("Porcentajes");
                yAxis.setMin(0);
                yAxis.setMax(100);
                data = lineModel;               
                break;
            case 3:
                PieChartModel pieModel = new PieChartModel();
                for (IndicadorTienePeriodo indicadorTienePeriodo : l) {
                    pieModel.set(indicadorTienePeriodo.getPeriodo().getPeriodo(), indicadorTienePeriodo.getResultado());
                }
                pieModel.setTitle(l.get(0).getIndicador().getNombre());
                pieModel.setLegendPosition("e");
                data = pieModel;
                break;
            default:
                
               
                BarChartModel barModel=  new BarChartModel();
            for (IndicadorTienePeriodo indicadorTienePeriodo : l) {
                
                 ChartSeries tem12 = new BarChartSeries();
                 String lab = indicadorTienePeriodo.getPeriodo().getPeriodo();
                 tem12.setLabel(lab);
                 tem12.set(lab, indicadorTienePeriodo.getResultado() * 100);
                 barModel.addSeries(tem12);
            }
            barModel.setTitle(l.get(0).getIndicador().getNombre());
            barModel.setLegendPosition("ne");
         
            xAxis = barModel.getAxis(AxisType.X);
            xAxis.setLabel("Periodos");
         
            yAxis = barModel.getAxis(AxisType.Y);
            yAxis.setLabel("Porcentajes");
            data = barModel;
                
                
        }
            }
            else{
                data = generaDefault(i);
            }
        }else{
             data = generaDefault(i);
        }
        return data;
    }
    
    public Object generaDefault(int i){
        Object data = null;
        switch(i){
                    case 1:
                        data = new LineChartModel();
                        break;
                    case 2:
                        data = new LineChartModel();
                        break;
                    case 3:
                        data = new PieChartModel();
                        break;
                        default:
                           data = new BarChartModel(); 
                }
        return data;
    }
    public List<Indicador> getList() {
        return list;
    }

    public void setList(List<Indicador> list) {
        this.list = list;
    }
    
    public void validar(){
        if(indice2>indice){
            procesa = true;
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("Bundle").getString("periodo.acept"));
        }
        else{
            procesa = false;
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("Bundle").getString("periodo.error"));
        }
    }
    
    public String getformat(Integer i){
        
        String resp = tipos[i];
        return resp;
    }
    

}
