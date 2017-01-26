/*
 * This code was developed by Edmundo Carrillo on java technologies.
 * Contact: edmundodev@gmail.com
 * Hope you'll find it useful.
 */
package com.ipn.mx.siipg.beans;

import com.ipn.mx.siipg.dao.AreaPolitecnicaDao;
import com.ipn.mx.siipg.impl.AreaPolitecnicaDaoImpl;
import com.ipn.mx.siipg.modelo.Areapolitecnica;
import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

@Named
@SessionScoped
public class AreaPolitecnicaController implements Serializable {

    public List<Areapolitecnica> getItemsAvailableSelectOne() {
        AreaPolitecnicaDao areaDao = new AreaPolitecnicaDaoImpl();
        return areaDao.loadAreas();
    }
}
