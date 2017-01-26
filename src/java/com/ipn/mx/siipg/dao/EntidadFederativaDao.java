/*
 * This code was developed by Edmundo Carrillo on java technologies.
 * Contact: edmundodev@gmail.com
 * Hope you'll find it useful.
 */
package com.ipn.mx.siipg.dao;

import com.ipn.mx.siipg.modelo.Entidadfederativa;
import java.util.List;

/**
 *
 * @author Edmundo Carrillo
 */
public interface EntidadFederativaDao {

    public List<Entidadfederativa> loadEntidades();

    public void newEntidad(Entidadfederativa entidadFederativa);

    public void updateEntidad(Entidadfederativa entidadFederativa);

    public void deleteEntidad(Entidadfederativa entidadFederativa);

}
