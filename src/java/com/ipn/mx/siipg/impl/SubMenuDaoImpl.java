/*
 * This code was developed by Edmundo Carrillo on java technologies.
 * Contact: edmundodev@gmail.com
 * Hope you'll find it useful.
 */
package com.ipn.mx.siipg.impl;

import com.ipn.mx.siipg.dao.SubMenuDao;
import com.ipn.mx.siipg.modelo.Menu;
import com.ipn.mx.siipg.modelo.Opcionesmenu;
import com.ipn.mx.siipg.modelo.Variablecheck;
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

    @Override
    public void vistaProvedorEnable() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.getTransaction();
        try {
            tx.begin();
            Menu menu = new Menu();
            menu.setIdmenu(6);
            Opcionesmenu subMenu = new Opcionesmenu();
            subMenu.setId(8);
            subMenu.setNombre("Cargar Variables");
            subMenu.setRuta("views/proveedor/variables/index");
            subMenu.setIcon("fa fa-database");
            subMenu.setMenu(menu);
            session.update(subMenu);
            tx.commit();
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println(ex.toString());
        } finally {
            session.close();
        }
    }

    @Override
    public void vistaProvedorDisable() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.getTransaction();
        try {
            tx.begin();
            Menu menu = new Menu();
            menu.setIdmenu(6);
            Opcionesmenu subMenu = new Opcionesmenu();
            subMenu.setId(8);
            subMenu.setNombre("Cargar Variables");
            subMenu.setRuta("views/proveedor/variables/invalidIndex");
            subMenu.setIcon("fa fa-database");
            subMenu.setMenu(menu);
            session.update(subMenu);
            tx.commit();
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println(ex.toString());
        } finally {
            session.close();
        }
    }

    @Override
    public Opcionesmenu getRutaEstatus() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.getTransaction();
        Opcionesmenu subMenu = null;
        try {
            tx.begin();
            subMenu = (Opcionesmenu) session.createQuery("From Opcionesmenu om where om.id =8 ").uniqueResult();
            tx.commit();
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println(ex.toString());
        } finally {
            session.close();
        }
        return subMenu;
    }

    public static void main(String[] args) {
        SubMenuDao subMenuDao = new SubMenuDaoImpl();
        Opcionesmenu subMenu = subMenuDao.getRutaEstatus();
        System.out.println(subMenu.getRuta());
    }

}
