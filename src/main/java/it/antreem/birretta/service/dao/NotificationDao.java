/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.antreem.birretta.service.dao;

import it.antreem.birretta.service.dao.DaoException;
import it.antreem.birretta.service.model.Notification;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public interface NotificationDao {

    Notification findById(String id) throws DaoException;

    ArrayList<Notification> findByUser(String user) throws DaoException;

    int saveNotification(Notification n) throws DaoException;
    
}
