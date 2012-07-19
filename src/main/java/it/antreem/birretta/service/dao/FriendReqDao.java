package it.antreem.birretta.service.dao;

import java.util.List;

/**
 *
 * @author alessio
 */
public interface FriendReqDao 
{
    public int saveFriendReq(String id1, String id2) throws DaoException;
    public int deleteFriendReq(String id1, String id2) throws DaoException;
    public List<String> findPendingReqs(String requested) throws DaoException;
    public boolean existFriendReq(String id1, String id2) throws DaoException;
}
