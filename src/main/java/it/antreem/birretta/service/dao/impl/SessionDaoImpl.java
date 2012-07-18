package it.antreem.birretta.service.dao.impl;

import com.mongodb.*;
import it.antreem.birretta.service.dao.DaoException;
import it.antreem.birretta.service.dao.SessionDao;
import it.antreem.birretta.service.model.Session;
import java.util.Date;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bson.types.ObjectId;

/**
 *
 * @author alessio
 */
public class SessionDaoImpl extends AbstractMongoDao implements SessionDao 
{
    private static final Log log = LogFactory.getLog(SessionDaoImpl.class);
    
    @Override
    public Session findSessionByUsername(String username) throws DaoException 
    {
        DB db = null;
        try
        {
            db = getDB();
            db.requestStart();
            DBCollection sessions = db.getCollection("sessions");
            BasicDBObject query = new BasicDBObject();
            query.put("username", username);
            DBCursor cur = sessions.find(query);
            
            while (cur.hasNext()){
                DBObject _s = cur.next();
                Session s = createSessionFromDBObject(_s);
                assert s.getUsername().equals(username);
                return s;
            }
            
            return null;
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
    public Session findSessionBySid(String sid) throws DaoException 
    {
        DB db = null;
        try
        {
            db = getDB();
            db.requestStart();
            DBCollection sessions = db.getCollection("sessions");
            BasicDBObject query = new BasicDBObject();
            query.put("sid", sid);
            DBCursor cur = sessions.find(query);
            
            while (cur.hasNext()){
                DBObject _s = cur.next();
                Session s = createSessionFromDBObject(_s);
                assert s.getSid().equals(sid);
                return s;
            }
            
            return null;
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
    public int saveSession(Session s) throws DaoException 
    {
        DB db = null;
        try
        {
            db = getDB();
            db.requestStart();
            DBCollection sessions = db.getCollection("sessions");
            BasicDBObject session = createDBObjectFromSession(s);
            return sessions.insert(session).getN();
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
    public int deleteSessionBySid(String sid) throws DaoException 
    {
        DB db = null;
        try
        {
            db = getDB();
            db.requestStart();
            DBCollection sessions = db.getCollection("sessions");
            BasicDBObject query = new BasicDBObject();
            query.put("sid", sid);
            return sessions.remove(query).getN();
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
    
    protected static Session createSessionFromDBObject(DBObject obj)
    {
        Session s = new Session();

        s.setId((ObjectId) obj.get("_id"));
        s.setUsername((String) obj.get("username"));
        s.setSid((String) obj.get("sid"));
        s.setTimestamp((Date) obj.get("timestamp"));
        
        return s;
    }
    
    // For first insert
    protected static BasicDBObject createDBObjectFromSession (Session s)
    {
        BasicDBObject _s = new BasicDBObject();
        _s.put("username", s.getUsername());
        _s.put("sid", s.getSid());
        _s.put("timestamp", s.getTimestamp());
        return _s;
    }
}
