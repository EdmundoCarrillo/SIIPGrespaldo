/*
 * This code was developed by Edmundo Carrillo on java technologies.
 * Contact: edmundodev@gmail.com
 * Hope you'll find it useful.
 */
package com.ipn.mx.siipg.dao;

import com.ipn.mx.siipg.modelo.Areapolitecnica;
import java.util.List;

/**
 *
 * @author Edmundo Carrillo
 */
public interface AreaPolitecnicaDao {

    public List<Areapolitecnica> loadAreas();

    public void newAreaPolitecnica(Areapolitecnica areaPolitecnica);

    public void updateAreaPolitecnica(Areapolitecnica areaPolitecnica);

    public void deleteAreaPolitecnica(Areapolitecnica areaPolitecnica);

}
