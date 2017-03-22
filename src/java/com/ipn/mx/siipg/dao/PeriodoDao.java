/*
 * This code was developed by Edmundo Carrillo on java technologies.
 * Contact: edmundodev@gmail.com
 * Hope you'll find it useful.
 */
package com.ipn.mx.siipg.dao;

import com.ipn.mx.siipg.modelo.Periodo;
import java.util.List;

/**
 *
 * @author Edmundo Carrillo
 */
public interface PeriodoDao {

    public Periodo periodoByMAXID();
    public List<Periodo> loadPeriodos();
    public void newPeriodo(Periodo periodo);
    public void updatePeriodo(Periodo periodo);
    public void deletePeriodo(Periodo periodo);

}
