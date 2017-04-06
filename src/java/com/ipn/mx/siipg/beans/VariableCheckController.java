/*
 * This code was developed by Edmundo Carrillo on java technologies.
 * Contact: edmundodev@gmail.com
 * Hope you'll find it useful.
 */
package com.ipn.mx.siipg.beans;

import com.ipn.mx.siipg.dao.VariableCheckDao;
import com.ipn.mx.siipg.impl.VariableCheckDaoImpl;
import com.ipn.mx.siipg.modelo.Variablecheck;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class VariableCheckController implements Serializable {

    private List<Variablecheck> items;
    private Variablecheck current;
    private String comentario;

    public List<Variablecheck> getItems() {
        VariableCheckDao varDao = new VariableCheckDaoImpl();
        return items = varDao.loadAll();
    }

    public void setItems(List<Variablecheck> items) {
        this.items = items;
    }

    public Variablecheck getCurrent() {
        return current;
    }

    public void setCurrent(Variablecheck current) {
        this.current = current;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public void updateVariableCheck() {

        current.setComentario(comentario);
        VariableCheckDao vrDao = new VariableCheckDaoImpl();
        vrDao.updateVariableCheck(current);
        comentario = "";

    }

}
