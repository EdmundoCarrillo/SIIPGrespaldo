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
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import org.primefaces.context.RequestContext;

@Named
@SessionScoped
public class VariableController implements Serializable {

    private Variable current;
    private List<Variable> items;
    private String selectedUnidad;
    private String duplicateMg;

    private boolean disableButton;

//    @PostConstruct
//    public void init() {
//        this.disableButton = true;
//    }
    public VariableController() {
        this.disableButton = false;
        this.duplicateMg = "";
    }

    public String getDuplicateMg() {
        return duplicateMg;
    }

    public void setDuplicateMg(String duplicateMg) {
        this.duplicateMg = duplicateMg;
    }

    public boolean isDisableButton() {
        return disableButton;
    }

    public void setDisableButton(boolean disableButton) {
        this.disableButton = disableButton;
    }

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
        duplicateMg = "";
        disableButton = false;
        RequestContext context = RequestContext.getCurrentInstance();
        context.update("VariablesCreateForm:out1");
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

    public void fixSelectOneMenu() {
        selectedUnidad = current.getUnidadresponsable().getId().toString();
    }

    public void printError(Exception ex) {
        JsfUtil.addErrorMessage(ResourceBundle.getBundle("Bundle").getString("system.error"));
        System.out.println(ex.toString());
    }

    public void findVariableWithTheSameName() {
        VariableDao variableDao = new VariableDaoImpl();
        int result = variableDao.findVariableWithTheSameName(current.getNombre());
        switch (result) {
            case 0:
                disableButton = false;
                break;
            case 1:
                disableButton = true;
                duplicateMg = "Ya existe una variable con el nombre que deseas ingresar.";
                break;

        }
    }

}
