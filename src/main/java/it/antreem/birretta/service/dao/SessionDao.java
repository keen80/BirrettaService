package it.antreem.birretta.service.dao;

import it.antreem.birretta.service.model.Session;

/**
 *
 * @author alessio
 */
public interface SessionDao 
{
    public Session findSessionByUsername(String username) throws DaoException;
    public Session findSessionBySid(String sid) throws DaoException;
    public int saveSession(Session s) throws DaoException;
    public int deleteSessionBySid(String sid) throws DaoException;
}
