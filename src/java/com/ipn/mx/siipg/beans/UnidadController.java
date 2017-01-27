/*
 * This code was developed by Edmundo Carrillo on java technologies.
 * Contact: edmundodev@gmail.com
 * Hope you'll find it useful.
 */
package com.ipn.mx.siipg.beans;

import com.ipn.mx.siipg.impl.UnidadResponsableDaoImpl;
import com.ipn.mx.siipg.modelo.Unidadresponsable;
import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import com.ipn.mx.siipg.dao.UnidadResponsableDao;
import com.ipn.mx.siipg.dao.util.JsfUtil;
import com.ipn.mx.siipg.modelo.Areapolitecnica;
import com.ipn.mx.siipg.modelo.Entidadfederativa;
import java.util.ResourceBundle;

/**
 *
 * @author Edmundo Carrillo
 */
@Named
@SessionScoped
public class UnidadController implements Serializable {

    private Unidadresponsable current;
    private List<Unidadresponsable> items;
    private String selectedEstado;
    private String selectedArea;

    public Unidadresponsable getCurrent() {
        return current;
    }

    public void setCurrent(Unidadresponsable current) {
        this.current = current;
    }

    public List<Unidadresponsable> getItems() {
        UnidadResponsableDao unidadDao = new UnidadResponsableDaoImpl();
        return items = unidadDao.loadUnidad();
    }

    public void setItems(List<Unidadresponsable> items) {
        this.items = items;
    }

    public String getSelectedEstado() {
        return selectedEstado;
    }

    public void setSelectedEstado(String selectedEstado) {
        this.selectedEstado = selectedEstado;
    }

    public String getSelectedArea() {
        return selectedArea;
    }

    public void setSelectedArea(String selectedArea) {
        this.selectedArea = selectedArea;
    }

    public void prepareCreate() {
        current = new Unidadresponsable();
    }

    public void createItem() {
        try {
            UnidadResponsableDao unidadDao = new UnidadResponsableDaoImpl();
            Entidadfederativa entidad = new Entidadfederativa();
            Areapolitecnica area = new Areapolitecnica();
            entidad.setId(Integer.valueOf(selectedEstado));
            area.setId(Integer.valueOf(selectedArea));
            current.setEntidadfederativa(entidad);
            current.setAreapolitecnica(area);
            unidadDao.newUnidad(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("Bundle").getString("unidad.create"));
        } catch (Exception ex) {
            printError(ex);
        }
    }

    public void updateItem() {
        try {
            UnidadResponsableDao unidadDao = new UnidadResponsableDaoImpl();
            Entidadfederativa entidad = new Entidadfederativa();
            Areapolitecnica area = new Areapolitecnica();
            entidad.setId(Integer.valueOf(selectedEstado));
            area.setId(Integer.valueOf(selectedArea));
            current.setEntidadfederativa(entidad);
            current.setAreapolitecnica(area);
            unidadDao.updateUnidad(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("Bundle").getString("unidad.update"));
        } catch (Exception ex) {
            printError(ex);
        }
    }

    public void deleteItem() {
        try {
            UnidadResponsableDao unidadDao = new UnidadResponsableDaoImpl();
            unidadDao.deleteUnidad(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("Bundle").getString("unidad.delete"));
        } catch (Exception ex) {
            printError(ex);

        }
    }

    public List<Unidadresponsable> getItemsAvailableSelectOne() {
        UnidadResponsableDao unidadDao = new UnidadResponsableDaoImpl();
        return unidadDao.loadUnidad();
    }

    public void fixSelectOneMenu() {
        selectedEstado = current.getEntidadfederativa().getId().toString();
        selectedArea = current.getAreapolitecnica().getId().toString();
    }

    public void printError(Exception ex) {
        JsfUtil.addErrorMessage(ResourceBundle.getBundle("Bundle").getString("system.error"));
        System.out.println(ex.toString());
    }

}
