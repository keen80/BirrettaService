package it.antreem.birretta.service.dao.impl;

import com.mongodb.*;
import it.antreem.birretta.service.dao.DaoException;
import it.antreem.birretta.service.dao.LocationCategoryDao;
import it.antreem.birretta.service.dao.LocationCategoryDao;
import it.antreem.birretta.service.model.LocationCategory;
import it.antreem.birretta.service.model.LocationCategory;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bson.types.ObjectId;

/**
 *
 * @author gmorlini
 */
public class LocationCategoryDaoImpl extends AbstractMongoDao implements LocationCategoryDao 
{
    public final static String LOCATIONCATEGORY_COLLNAME = "locationCategories";
    
    private static final Log log = LogFactory.getLog(LocationCategoryDaoImpl.class);
  
     
    @Override
    public LocationCategory findById(String id) throws DaoException 
    {
        DBObject obj = findById(id, LOCATIONCATEGORY_COLLNAME);
        if (obj == null) return null;
        LocationCategory l = createLocationCategoryFromDBObject(obj);
        return l;
    }
    

    @Override
    public List<LocationCategory> findLocationCategoryByNameLike(String name) throws DaoException {
        List<LocationCategory> list = new ArrayList<LocationCategory>();
        
        DB db = null;
        try
        {
            db = getDB();
            db.requestStart();
            DBCollection LocationCategorys = db.getCollection(LOCATIONCATEGORY_COLLNAME);
            BasicDBObject query = new BasicDBObject();
            Pattern pattern = Pattern.compile(/*"^" + */name, Pattern.CASE_INSENSITIVE);
            query.put("name", pattern);
            DBCursor cur = LocationCategorys.find(query).limit(10);
            
            while (cur.hasNext()){
                DBObject _l = cur.next();
                LocationCategory l = createLocationCategoryFromDBObject(_l);
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
    public LocationCategory findLocationCategoryByIdCategory(String idCategory) throws DaoException {
    
        DB db = null;
        try
        {
            db = getDB();
            db.requestStart();
            DBCollection LocationCategorys = db.getCollection(LOCATIONCATEGORY_COLLNAME);
            BasicDBObject query = new BasicDBObject();
            query.put("idCategory", idCategory);
            DBCursor cur = LocationCategorys.find(query);
            
            while (cur.hasNext()){
                DBObject _l = cur.next();
                LocationCategory l = createLocationCategoryFromDBObject(_l);
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
    public int saveLocationCategory(LocationCategory l) throws DaoException {
        DB db = null;
        try
        {
            db = getDB();
            db.requestStart();
            DBCollection LocationCategories = db.getCollection(LOCATIONCATEGORY_COLLNAME);
            BasicDBObject loc = createDBObjectFromLocationCategory(l);
            return LocationCategories.insert(loc).getN();
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
  
    protected static LocationCategory createLocationCategoryFromDBObject(DBObject obj)
    {
        LocationCategory l = new LocationCategory();

        l.setId((ObjectId) obj.get("_id"));
        
        l.setIdCategory((String) obj.get("idCategory"));
        l.setName((String) obj.get("name"));
        l.setShortName((String)obj.get("shortName"));
        l.setPluralName((String)obj.get("pluralName"));
        l.setImage((String)obj.get("image"));
        return l;
    }
   
    // For first insert
    protected static BasicDBObject createDBObjectFromLocationCategory (LocationCategory l)
    {
        BasicDBObject _l = new BasicDBObject();
        _l.put("idCategory", l.getIdCategory().trim());
        _l.put("name", l.getName());
        _l.put("shortName", l.getShortName());
        _l.put("pluralName", l.getPluralName());
        _l.put("image", l.getImage());
        return _l;
    }

    @Override
    public List<LocationCategory> findAll() throws DaoException {
             List<LocationCategory> list = new ArrayList<LocationCategory>();
        
        DB db = null;
        try
        {
            db = getDB();
            db.requestStart();
            DBCollection LocationCategorys = db.getCollection(LOCATIONCATEGORY_COLLNAME);
            DBCursor cur = LocationCategorys.find();
            
            while (cur.hasNext()){
                DBObject _l = cur.next();
                LocationCategory l = createLocationCategoryFromDBObject(_l);
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
}
