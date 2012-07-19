package it.antreem.birretta.service.dao.impl;

import com.mongodb.*;
import it.antreem.birretta.service.dao.DaoException;
import it.antreem.birretta.service.dao.FriendReqDao;
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
public class FriendReqDaoImpl extends AbstractMongoDao implements FriendReqDao
{
    public final static String FRIENDREQS_COLLNAME = "friendreqs";
    
    private static final Log log = LogFactory.getLog(FriendReqDaoImpl.class);
    
    @Override
    public int saveFriendReq(String id1, String id2) throws DaoException 
    {
        DB db = null;
        try
        {
            db = getDB();
            db.requestStart();
            DBCollection friendreqs = db.getCollection(FRIENDREQS_COLLNAME);
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
    public int deleteFriendReq(String id1, String id2) throws DaoException 
    {
        DB db = null;
        try
        {
            db = getDB();
            db.requestStart();
            DBCollection friendreqs = db.getCollection(FRIENDREQS_COLLNAME);
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
    public boolean existFriendReq(String idRequestor, String idRequested) throws DaoException 
    {
        DB db = null;
        try
        {
            db = getDB();
            db.requestStart();
            DBCollection users = db.getCollection(FRIENDREQS_COLLNAME);
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
            DBCollection friendreqs = db.getCollection(FRIENDREQS_COLLNAME);
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
