/*
 * This code was developed by Edmundo Carrillo on java technologies.
 * Contact: edmundodev@gmail.com
 * Hope you'll find it useful.
 */
package com.ipn.mx.siipg.beans;

import com.ipn.mx.siipg.dao.SubMenuDao;
import com.ipn.mx.siipg.dao.util.JsfUtil;
import com.ipn.mx.siipg.impl.SubMenuDaoImpl;
import java.io.Serializable;
import java.util.ResourceBundle;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.context.RequestContext;

@Named
@SessionScoped
public class MostrarViewProveedorController implements Serializable {

    public void vistaProveedorEnable() {
        SubMenuDao subMenuDao = new SubMenuDaoImpl();
        subMenuDao.vistaProvedorEnable();
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("window.location.reload(true);");
        //     JsfUtil.addSuccessMessage("La vista fue habilitada");
    }

    public void vistaProveedorDisable() {
        SubMenuDao subMenuDao = new SubMenuDaoImpl();
        subMenuDao.vistaProvedorDisable();
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("window.location.reload(true);");
        //   JsfUtil.addErrorMessage("La vista fue deshabilitada");
    }

    public String getRutaEstatus() {
        SubMenuDao subMenuDao = new SubMenuDaoImpl();
        return subMenuDao.getRutaEstatus().getRuta();
    }

}
