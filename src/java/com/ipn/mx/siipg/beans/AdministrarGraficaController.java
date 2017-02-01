/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.siipg.beans;

import com.ipn.mx.siipg.dao.IndicadorDao;
import com.ipn.mx.siipg.impl.IndicadorDaoImpl;
import com.ipn.mx.siipg.modelo.Indicador;
import com.ipn.mx.siipg.modelo.Variable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
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
    private List<Indicador> indicadores;

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
    
    

 
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
