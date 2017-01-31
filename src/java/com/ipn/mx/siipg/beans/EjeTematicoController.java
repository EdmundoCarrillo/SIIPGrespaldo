/*
 * This code was developed by Edmundo Carrillo on java technologies.
 * Contact: edmundodev@gmail.com
 * Hope you'll find it useful.
 */
package com.ipn.mx.siipg.beans;

import com.ipn.mx.siipg.dao.EjeTematicoDao;
import com.ipn.mx.siipg.dao.util.JsfUtil;
import com.ipn.mx.siipg.impl.EjeTematicoDaoImpl;
import com.ipn.mx.siipg.modelo.Ejetematico;
import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

@Named
@SessionScoped
public class EjeTematicoController implements Serializable {

    private Ejetematico current;
    private List<Ejetematico> items;

    public Ejetematico getCurrent() {
        return current;
    }

    public void setCurrent(Ejetematico current) {
        this.current = current;
    }

    public List<Ejetematico> getItems() {
        EjeTematicoDao ejeTematicoDao = new EjeTematicoDaoImpl();
        return items = ejeTematicoDao.loadEjes();
    }

    public void setItems(List<Ejetematico> items) {
        this.items = items;
    }

    public void prepareCreate() {
        current = new Ejetematico();
    }

    public void createItem() {
        try {
            EjeTematicoDao ejeTematicoDao = new EjeTematicoDaoImpl();
            ejeTematicoDao.newEjeTematico(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("Bundle").getString("ejeTematico.create"));
        } catch (Exception ex) {
            printError(ex);
        }
    }

    public void deleteItem() {
        try {
            EjeTematicoDao ejeTematicoDao = new EjeTematicoDaoImpl();
            ejeTematicoDao.deleteEjeTematico(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("Bundle").getString("ejeTematico.delete"));
        } catch (Exception ex) {
            printError(ex);
        }
    }

    public void updateItem() {
        try {
            EjeTematicoDao ejeTematicoDao = new EjeTematicoDaoImpl();
            ejeTematicoDao.updateEjeTematico(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("Bundle").getString("ejeTematico.update"));
        } catch (Exception ex) {
            printError(ex);
        }
    }

    public List<Ejetematico> getItemsAvailableSelectOne() {
        EjeTematicoDao ejeTematicoDao = new EjeTematicoDaoImpl();
        return ejeTematicoDao.loadEjes();
    }

    public void printError(Exception ex) {
        JsfUtil.addErrorMessage(ResourceBundle.getBundle("Bundle").getString("system.error"));
        System.out.println(ex.toString());
    }

}
