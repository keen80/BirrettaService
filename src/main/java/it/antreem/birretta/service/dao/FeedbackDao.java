/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.antreem.birretta.service.dao;

import it.antreem.birretta.service.dao.DaoException;
import it.antreem.birretta.service.model.Feedback;
import java.util.List;

/**
 *
 * @author gmorlini
 */
public interface FeedbackDao {

    Feedback findById(String id) throws DaoException;

    List<Feedback> findByIdActivity(String idActivity) throws DaoException;

    List<Feedback> findByIdUser(String idUser) throws DaoException;

    int saveFeedback(Feedback feedback) throws DaoException;

    int udateFeedback(Feedback f) throws DaoException;
    
}
