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
    
    public String getReportServerUrl() {
        return reportServerUrl + "/reports/generateReport";
    }
}
