/*
 * This code was developed by Edmundo Carrillo on java technologies.
 * Contact: edmundodev@gmail.com
 * Hope you'll find it useful.
 */
package com.ipn.mx.siipg.impl;

import com.ipn.mx.siipg.dao.EjeTematicoDao;
import com.ipn.mx.siipg.modelo.Ejetematico;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class EjeTematicoDaoImpl implements EjeTematicoDao {

    @Override
    public List<Ejetematico> loadEjes() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.getTransaction();
        List ejesList = null;
        try {
            tx.begin();
            ejesList = session.createQuery("from Ejetematico").list();
            tx.commit();
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println(ex.toString());
        } finally {
            session.close();
        }
        return ejesList;
    }

    @Override
    public void newEjeTematico(Ejetematico ejeTematico) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.getTransaction();
        try {
            tx.begin();
            session.save(ejeTematico);
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
    public void updateEjeTematico(Ejetematico ejeTematico) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.getTransaction();
        try {
            tx.begin();
            session.update(ejeTematico);
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
    public void deleteEjeTematico(Ejetematico ejeTematico) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.getTransaction();
        try {
            tx.begin();
            session.delete(ejeTematico);
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
//        EjeTematicoDao ejeDao = new EjeTematicoDaoImpl();
//        Ejetematico ejeTematico = new Ejetematico();
//        ejeTematico.setNombre("eje 3");
//        ejeTematico.setEstatus(1);
//        ejeTematico.setDescripcion("des 3");
//        ejeDao.newEjeTematico(ejeTematico);
//    }
}
