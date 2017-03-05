/*
 * This code was developed by Edmundo Carrillo on java technologies.
 * Contact: edmundodev@gmail.com
 * Hope you'll find it useful.
 */
package com.ipn.mx.siipg.beans;

import com.ipn.mx.siipg.dao.VariableCheckDao;
import com.ipn.mx.siipg.dao.VariableDao;
import com.ipn.mx.siipg.impl.VariableCheckDaoImpl;
import com.ipn.mx.siipg.impl.VariableDaoImpl;
import com.ipn.mx.siipg.modelo.Unidadresponsable;
import com.ipn.mx.siipg.modelo.Usuario;
import com.ipn.mx.siipg.modelo.Variable;
import com.ipn.mx.siipg.modelo.Variablecheck;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named
@SessionScoped
public class VarProveedorController implements Serializable {

    private Variable current;
    private List<Variable> items;

    public Variable getCurrent() {
        return current;
    }

    public void setCurrent(Variable current) {
        this.current = current;
    }

    public List<Variable> getItems() {
        VariableDao variableDao = new VariableDaoImpl();
        return items = variableDao.loadVariableByUnidad(getUnidadFromSession());
    }

    public void setItems(List<Variable> items) {
        this.items = items;
    }

    public Unidadresponsable getUnidadFromSession() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        Usuario usuario = (Usuario) context.getSessionMap().get("usuario");
        return usuario.getUnidadresponsable();
    }

    /*
    
    WARNING
    Este metodo se tiene que cambiar, no se pueden hacer tantas consultas a la base 
    para consultar el estatus de la revisión
    
     */
    public Variablecheck getAdminReview(Variable variable) {
        VariableCheckDao varCheckDao = new VariableCheckDaoImpl();
        return varCheckDao.checkByExistingVar(variable);
    }

}
