package it.antreem.birretta.service.dao;

/**
 *
 * @author alessio
 */
public interface FriendRelationDao 
{
    public int saveFriendship(String id1, String id2) throws DaoException;
    public int deleteFriendship(String id1, String id2) throws DaoException;
    public boolean areFriends(String id1, String id2) throws DaoException;
}
