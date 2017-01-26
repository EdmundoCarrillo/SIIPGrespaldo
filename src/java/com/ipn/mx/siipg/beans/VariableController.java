/*
 * This code was developed by Edmundo Carrillo on java technologies.
 * Contact: edmundodev@gmail.com
 * Hope you'll find it useful.
 */
package com.ipn.mx.siipg.beans;

import com.ipn.mx.siipg.dao.VariableDao;
import com.ipn.mx.siipg.dao.util.JsfUtil;
import com.ipn.mx.siipg.impl.VariableDaoImpl;
import com.ipn.mx.siipg.modelo.Unidadresponsable;
import com.ipn.mx.siipg.modelo.Variable;
import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

@Named
@SessionScoped
public class VariableController implements Serializable {

    private Variable current;
    private List<Variable> items;
    private String selectedUnidad;

    public Variable getCurrent() {
        return current;
    }

    public void setCurrent(Variable current) {
        this.current = current;
    }

    public List<Variable> getItems() {
        VariableDao variableDao = new VariableDaoImpl();
        return items = variableDao.loadVariables();
    }

    public void setItems(List<Variable> items) {
        this.items = items;
    }

    public String getSelectedUnidad() {
        return selectedUnidad;
    }

    public void setSelectedUnidad(String selectedUnidad) {
        this.selectedUnidad = selectedUnidad;
    }

    public void prepareCreate() {
        current = new Variable();
    }

    public void createItem() {
        try {
            VariableDao variableDao = new VariableDaoImpl();
            Unidadresponsable unidad = new Unidadresponsable();
            unidad.setId(Integer.valueOf(selectedUnidad));
            current.setUnidadresponsable(unidad);
            variableDao.newVariable(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("Bundle").getString("variable.create"));
        } catch (Exception ex) {
            printError(ex);
        }
    }

    public void deleteItem() {
        try {
            VariableDao variableDao = new VariableDaoImpl();
            variableDao.deleteVariable(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("Bundle").getString("variable.delete"));
        } catch (Exception ex) {
            printError(ex);
        }

    }

    public void updateItem() {
        try {
            VariableDao variableDao = new VariableDaoImpl();
            Unidadresponsable unidad = new Unidadresponsable();
            unidad.setId(Integer.valueOf(selectedUnidad));
            current.setUnidadresponsable(unidad);
            variableDao.updateVariable(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("Bundle").getString("variable.update"));
        } catch (Exception ex) {
            printError(ex);
        }

    }

    public void printError(Exception ex) {
        JsfUtil.addErrorMessage(ResourceBundle.getBundle("Bundle").getString("system.error"));
        System.out.println(ex.toString());
    }

}
