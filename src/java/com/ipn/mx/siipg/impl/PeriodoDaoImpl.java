/*
 * This code was developed by Edmundo Carrillo on java technologies.
 * Contact: edmundodev@gmail.com
 * Hope you'll find it useful.
 */
package com.ipn.mx.siipg.impl;

import com.ipn.mx.siipg.dao.PeriodoDao;
import com.ipn.mx.siipg.modelo.Periodo;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class PeriodoDaoImpl implements PeriodoDao {
    
    @Override
    public List<Periodo> loadPeriodos() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.getTransaction();
        List periodoList = null;
        try {
            tx.begin();
            periodoList = session.createQuery("from Periodo").list();
            tx.commit();
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println(ex.toString());
        } finally {
            session.close();
        }
        return periodoList;
    }

    @Override
    public void newPeriodo(Periodo periodo) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.getTransaction();
        try {
            tx.begin();
            session.save(periodo);
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
    public void updatePeriodo(Periodo periodo) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.getTransaction();
        try {
            tx.begin();
            session.update(periodo);
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
    public void deletePeriodo(Periodo periodo) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.getTransaction();
        try {
            tx.begin();
            session.save(periodo);
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
    public Periodo periodoByMAXID() {
        Periodo periodo = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        String stringQuery = "From Periodo where id = (select max(id) from Periodo )";
        try {
            Query query = session.createQuery(stringQuery);
            periodo = (Periodo) query.uniqueResult();

        } catch (HibernateException ex) {
            System.out.println(ex.toString());
        } finally {
            session.close();
        }
        return periodo;
    }

    /*public static void main(String[] args) {
        Calendar p = new GregorianCalendar();
        int ano = p.get(Calendar.YEAR);
          System.out.println("anño"+ano);
          System.out.println(p);
    }*/
}
