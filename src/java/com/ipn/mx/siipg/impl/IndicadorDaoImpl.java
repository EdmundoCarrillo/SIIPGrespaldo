/*
 * This code was developed by Edmundo Carrillo on java technologies.
 * Contact: edmundodev@gmail.com
 * Hope you'll find it useful.
 */
package com.ipn.mx.siipg.impl;

import com.ipn.mx.siipg.dao.IndicadorDao;
import com.ipn.mx.siipg.dao.util.EjeHasIndicadorView;
import com.ipn.mx.siipg.modelo.Ejetematico;
import com.ipn.mx.siipg.modelo.Indicador;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class IndicadorDaoImpl implements IndicadorDao {

    @Override
    public List<EjeHasIndicadorView> loadIndicadores() {
        List<EjeHasIndicadorView> indicadorView = new ArrayList();
        List<Ejetematico> ejesList = new EjeTematicoDaoImpl().loadEjes();
        try {
            for (Ejetematico ejeTematico : ejesList) {
                indicadorView.add(new EjeHasIndicadorView(ejeTematico.getNombre(), loadIndicadoresByEje(ejeTematico)));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return indicadorView;
    }

    @Override
    public List<Indicador> loadIndicadoresList() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.getTransaction();
        List indicadoresList = null;
        try {
            tx.begin();
            indicadoresList = session.createQuery("from Indicador").list();
            tx.commit();
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println(ex.toString());
        } finally {
            session.close();
        }
        return indicadoresList;
    }

    @Override
    public void newIndicador(Indicador indicador) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.getTransaction();
        try {
            tx.begin();
            session.save(indicador);
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
    public void updateIndicador(Indicador indicador) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.getTransaction();
        try {
            tx.begin();
            session.update(indicador);
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
    public void deleteIndicador(Indicador indicador) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.getTransaction();
        try {
            tx.begin();
            session.delete(indicador);
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
    public List<Indicador> loadIndicadoresByEje(Ejetematico ejeTematico) {
        List ejesList = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        String stringQuery = "From Indicador i where i.ejetematico.id='" + ejeTematico.getId() + "'";
        try {

            Query query = session.createQuery(stringQuery);
            ejesList = query.list();

        } catch (HibernateException ex) {

            System.out.println(ex.toString());
        } finally {
            session.close();
        }
        return ejesList;
    }
//
//    public static void main(String[] args) {
//        IndicadorDao indicadorDao = new IndicadorDaoImpl();
//        List<Indicador> indicadoresList = indicadorDao.loadIndicadoresList();
//        for (Indicador indicador : indicadoresList) {
//            System.out.println(indicador.getNombre());
//        }
//    }
}
