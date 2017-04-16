/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.siipg.beans;

import com.ipn.mx.siipg.dao.NotificacionDao;
import com.ipn.mx.siipg.dao.VariableDao;
import com.ipn.mx.siipg.impl.NotificacionDaoImpl;
import com.ipn.mx.siipg.impl.VariableDaoImpl;
import com.ipn.mx.siipg.modelo.Notificacion;
import com.ipn.mx.siipg.modelo.Variable;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Raul
 */
@SessionScoped
@Named
public class NotificacionController implements Serializable
{
    private int notificacionesNum;
    private List<Notificacion> notificaciones;
    
    public int getNotificacionesNum() {
        return notificacionesNum;
    }

    public void setNotificacionesNum(int notificacionesNum) {
        this.notificacionesNum = notificacionesNum;
    }
    
    
    public NotificacionController() 
    {
        
    }

    public List<Notificacion> getNotificaciones() {
        return notificaciones;
    }

    
    
    
    
    private NotificacionDao notificacionDao= new NotificacionDaoImpl();
    private int numeroNotificaciones;

    public int getNumeroNotificaciones() {
        return numeroNotificaciones;
    }

    public void setNumeroNotificaciones(int numeroNotificaciones) {
        this.numeroNotificaciones = numeroNotificaciones;
    }
    
    
    public void viewNotificacion(Notificacion notificacion)
    {
        notificacion.setStatus(1);
        notificacionDao.updateNotificacion(notificacion);
        
    }
    
    public List<Notificacion> listNotificacionesSinRevisar()
    {
        VariableDao variableDao= new VariableDaoImpl();       
        List<Variable> variables=variableDao.loadVariables();
        
        List<Notificacion> notificaciones=notificacionDao.loadNotificaciones();
        if(notificaciones!=null)
        {
            /*for(Notificacion n: notificaciones)
            {
                for(Variable v: variables)
                {
                    if(v.getId()==n.getVariableId())
                        n.setNotificacion(n.getNotificacion()+" "+v.getNombre());
                }
                System.out.println("Notificacion "+n.getNotificacion()+ " ");                
            }*/
        }                
        return notificaciones;
    }
    
    
    
    
    
}
