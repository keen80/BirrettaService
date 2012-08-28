package it.antreem.birretta.service.dao;
import it.antreem.birretta.service.model.FriendsRelation;
import java.util.ArrayList;

/**
 *
 * @author alessio
 */
public interface FriendRelationDao 
{
    public ArrayList<FriendsRelation> getMyFriends(String id_user, int maxElement) throws DaoException;
    public int deleteFriendship(String id1, String id2) throws DaoException;
    public boolean areFriends(String id1, String id2) throws DaoException;
    public int saveFriendsRelation(FriendsRelation fr) throws DaoException;
}
