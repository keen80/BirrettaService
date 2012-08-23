package it.antreem.birretta.service.dao;

import java.util.List;

/**
 *
 * @author alessio
 */
public interface FriendRelationReqDao 
{
    public int saveFriendRelationReq(String id1, String id2) throws DaoException;
    public int deleteFriendRelationReq(String id1, String id2) throws DaoException;
    public List<String> findPendingReqs(String requested) throws DaoException;
    public boolean existFriendRelationReq(String id1, String id2) throws DaoException;
}
