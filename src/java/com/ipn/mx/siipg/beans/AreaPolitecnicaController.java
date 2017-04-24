/*
 * This code was developed by Edmundo Carrillo on java technologies.
 * Contact: edmundodev@gmail.com
 * Hope you'll find it useful.
 */
package com.ipn.mx.siipg.beans;

import com.ipn.mx.siipg.dao.AreaPolitecnicaDao;
import com.ipn.mx.siipg.dao.util.JsfUtil;
import com.ipn.mx.siipg.impl.AreaPolitecnicaDaoImpl;
import com.ipn.mx.siipg.modelo.Areapolitecnica;
import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

@Named
@SessionScoped
public class AreaPolitecnicaController implements Serializable {

    private Areapolitecnica current;
    private List<Areapolitecnica> items;

    public List<Areapolitecnica> getItemsAvailableSelectOne() {
        AreaPolitecnicaDao areaDao = new AreaPolitecnicaDaoImpl();
        return areaDao.loadAreas();
    }

    public Areapolitecnica getCurrent() {
        return current;
    }

    public void setCurrent(Areapolitecnica current) {
        this.current = current;
    }

    public List<Areapolitecnica> getItems() {
        AreaPolitecnicaDao areaDao = new AreaPolitecnicaDaoImpl();
        return items = areaDao.loadAreas();
    }

    public void setItems(List<Areapolitecnica> items) {
        this.items = items;
    }

    public void prepareCreate() {
        current = new Areapolitecnica();
    }

    public void createItem() {
        try {
            AreaPolitecnicaDao areaDao = new AreaPolitecnicaDaoImpl();
            areaDao.newAreaPolitecnica(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("Bundle").getString("area.create"));
        } catch (Exception ex) {
            printError(ex);
        }
    }

    public void deleteItem() {
        try {
            AreaPolitecnicaDao areaDao = new AreaPolitecnicaDaoImpl();
            areaDao.deleteAreaPolitecnica(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("Bundle").getString("area.delete"));
        } catch (Exception ex) {
            printError(ex);
        }
    }

    public void updateItem() {
        try {
            AreaPolitecnicaDao areaDao = new AreaPolitecnicaDaoImpl();
            areaDao.updateAreaPolitecnica(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("Bundle").getString("area.update"));
        } catch (Exception ex) {
            printError(ex);
        }
    }

    public void printError(Exception ex) {
        JsfUtil.addErrorMessage(ResourceBundle.getBundle("Bundle").getString("system.error"));
        System.out.println(ex.toString());
    }

}
