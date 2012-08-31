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
            Pattern pattern = Pattern.compile(/*"^" + */name.trim(), Pattern.CASE_INSENSITIVE);
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
    public Location findLocationByName(String name) throws DaoException 
    {
        DB db = null;
        try
        {
            db = getDB();
            db.requestStart();
            DBCollection users = db.getCollection(LOCATIONS_COLLNAME);
            BasicDBObject query = new BasicDBObject();
            query.put("name", name);
            DBCursor cur = users.find(query);
            
            while (cur.hasNext()){
                DBObject _u = cur.next();
                Location u = createLocationFromDBObject(_u);
                assert u.getName().equals(name);
                return u;
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
    @Override
    public Location findByIdLocation(String idLocation) {
        DB db = null;
        try
        {
            db = getDB();
            db.requestStart();
            DBCollection users = db.getCollection(LOCATIONS_COLLNAME);
            BasicDBObject query = new BasicDBObject();
            query.put("idLocation", idLocation);
            DBCursor cur = users.find(query);
            
            while (cur.hasNext()){
                DBObject _l = cur.next();
                Location l = createLocationFromDBObject(_l);
                assert l.getIdLocation().equals(idLocation);
                return l;
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
    public int updateLocation(Location l) {
         DB db = null;
        try
        {
            db = getDB();
            db.requestStart();
            DBCollection locations = db.getCollection(LOCATIONS_COLLNAME);
             DBObject _l = createDBObjectFromLocation(l);
            return locations.update(new BasicDBObject("idLocation",l.getIdLocation()), _l).getN();
            
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

    protected static Location createLocationFromDBObject(DBObject obj)
    {
        Location l = new Location();
        
        l.setId((ObjectId) obj.get("_id"));
        l.setIdLocation((String) obj.get("IdLocation"));
        l.setUrl((String) obj.get("desc"));
        l.setCategories((ArrayList<String>) obj.get("categories"));
        l.setName((String) obj.get("name"));
        l.setPos((ArrayList<Double>) obj.get("pos"));
        l.setAddress((String) obj.get("address"));
        l.setCity((String) obj.get("city"));
        l.setCountry((String) obj.get("country"));
        
        return l;
    }
    
    // For first insert
    protected static BasicDBObject createDBObjectFromLocation (Location l)
    {
        BasicDBObject _l = new BasicDBObject();
        _l.put("idLocation", l.getIdLocation());
        _l.put("name", l.getName().trim());
        _l.put("url", l.getUrl());
        _l.put("pos", l.getPos());
        _l.put("categories", l.getCategories());
        _l.put("address", l.getAddress());
        _l.put("country", l.getCountry());
        _l.put("city", l.getCity());

        return _l;
    }

  
    
}
