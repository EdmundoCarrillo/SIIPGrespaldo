/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.siipg.dao;

import com.ipn.mx.siipg.modelo.Notificacion;
import java.util.List;

/**
 *
 * @author Raul
 */
public interface NotificacionDao 
{
    public List<Notificacion> loadNotificaciones();

    public void newNotificacion(Notificacion notificacion);

    public void updateNotificacion(Notificacion notificacion);

    public void deleteNotificacion(Notificacion notificacion);
}
