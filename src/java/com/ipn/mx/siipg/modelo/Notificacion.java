/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.siipg.modelo;

import com.ipn.mx.siipg.dao.NotificacionDao;
import java.io.Serializable;

/**
 *
 * @author Raul
 */
public class Notificacion implements Serializable
{
    private Integer id;
    private String notificacion;
    /***
     * 0: La notificación no ha sido atendida
     * 1: La notificación ha sido atendida
     * 2: La notificación ha sido borrada
     */
    private Integer status;
    private Integer variableId;
    
    
    
    public Integer getId() {
        return id;
    }

    public Notificacion() {
        NotificacionDao notificacionDao;
    }

    public Integer getVariableId() {
        return variableId;
    }

    public void setVariableId(Integer variableId) {
        this.variableId = variableId;
    }

    
    
    
    
    public void setId(Integer id) {
        this.id = id;
    }

    public String getNotificacion() {
        return notificacion;
    }

    public void setNotificacion(String notificacion) {
        this.notificacion = notificacion;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    
    
    
    
    
}
