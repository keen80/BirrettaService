package it.antreem.birretta.service.dao.impl;

import com.mongodb.*;
import it.antreem.birretta.service.dao.BeerDao;
import it.antreem.birretta.service.dao.DaoException;
import it.antreem.birretta.service.model.Beer;
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
public class BeerDaoImpl extends AbstractMongoDao implements BeerDao 
{
    public final static String BEERS_COLLNAME = "beers";
    
    private static final Log log = LogFactory.getLog(BeerDaoImpl.class);
    
    @Override
    public List<Beer> findBeersByNameLike(String name) throws DaoException 
    {
        List<Beer> list = new ArrayList<Beer>();
        
        DB db = null;
        try
        {
            db = getDB();
            db.requestStart();
            DBCollection beers = db.getCollection(BEERS_COLLNAME);
            BasicDBObject query = new BasicDBObject();
            Pattern pattern = Pattern.compile(/*"^" + */name.trim(), Pattern.CASE_INSENSITIVE);
            query.put("name", pattern);
            DBCursor cur = beers.find(query).limit(10);
            
            while (cur.hasNext()){
                DBObject _b = cur.next();
                Beer l = createBeerFromDBObject(_b);
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
    public Beer findBeerByName(String name) throws DaoException 
    {
        DB db = null;
        try
        {
            db = getDB();
            db.requestStart();
            DBCollection beers = db.getCollection(BEERS_COLLNAME);
            BasicDBObject query = new BasicDBObject();
            query.put("name", name);
            DBCursor cur = beers.find(query);
            
            while (cur.hasNext()){
                DBObject _b = cur.next();
                Beer u = createBeerFromDBObject(_b);
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
    public Beer findById(String id) throws DaoException {
        DBObject obj = findById(id, BEERS_COLLNAME);
        if (obj == null) return null;
        Beer b = createBeerFromDBObject(obj);
        return b;
    }

    @Override
    public int saveBeer(Beer b) throws DaoException 
    {
        DB db = null;
        try
        {
            db = getDB();
            db.requestStart();
            DBCollection beers = db.getCollection(BEERS_COLLNAME);
            BasicDBObject beer = createDBObjectFromBeer(b);
            return beers.insert(beer).getN();
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
    
    
    protected static Beer createBeerFromDBObject(DBObject obj)
    {
        Beer b = new Beer();
        
        b.setId((ObjectId) obj.get("_id"));
        b.setDesc((String) obj.get("desc"));
        b.setCategory((String) obj.get("category"));
        b.setName((String) obj.get("name"));
        b.setSubcategory((String) obj.get("subcategory"));
        b.setPicture((byte[]) obj.get("picture"));
        
        return b;
    }
    
    // For first insert
    protected static BasicDBObject createDBObjectFromBeer (Beer b)
    {
        BasicDBObject _b = new BasicDBObject();
        _b.put("name", b.getName().trim());
        _b.put("desc", b.getDesc());
        _b.put("category", b.getCategory());
        _b.put("subcategory", b.getSubcategory());
        _b.put("picture", b.getPicture());
        
        return _b;
    }
}