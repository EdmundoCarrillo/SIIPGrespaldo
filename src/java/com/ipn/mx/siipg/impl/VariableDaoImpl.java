/*
 * This code was developed by Edmundo Carrillo on java technologies.
 * Contact: edmundodev@gmail.com
 * Hope you'll find it useful.
 */
package com.ipn.mx.siipg.impl;

import com.ipn.mx.siipg.dao.VariableDao;
import com.ipn.mx.siipg.dao.util.VarProveedorView;
import com.ipn.mx.siipg.modelo.Unidadresponsable;
import com.ipn.mx.siipg.modelo.Variable;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class VariableDaoImpl implements VariableDao {

    @Override
    public List<Variable> loadVariables() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.getTransaction();
        List varList = null;
        try {
            tx.begin();
            varList = session.createQuery("from Variable").list();
            tx.commit();
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println(ex.toString());
        } finally {
            session.close();
        }
        return varList;
    }

    @Override
    public void newVariable(Variable variable) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.getTransaction();
        try {
            tx.begin();
            session.save(variable);
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
    public void updateVariable(Variable variable) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.getTransaction();
        try {
            tx.begin();
            session.update(variable);
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
    public void deleteVariable(Variable variable) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.getTransaction();
        try {
            tx.begin();
            session.delete(variable);
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
    public List<Variable> loadVariableByUnidad(Unidadresponsable unidadResponsable) {
        List varList = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        String stringQuery = "From Variable v where v.unidadresponsable.id=" + unidadResponsable.getId() + "";
        try {

            Query query = session.createQuery(stringQuery);
            varList = query.list();

        } catch (HibernateException ex) {
            System.out.println(ex.toString());
        } finally {
            session.close();
        }
        return varList;

    }

//    @Override
//    public List<VarProveedorView> loadVarsForProveedor(Unidadresponsable unidadResponsable) {
//        List varList = null;
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        String stringQuery = "select v.nombre from Variable v";
//        try {
//
//            Query query = session.createQuery(stringQuery);
//            varList = query.list();
//
//        } catch (HibernateException ex) {
//            System.out.println(ex.toString());
//        } finally {
//            session.close();
//        }
//        return varList;
//
//    }
//
//    public static void main(String[] args) {
//        VariableDao variableDao = new VariableDaoImpl();
//        Unidadresponsable unidadResponsable = new Unidadresponsable();
//        unidadResponsable.setId(1);
//        List<VarProveedorView> variableList = variableDao.loadVarsForProveedor(unidadResponsable);
//        for (VarProveedorView var : variableList) {
//            System.out.println(var.getNombre());
//        }
//    }
}
