package it.antreem.birretta.service.dao;

import it.antreem.birretta.service.model.User;

/**
 *
 * @author alessio
 */
public interface UserDao
{
    public User findById(String id) throws DaoException;
    public User findUserByUsername(String username) throws DaoException;
    public int saveUser(User s) throws DaoException;
}
