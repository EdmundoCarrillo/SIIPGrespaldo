/*
 * This code was developed by Edmundo Carrillo on java technologies.
 * Contact: edmundodev@gmail.com
 * Hope you'll find it useful.
 */
package com.ipn.mx.siipg.impl;

import com.ipn.mx.siipg.dao.VariableCheckDao;
import com.ipn.mx.siipg.modelo.Periodo;
import com.ipn.mx.siipg.modelo.Variable;
import com.ipn.mx.siipg.modelo.Variablecheck;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class VariableCheckDaoImpl implements VariableCheckDao {

    @Override
    public List<Variablecheck> loadAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.getTransaction();
        List ejesList = null;
        try {
            tx.begin();
            ejesList = session.createQuery("from Variablecheck").list();
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
    public List<Variablecheck> loadVarForProveedor() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.getTransaction();
        List varList = null;
        try {
            tx.begin();
            varList = session.createQuery("from Variablecheck v where v.periodo.id =(select max(periodo.id) "
                    + "from Variablecheck)and v.variable.unidadresponsable.id=2").list();
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
    public List<Variablecheck> checkByExistingVar(List<Variable> variableList) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List varCheckList = new ArrayList();
        Variablecheck varCheck;
        try {
            for (Variable var : variableList) {
                varCheck = (Variablecheck) session.createQuery("From Variablecheck vc where vc.variable.id = "
                        + var.getId() + " and vc.periodo.id=(select max(periodo.id) from Variablecheck)").uniqueResult();
                if (varCheck == null) {

                    varCheck = new Variablecheck();
                    varCheck.setId(0);
                }
                varCheckList.add(varCheck);
            }
        } catch (HibernateException ex) {
            System.out.println(ex.toString());
        } finally {
            session.close();
        }
        return varCheckList;
    }

    @Override
    public Variablecheck checkByExistingVar(Variable variable) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.getTransaction();
        Variablecheck varCheck = null;
        try {
            tx.begin();
            varCheck = (Variablecheck) session.createQuery("From Variablecheck vc where vc.variable.id = "
                    + variable.getId() + " and vc.periodo.id=(select max(periodo.id) from Variablecheck)").uniqueResult();
            if (varCheck == null) {

                varCheck = new Variablecheck();
                varCheck.setId(0);
                varCheck.setEstatus(0);
                varCheck.setValor(0.0f);
                varCheck.setComentario("No revisada, regrese mas tarde..");
                varCheck.setNoIniciada(true);
            }
            tx.commit();
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println(ex.toString());
        } finally {
            session.close();
        }
        return varCheck;

    }

    @Override
    public void newVariableCheck(Variablecheck varCheck) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.getTransaction();
        try {
            tx.begin();
            session.save(varCheck);
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
    public void updateVariableCheck(Variablecheck varCheck) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.getTransaction();
        try {
            tx.begin();
            session.update(varCheck);
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
//        VariableCheckDao varDao = new VariableCheckDaoImpl();
//        List<Variable> varList = new ArrayList();
//        //--------------
//        Variable varA = new Variable();
//        varA.setId(2);
//        //--------------
//        Variable varB = new Variable();
//        varB.setId(3);
//        //-------------
//        Variable varC = new Variable();
//        varC.setId(7);
//        //-------------
//        Variable varD = new Variable();
//        varD.setId(1);
//
//        varList.add(varA);
//        varList.add(varB);
//        varList.add(varC);
//        varList.add(varD);
//
//        List<Variablecheck> lista = varDao.checkByExistingVar(varList);
//
//        for (Variablecheck vr : lista) {
//            System.out.println(vr.getId());
//
//        }
//    }
//    public static void main(String[] args) {
//        VariableCheckDao varDao = new VariableCheckDaoImpl();
//        Variable variable = new Variable();
//        variable.setId(0);
//        Variablecheck check = varDao.checkByExistingVar(variable);
//        System.out.println(check.getId());
//    }
//    public static void main(String[] args) {
//        VariableCheckDao checkDao = new VariableCheckDaoImpl();
//        Variablecheck check = new Variablecheck();
//        Periodo periodo = new Periodo();
//        periodo.setId(4);
//        Variable variable = new Variable();
//        variable.setId(7);
//
//        check.setId(15);
//        check.setComentario("from hibernate 1we");
//        check.setEstatus(1);
//        check.setValor(85.2f);
//        check.setVariable(variable);
//        check.setPeriodo(periodo);
//
//        checkDao.newVariableCheck(check);
//    }

}
