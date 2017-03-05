/*
 * This code was developed by Edmundo Carrillo on java technologies.
 * Contact: edmundodev@gmail.com
 * Hope you'll find it useful.
 */
package com.ipn.mx.siipg.impl;

import com.ipn.mx.siipg.dao.VariableCheckDao;
import com.ipn.mx.siipg.modelo.Variable;
import com.ipn.mx.siipg.modelo.Variablecheck;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class VariableCheckDaoImpl implements VariableCheckDao {

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
}
