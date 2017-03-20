/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.siipg.beans;

import com.ipn.mx.siipg.dao.FormatoDao;
import com.ipn.mx.siipg.dao.IndicadorDao;
import com.ipn.mx.siipg.dao.util.JsfUtil;
import com.ipn.mx.siipg.impl.FormatoDaoImpl;
import com.ipn.mx.siipg.impl.IndicadorDaoImpl;
import com.ipn.mx.siipg.modelo.Formato;
import com.ipn.mx.siipg.modelo.Indicador;
import com.ipn.mx.siipg.modelo.Variable;
import static com.sun.javafx.logging.PulseLogger.addMessage;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import org.primefaces.context.RequestContext;


import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartModel;
import org.primefaces.model.chart.HorizontalBarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.PieChartModel;

/**
 *
 * @author maccker
 */
@Named
@SessionScoped
public class AdministrarGraficaController implements Serializable{
    
    private static String [] namesImg = {"colum.png","area.png","lineas.png","pastel.png"};
    private String selectGraphic ;
    private String selectIndicador;
    private String selectVarible;
    private String selectVarible2;
    private String image = "colum.png";
    private final String reportServerUrl;
    private List<Indicador> indicadores;    
    private ChartModel graphic;

    public AdministrarGraficaController() {
        this.reportServerUrl = System.getenv("REPORT_SERVER") != null  ? System.getenv("REPORT_SERVER") : "https://siipg-reports.herokuapp.com";
    }

    public String getSelectGraphic() {
        return selectGraphic;
    }

    public void setSelectGraphic(String selectGraphic) {
        this.selectGraphic = selectGraphic;
    }

    public String getSelectIndicador() {
        return selectIndicador;
    }

    public void setSelectIndicador(String selectIndicador) {
        this.selectIndicador = selectIndicador;
    }

    public String getSelectVarible() {
        return selectVarible;
    }

    public void setSelectVarible(String selectVarible) {
        this.selectVarible = selectVarible;
    }

    public String getSelectVarible2() {
        return selectVarible2;
    }

    public void setSelectVarible2(String selectVarible2) {
        this.selectVarible2 = selectVarible2;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
                
    public List<Indicador> getIndicadores() {
        IndicadorDaoImpl indicador = new IndicadorDaoImpl();
        indicadores = indicador.loadIndicadoresList();
        return indicadores;
    }

    public void setIndicadores(List<Indicador> indicadores) {
        this.indicadores = indicadores;
    }
    
    public void refreshImage(){
        int option = Integer.parseInt(selectGraphic);
        image = namesImg[option];
    }
     
    
    public void generateFormat(){
        int indi, divisor, dividendo, grafica;
        if (selectIndicador == null ){ return;}
        try{
            indi = Integer.parseInt(selectIndicador);
            grafica = Integer.parseInt(selectGraphic);
            divisor = Integer.parseInt(selectVarible);
            dividendo = Integer.parseInt(selectVarible2);
            Formato nuevo = null ;
            Indicador indicador = null;
            FormatoDao formato = new FormatoDaoImpl();
            for(Indicador temp: indicadores){
                if(temp.getId() ==indi){
                    indicador = temp;
                }
            }           
            if ( indicador.getFormato() == null){
                nuevo = new Formato(indicador, grafica, divisor, dividendo);
                formato.newFormato(nuevo);
                JsfUtil.addSuccessMessage(ResourceBundle.getBundle("Bundle").getString("formato.new"));
            }
            else{
                nuevo = indicador.getFormato();
                nuevo.setFormato(grafica);
                nuevo.setIdDivisor(divisor);
                nuevo.setIdDividendo(dividendo);
                formato.updateFormato(nuevo);
                JsfUtil.addSuccessMessage(ResourceBundle.getBundle("Bundle").getString("formato.update"));
            }           
        }
        catch(Exception e){
            System.err.println(e);
        }
    }
    
    public ChartModel generateImageFromIndicator() {
        int option = Integer.parseInt(selectGraphic);

        ChartSeries boys = new ChartSeries();
        boys.setLabel("Boys");
        boys.set("2004", 120);
        boys.set("2005", 100);
        boys.set("2006", 44);
        boys.set("2007", 150);
        boys.set("2008", 25);

        ChartSeries girls = new ChartSeries();
        girls.setLabel("Girls");
        girls.set("2004", 52);
        girls.set("2005", 60);
        girls.set("2006", 110);
        girls.set("2007", 135);
        girls.set("2008", 120);

        switch (option) {
            case 0:
                BarChartModel model = new BarChartModel();
                model.addSeries(boys);
                model.addSeries(girls);
                graphic = model;
                break;
            case 1:
                LineChartModel areaModel = new LineChartModel();
                areaModel.addSeries(boys);
                areaModel.addSeries(girls);
                areaModel.setTitle("Area Chart");
                areaModel.setLegendPosition("ne");
                areaModel.setStacked(true);
                areaModel.setShowPointLabels(true);
                Axis xAxis = new CategoryAxis("Years");
                areaModel.getAxes().put(AxisType.X, xAxis);
                Axis yAxis = areaModel.getAxis(AxisType.Y);
                yAxis.setLabel("Births");
                yAxis.setMin(0);
                yAxis.setMax(300);
                graphic = areaModel;
                break;
            case 2:
                LineChartModel lineModel = new LineChartModel();
                lineModel.addSeries(boys);
                lineModel.addSeries(girls);
                graphic = lineModel;
                break;
            default:
                PieChartModel pieModel1 = new PieChartModel();
                pieModel1.set("Brand 1", 540);
                pieModel1.set("Brand 2", 325);
                pieModel1.set("Brand 3", 702);
                pieModel1.set("Brand 4", 421);
                pieModel1.setTitle("Simple Pie");
                pieModel1.setLegendPosition("w");
                graphic = pieModel1;
                break;
        }
        return graphic;
    }
    
    public String getReportServerUrl() {
        return reportServerUrl + "/reports/generateReport";
    }
}
