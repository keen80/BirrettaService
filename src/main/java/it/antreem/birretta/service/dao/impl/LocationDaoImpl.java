package it.antreem.birretta.service.dao.impl;

import com.mongodb.*;
import com.mongodb.util.JSON;
import it.antreem.birretta.service.dao.DaoException;
import it.antreem.birretta.service.dao.LocationDao;
import it.antreem.birretta.service.model.Address;
import it.antreem.birretta.service.model.Location;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bson.types.ObjectId;

/**
 *
 * @author alessio
 */
public class LocationDaoImpl extends AbstractMongoDao implements LocationDao 
{
    public final static String LOCATIONS_COLLNAME = "locations";
    
    private static final Log log = LogFactory.getLog(LocationDaoImpl.class);
    
    @Override
    public int saveLocation(Location l) throws DaoException 
    {
        DB db = null;
        try
        {
            db = getDB();
            db.requestStart();
            DBCollection locations = db.getCollection(LOCATIONS_COLLNAME);
            BasicDBObject loc = createDBObjectFromLocation(l);
            return locations.insert(loc).getN();
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
    public List<Location> findLocationsByNameLike(String name) throws DaoException 
    {
        List<Location> list = new ArrayList<Location>();
        
        DB db = null;
        try
        {
            db = getDB();
            db.requestStart();
            DBCollection users = db.getCollection(LOCATIONS_COLLNAME);
            BasicDBObject query = new BasicDBObject();
            Pattern pattern = Pattern.compile(/*"^" + */name, Pattern.CASE_INSENSITIVE);
            query.put("name", pattern);
            DBCursor cur = users.find(query).limit(10);
            
            while (cur.hasNext()){
                DBObject _l = cur.next();
                Location l = createLocationFromDBObject(_l);
                list.add(l);
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

    @Override
    public Location findById(String id) throws DaoException {
        DBObject obj = findById(id, LOCATIONS_COLLNAME);
        if (obj == null) return null;
        Location l = createLocationFromDBObject(obj);
        return l;
    }
    
    @Override
    public List<Location> findLocationNear(Double lat, Double lon, Double radius) throws DaoException 
    {
        List<Location> list = new ArrayList<Location>();
        if (lat == null || lon == null) return list;
        if (radius == null) radius = 0.8;
        
        DB db = null;
        try
        {
            db = getDB();
            db.requestStart();
            DBCollection users = db.getCollection(LOCATIONS_COLLNAME);
            BasicDBObject query = new BasicDBObject();
            query.put("pos", JSON.parse("{$near : [ " + lon + "," + lat + " ] , $maxDistance : " + radius + "}"));
            DBCursor cur = users.find(query).limit(10);
            
            while (cur.hasNext()){
                DBObject _l = cur.next();
                Location l = createLocationFromDBObject(_l);
                list.add(l);
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
    
    
    protected static Location createLocationFromDBObject(DBObject obj)
    {
        Location l = new Location();
        
        l.setId((ObjectId) obj.get("_id"));
        l.setDesc((String) obj.get("desc"));
        l.setIdLocType((String) obj.get("id_loctype"));
        l.setName((String) obj.get("name"));
        l.setPos((ArrayList<Double>) obj.get("pos"));
        
        BasicDBObject _addr = (BasicDBObject) obj.get("address");
        if (_addr != null){
            Address addr = new Address();
            addr.setStreet((String) _addr.get("street"));
            addr.setNum((Integer) _addr.get("num"));
            addr.setCap((String) _addr.get("cap"));
            addr.setState((String) _addr.get("state"));
            
            l.setAddress(addr);
        }
        
        return l;
    }
    
    // For first insert
    protected static BasicDBObject createDBObjectFromLocation (Location l)
    {
        BasicDBObject _l = new BasicDBObject();
        _l.put("name", l.getName());
        _l.put("desc", l.getDesc());
        _l.put("pos", l.getPos());
        _l.put("id_loctype", l.getIdLocType());
        
        // Address
        if (l.getAddress() != null){
            BasicDBObject addr = new BasicDBObject();
            addr.put("street", l.getAddress().getStreet());
            addr.put("num", l.getAddress().getNum());
            addr.put("cap", l.getAddress().getCap());
            addr.put("state", l.getAddress().getState());
            
            _l.put("address", addr);
        }
        
        return _l;
    }
}
