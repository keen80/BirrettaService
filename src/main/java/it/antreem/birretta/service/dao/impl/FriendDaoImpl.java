package it.antreem.birretta.service.dao.impl;

import com.mongodb.*;
import it.antreem.birretta.service.dao.DaoException;
import it.antreem.birretta.service.dao.FriendDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author alessio
 */
public class FriendDaoImpl extends AbstractMongoDao implements FriendDao
{
    public final static String FRIENDS_COLLNAME = "friends";
    
    private static final Log log = LogFactory.getLog(FriendDaoImpl.class);
    
    @Override
    public int saveFriendship(String id1, String id2) throws DaoException 
    {
        DB db = null;
        try
        {
            db = getDB();
            db.requestStart();
            DBCollection friends = db.getCollection(FRIENDS_COLLNAME);
            BasicDBObject req = new BasicDBObject();
            req.put("id_1", id1);
            req.put("id_2", id2);
            return friends.insert(req).getN();
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
    public int deleteFriendship(String id1, String id2) throws DaoException 
    {
        DB db = null;
        try
        {
            db = getDB();
            db.requestStart();
            DBCollection friends = db.getCollection(FRIENDS_COLLNAME);
            BasicDBObject req = new BasicDBObject();
            req.put("id_1", id1);
            req.put("id_2", id2);
            int a = friends.remove(req).getN();
            req = new BasicDBObject();
            req.put("id_1", id2);
            req.put("id_2", id1);
            int b = friends.remove(req).getN();
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
    public boolean areFriends(String id1, String id2) throws DaoException 
    {
        DB db = null;
        try
        {
            db = getDB();
            db.requestStart();
            DBCollection users = db.getCollection(FRIENDS_COLLNAME);
            BasicDBObject query = new BasicDBObject();
            query.put("id_1", id1);
            query.put("id_2", id2);
            DBCursor cur = users.find(query);
            if (cur.hasNext()) return true;
            
            query = new BasicDBObject();
            query.put("id_1", id2);
            query.put("id_2", id1);
            cur = users.find(query);
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
    
}
