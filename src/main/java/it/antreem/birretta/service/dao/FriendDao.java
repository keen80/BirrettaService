package it.antreem.birretta.service.dao;

import java.util.ArrayList;

/**
 *
 * @author alessio
 */
public interface FriendDao 
{
    public int saveFriend(String id1, String id2) throws DaoException;
    public int deleteFriend(String id1, String id2) throws DaoException;
    public ArrayList getAllFriends(int id1) throws DaoException;
}
