package it.antreem.birretta.service.dao;

import it.antreem.birretta.service.model.User;
import java.util.List;

/**
 *
 * @author alessio
 */
public interface UserDao
{
    public User findById(String id) throws DaoException;
    public User findUserByUsername(String username) throws DaoException;
    public List<User> findUsers(String username, String first, String last) throws DaoException;
    public int saveUser(User s) throws DaoException;
}
