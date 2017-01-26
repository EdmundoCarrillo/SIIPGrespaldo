/*
 * This code was developed by Edmundo Carrillo on java technologies.
 * Contact: edmundodev@gmail.com
 * Hope you'll find it useful.
 */
package com.ipn.mx.siipg.beans;

import com.ipn.mx.siipg.dao.EntidadFederativaDao;
import com.ipn.mx.siipg.impl.EntidadFederativaDaoImpl;
import com.ipn.mx.siipg.modelo.Entidadfederativa;
import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

@Named
@SessionScoped
public class EntidadFederativaController implements Serializable {

    public List<Entidadfederativa> getItemsAvailableSelectOne() {
        EntidadFederativaDao entidadDao = new EntidadFederativaDaoImpl();
        return entidadDao.loadEntidades();
    }

}
