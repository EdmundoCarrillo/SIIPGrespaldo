/*
 * This code was developed by Edmundo Carrillo on java technologies.
 * Contact: edmundodev@gmail.com
 * Hope you'll find it useful.
 */
package com.ipn.mx.siipg.impl;

import com.ipn.mx.siipg.dao.PeriodoDao;
import com.ipn.mx.siipg.modelo.Periodo;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

public class PeriodoDaoImpl implements PeriodoDao {

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

//    public static void main(String[] args) {
//        PeriodoDao periodoDao = new PeriodoDaoImpl();
//        Periodo periodo = periodoDao.periodoByMAXID();
//        System.out.println(periodo.getId());
//    }
}
