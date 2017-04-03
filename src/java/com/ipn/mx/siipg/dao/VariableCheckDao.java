/*
 * This code was developed by Edmundo Carrillo on java technologies.
 * Contact: edmundodev@gmail.com
 * Hope you'll find it useful.
 */
package com.ipn.mx.siipg.dao;

import com.ipn.mx.siipg.modelo.Variable;
import com.ipn.mx.siipg.modelo.Variablecheck;
import java.util.List;

/**
 *
 * @author Edmundo Carrillo
 */
public interface VariableCheckDao {

    public List<Variablecheck> loadAll();

    public List<Variablecheck> loadVarForProveedor();

    public List<Variablecheck> checkByExistingVar(List<Variable> variableList);

    public Variablecheck checkByExistingVar(Variable variable);

    public void newVariableCheck(Variablecheck varCheck);

    public void updateVariableCheck(Variablecheck varCheck);

}
