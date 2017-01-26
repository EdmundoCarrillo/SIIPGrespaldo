/*
 * This code was developed by Edmundo Carrillo on java technologies.
 * Contact: edmundodev@gmail.com
 * Hope you'll find it useful.
 */
package com.ipn.mx.siipg.impl;

import com.ipn.mx.siipg.dao.EntidadFederativaDao;
import com.ipn.mx.siipg.modelo.Entidadfederativa;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class EntidadFederativaDaoImpl implements EntidadFederativaDao {

    @Override
    public List<Entidadfederativa> loadEntidades() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.getTransaction();
        List entidadesList = null;
        try {
            tx.begin();
            entidadesList = session.createQuery("from Entidadfederativa").list();
            tx.commit();
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println(ex.toString());
        } finally {
            session.close();
        }
        return entidadesList;
    }

    @Override
    public void newEntidad(Entidadfederativa entidadFederativa) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateEntidad(Entidadfederativa entidadFederativa) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteEntidad(Entidadfederativa entidadFederativa) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//    public static void main(String[] args) {
//        EntidadFederativaDao entidadDao = new EntidadFederativaDaoImpl();
//        List<Entidadfederativa> entidadList = entidadDao.loadEntidades();
//        for (Entidadfederativa entidad : entidadList) {
//            System.out.println(entidad.getNombre());
//        }
//    }

}
