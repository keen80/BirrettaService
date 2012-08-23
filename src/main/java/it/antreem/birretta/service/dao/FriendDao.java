package it.antreem.birretta.service.dao;

import it.antreem.birretta.service.model.Friend;
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

    public ArrayList<Friend> getAllMyFriends(int maxElemet, String id_user);
}
