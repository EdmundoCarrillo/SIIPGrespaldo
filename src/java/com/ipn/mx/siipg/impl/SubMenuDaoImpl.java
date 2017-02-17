/*
 * This code was developed by Edmundo Carrillo on java technologies.
 * Contact: edmundodev@gmail.com
 * Hope you'll find it useful.
 */
package com.ipn.mx.siipg.impl;

import com.ipn.mx.siipg.dao.SubMenuDao;
import com.ipn.mx.siipg.modelo.Opcionesmenu;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class SubMenuDaoImpl implements SubMenuDao {

    @Override
    public List<Opcionesmenu> loadSubMenus() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.getTransaction();
        List subMenuList = null;
        try {
            tx.begin();
            subMenuList = session.createQuery("from Opcionesmenu").list();
            tx.commit();
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println(ex.toString());
        } finally {
            session.close();
        }
        return subMenuList;
    }

    @Override
    public void newSubMenu(Opcionesmenu subMenu) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateSubMenu(Opcionesmenu subMenu) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteSubMenu(Opcionesmenu rol) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   /* public static void main(String[] args) {
        SubMenuDao subMenuDao = new SubMenuDaoImpl();
        List<Opcionesmenu> subMenuList = subMenuDao.loadSubMenus();
        for (Opcionesmenu subMenu : subMenuList) {
            System.out.println(subMenu.getNombre());
        }
    }*/

}
