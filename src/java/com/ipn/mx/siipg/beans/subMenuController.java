/*
 * This code was developed by Edmundo Carrillo on java technologies.
 * Contact: edmundodev@gmail.com
 * Hope you'll find it useful.
 */
package com.ipn.mx.siipg.beans;

import com.ipn.mx.siipg.dao.SubMenuDao;
import com.ipn.mx.siipg.impl.SubMenuDaoImpl;
import com.ipn.mx.siipg.modelo.Opcionesmenu;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class subMenuController implements Serializable {

    private Opcionesmenu current;
    private List<Opcionesmenu> items;

    public Opcionesmenu getCurrent() {
        return current;
    }

    public void setCurrent(Opcionesmenu current) {
        this.current = current;
    }

    public List<Opcionesmenu> getItems() {
        SubMenuDao subMenuDao = new SubMenuDaoImpl();
        return items = subMenuDao.loadSubMenus();
    }

    public void setItems(List<Opcionesmenu> items) {
        this.items = items;
    }

}
