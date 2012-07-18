package it.antreem.birretta.service.dao.impl;

import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.MongoException;
import it.antreem.birretta.service.util.BirrettaServiceProperties;
import java.net.UnknownHostException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

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
}
