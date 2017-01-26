/*
 * This code was developed by Edmundo Carrillo on java technologies.
 * Contact: edmundodev@gmail.com
 * Hope you'll find it useful.
 */
package com.ipn.mx.siipg.dao;

import com.ipn.mx.siipg.modelo.Opcionesmenu;
import java.util.List;

/**
 *
 * @author Edmundo Carrillo
 */
public interface SubMenuDao {

    public List<Opcionesmenu> loadSubMenus();

    public void newSubMenu(Opcionesmenu subMenu);

    public void updateSubMenu(Opcionesmenu subMenu);

    public void deleteSubMenu(Opcionesmenu rol);
}
