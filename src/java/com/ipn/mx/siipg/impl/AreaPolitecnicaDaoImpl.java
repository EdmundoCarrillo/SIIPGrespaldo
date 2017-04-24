/*
 * This code was developed by Edmundo Carrillo on java technologies.
 * Contact: edmundodev@gmail.com
 * Hope you'll find it useful.
 */
package com.ipn.mx.siipg.impl;

import com.ipn.mx.siipg.dao.AreaPolitecnicaDao;
import com.ipn.mx.siipg.modelo.Areapolitecnica;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class AreaPolitecnicaDaoImpl implements AreaPolitecnicaDao {

    @Override
    public List<Areapolitecnica> loadAreas() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.getTransaction();
        List areaList = null;
        try {
            tx.begin();
            areaList = session.createQuery("from Areapolitecnica").list();
            tx.commit();
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println(ex.toString());
        } finally {
            session.close();
        }
        return areaList;
    }

    @Override
    public void newAreaPolitecnica(Areapolitecnica areaPolitecnica) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.getTransaction();
        try {
            tx.begin();
            session.save(areaPolitecnica);
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
    public void updateAreaPolitecnica(Areapolitecnica areaPolitecnica) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.getTransaction();
        try {
            tx.begin();
            session.update(areaPolitecnica);
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
    public void deleteAreaPolitecnica(Areapolitecnica areaPolitecnica) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.getTransaction();
        try {
            tx.begin();
            session.delete(areaPolitecnica);
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

//    public static void main(String[] args) {
//        AreaPolitecnicaDao areaDao = new AreaPolitecnicaDaoImpl();
//        List<Areapolitecnica> areaList = areaDao.loadAreas();
//        for (Areapolitecnica area : areaList) {
//            System.out.println(area.getNombre());
//        }
//    }
}
