/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.siipg.impl;

import com.ipn.mx.siipg.beans.SessionController;
import com.ipn.mx.siipg.dao.NotificacionDao;
import com.ipn.mx.siipg.modelo.Notificacion;
import com.ipn.mx.siipg.modelo.Unidadresponsable;
import com.ipn.mx.siipg.modelo.Variable;
import java.util.List;
import java.util.stream.Collectors;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Raul
 */
public class NotificacionDaoImpl implements NotificacionDao
{

    @Override
    public List<Notificacion> loadNotificaciones() {
        Session session= HibernateUtil.getSessionFactory().openSession();
        Transaction tx= session.getTransaction();
        //Iniciamos la transacción
        tx.begin();
        List notificacionesList= null;
        
        try
        {
            notificacionesList=session.createQuery("from Notificacion").list();
            tx.commit();
        }catch(Exception e )
        {
            e.printStackTrace();
        }
        finally
        {
            session.close();
        }
        return ( List<Notificacion>)notificacionesList.stream().filter( (t) -> {
           Notificacion n= (Notificacion) t;
            Unidadresponsable unidadUser= new SessionController().getUnidadObjectFromSession();
           VariableDaoImpl variableDao= new VariableDaoImpl();
           List<Variable> variables=variableDao.loadVariableByUnidad(unidadUser);
           if(n.getStatus()!=0)
                {
                    return false;
                }
           else
                {
                    for(Variable v:variables)
                    {
                        if(v.getId()==n.getVariableId())
                        {
                            return true;
                        }
                    }
                    return false;
                }
        }).collect(Collectors.toList());
        
    }

    @Override
    public void newNotificacion(Notificacion notificacion) {
        Session session= HibernateUtil.getSessionFactory().openSession();
        Transaction tx= session.getTransaction();
        //Iniciamos la transacción
        tx.begin();
        try
        {
            session.save(notificacion);
            tx.commit();
        }catch(Exception e )
        {
            tx.rollback();
            System.err.println("No se ha guardado el registro de Notificación");
            e.printStackTrace();
        }
        finally
        {
            session.close();
        }        
    }

    @Override
    public void updateNotificacion(Notificacion notificacion) {
        Session session= HibernateUtil.getSessionFactory().openSession();
        Transaction tx= session.getTransaction();
        //Iniciamos la transacción
        tx.begin();
        try
        {
            session.update(notificacion);
            tx.commit();
        }catch(Exception e )
        {
            tx.rollback();
            e.printStackTrace();
        }
        finally
        {
            session.close();
        }
        
    }

    @Override
    public void deleteNotificacion(Notificacion notificacion) {
        Session session= HibernateUtil.getSessionFactory().openSession();
        Transaction tx= session.getTransaction();
        //Iniciamos la transacción
        tx.begin();
        try
        {
            session.delete(notificacion);
            tx.commit();
        }catch(Exception e )
        {
            tx.rollback();
            e.printStackTrace();
        }
        finally
        {
            session.close();
        }
        
    }
    
}
