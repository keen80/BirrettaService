package it.antreem.birretta.service.dao.impl;

import com.mongodb.*;
import it.antreem.birretta.service.dao.DaoException;
import it.antreem.birretta.service.dao.FriendRelationReqDao;
import it.antreem.birretta.service.model.Location;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author alessio
 */
public class FriendRelationReqDaoImpl extends AbstractMongoDao implements FriendRelationReqDao
{
    public final static String FRIEND_RELATIONREQS_COLLNAME = "friendrelationreqs";
    
    private static final Log log = LogFactory.getLog(FriendRelationReqDaoImpl.class);
    
    @Override
    public int saveFriendRelationReq(String id1, String id2) throws DaoException 
    {
        DB db = null;
        try
        {
            db = getDB();
            db.requestStart();
            DBCollection friendreqs = db.getCollection(FRIEND_RELATIONREQS_COLLNAME);
            BasicDBObject req = new BasicDBObject();
            req.put("id_requestor", id1);
            req.put("id_requested", id2);
            return friendreqs.insert(req).getN();
        }
        catch(MongoException ex){
            log.error(ex.getLocalizedMessage(), ex);
            throw new DaoException(ex.getLocalizedMessage(), ex);
        }
        finally {
            if (db != null){
                db.requestDone();
            }
        }
    }

    @Override
    public int deleteFriendRelationReq(String id1, String id2) throws DaoException 
    {
        DB db = null;
        try
        {
            db = getDB();
            db.requestStart();
            DBCollection friendreqs = db.getCollection(FRIEND_RELATIONREQS_COLLNAME);
            BasicDBObject req = new BasicDBObject();
            req.put("id_requestor", id1);
            req.put("id_requested", id2);
            int a = friendreqs.remove(req).getN();
            req = new BasicDBObject();
            req.put("id_requestor", id2);
            req.put("id_requested", id1);
            int b = friendreqs.remove(req).getN();
            return a+b;
        }
        catch(MongoException ex){
            log.error(ex.getLocalizedMessage(), ex);
            throw new DaoException(ex.getLocalizedMessage(), ex);
        }
        finally {
            if (db != null){
                db.requestDone();
            }
        }
    }

    @Override
    public boolean existFriendRelationReq(String idRequestor, String idRequested) throws DaoException 
    {
        DB db = null;
        try
        {
            db = getDB();
            db.requestStart();
            DBCollection users = db.getCollection(FRIEND_RELATIONREQS_COLLNAME);
            BasicDBObject query = new BasicDBObject();
            query.put("id_requestor", idRequestor);
            query.put("id_requested", idRequested);
            DBCursor cur = users.find(query);
            if (cur.hasNext()) return true;
            
            return false;
        }
        catch(MongoException ex){
            log.error(ex.getLocalizedMessage(), ex);
            throw new DaoException(ex.getLocalizedMessage(), ex);
        }
        finally {
            if (db != null){
                db.requestDone();
            }
        }
    }

    @Override
    public List<String> findPendingReqs(String idRequested) throws DaoException 
    {
        List<String> list = new ArrayList<String>();
        
        DB db = null;
        try
        {
            db = getDB();
            db.requestStart();
            DBCollection friendreqs = db.getCollection(FRIEND_RELATIONREQS_COLLNAME);
            BasicDBObject query = new BasicDBObject();
            query.put("id_requested", idRequested);
            DBCursor cur = friendreqs.find(query);
            
            while (cur.hasNext()){
                DBObject _l = cur.next();
                list.add((String) _l.get("id_requestor"));
            }
        }
        catch(MongoException ex){
            log.error(ex.getLocalizedMessage(), ex);
            throw new DaoException(ex.getLocalizedMessage(), ex);
        }
        finally {
            if (db != null){
                db.requestDone();
            }
        }
        
        return list;
    }
    
}
