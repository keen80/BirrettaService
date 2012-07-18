package it.antreem.birretta.service.dao;

import it.antreem.birretta.service.model.User;

/**
 *
 * @author alessio
 */
public interface UserDao
{
    public User findUserByUsername(String username) throws DaoException;
}
