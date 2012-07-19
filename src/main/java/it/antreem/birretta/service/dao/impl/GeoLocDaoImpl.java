package it.antreem.birretta.service.dao.impl;

import com.mongodb.*;
import it.antreem.birretta.service.dao.DaoException;
import it.antreem.birretta.service.dao.GeoLocDao;
import it.antreem.birretta.service.model.GeoLoc;
import java.util.ArrayList;
import java.util.Date;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bson.types.ObjectId;

/**
 *
 * @author alessio
 */
public class GeoLocDaoImpl extends AbstractMongoDao implements GeoLocDao 
{
    public final static String GEOLOC_COLLNAME = "geolocs";
    
    private static final Log log = LogFactory.getLog(GeoLocDaoImpl.class);
    
    @Override
    public int updateLoc(String idUser, double lat, double lon) throws DaoException 
    {
        DB db = null;
        try
        {
            db = getDB();
            db.requestStart();
            DBCollection geolocs = db.getCollection(GEOLOC_COLLNAME);
            GeoLoc _g = new GeoLoc(idUser, lat, lon);
            BasicDBObject geoloc = createDBObjectFromGeoLoc(_g);
            BasicDBObject query = new BasicDBObject();
            query.put("id_user", idUser);
            
            return geolocs.update(query, geoloc, true, false).getN();
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
    
    
    protected static GeoLoc createGeoLocFromDBObject(DBObject obj)
    {
        GeoLoc g = new GeoLoc();

        g.setId((ObjectId) obj.get("_id"));
        g.setIdUser((String) obj.get("id_user"));
        g.setPos((ArrayList<Double>) obj.get("pos"));
        g.setTimestamp((Date) obj.get("timestamp"));
        
        return g;
    }
    
    // For upsert
    protected static BasicDBObject createDBObjectFromGeoLoc (GeoLoc g)
    {
        BasicDBObject obj = new BasicDBObject();
        obj.put("id_user", g.getIdUser());
        obj.put("pos", g.getPos());
        obj.put("timestamp", g.getTimestamp());
        return obj;
    }
}
