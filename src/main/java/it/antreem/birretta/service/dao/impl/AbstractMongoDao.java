package it.antreem.birretta.service.dao.impl;

import com.mongodb.*;
import it.antreem.birretta.service.dao.DaoException;
import it.antreem.birretta.service.model.MongoDBObject;
import it.antreem.birretta.service.util.BirrettaServiceProperties;
import java.net.UnknownHostException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bson.types.ObjectId;

/**
 * Tutti i DAO dovranno estendere questa classe.
 * 
 * @author alessio
 */
public abstract class AbstractMongoDao 
{
    private static final Log log = LogFactory.getLog(AbstractMongoDao.class);
    
    // Thread-safe connection-pool => static
    private final static Mongo m;
    
    // Mongo connection-pool initialization
    static
    {
        try 
        {
            String host = BirrettaServiceProperties.MONGODB_HOST;
            int port = Integer.parseInt(BirrettaServiceProperties.MONGODB_PORT);
            m = new Mongo(host, port);
        
        } catch (UnknownHostException ex) {
            log.error("Unknown host: " + ex.getLocalizedMessage(), ex);
            throw new ExceptionInInitializerError(ex);
        } catch (MongoException ex) {
            log.error("MongoException: " + ex.getLocalizedMessage(), ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    protected DB getDB()
    {
        try
        {
            return m.getDB(BirrettaServiceProperties.MONGODB_DBNAME);
        }
        catch(MongoException ex){
            log.error("MongoException: " + ex.getLocalizedMessage(), ex);
            throw ex;
        }
    }
    
    protected DBObject findById(String id, String collName) throws DaoException
    {
        if (!ObjectId.isValid(id)) return null;
        
        ObjectId _id = new ObjectId(id);
        DB db = null;
        try
        {
            db = getDB();
            db.requestStart();
            DBCollection coll = db.getCollection(collName);
            BasicDBObject query = new BasicDBObject();
            query.put("_id", _id);
            DBCursor cur = coll.find(query);
            
            while (cur.hasNext()){
                DBObject obj = cur.next();
                return obj;
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
}
