package it.antreem.birretta.service.dao.impl;

import com.mongodb.*;
import it.antreem.birretta.service.dao.DaoException;
import it.antreem.birretta.service.dao.LocTypeDao;
import it.antreem.birretta.service.model.LocType;
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
public class LocTypeDaoImpl extends AbstractMongoDao implements LocTypeDao 
{
    public final static String LOCTYPE_COLLNAME = "loctypes";
    
    private static final Log log = LogFactory.getLog(LocTypeDaoImpl.class);
    
    @Override
    public List<LocType> findLocTypesByCodLike(String cod) throws DaoException 
    {
        List<LocType> list = new ArrayList<LocType>();
        
        DB db = null;
        try
        {
            db = getDB();
            db.requestStart();
            DBCollection loctypes = db.getCollection(LOCTYPE_COLLNAME);
            BasicDBObject query = new BasicDBObject();
            Pattern pattern = Pattern.compile(/*"^" + */cod, Pattern.CASE_INSENSITIVE);
            query.put("cod", pattern);
            DBCursor cur = loctypes.find(query).limit(10);
            
            while (cur.hasNext()){
                DBObject _l = cur.next();
                LocType l = createLocTypeFromDBObject(_l);
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
    public LocType findLocTypeByCod(String cod) throws DaoException 
    {
        DB db = null;
        try
        {
            db = getDB();
            db.requestStart();
            DBCollection loctypes = db.getCollection(LOCTYPE_COLLNAME);
            BasicDBObject query = new BasicDBObject();
            query.put("cod", cod);
            DBCursor cur = loctypes.find(query);
            
            while (cur.hasNext()){
                DBObject _l = cur.next();
                LocType l = createLocTypeFromDBObject(_l);
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
    public LocType findById(String id) throws DaoException 
    {
        DBObject obj = findById(id, LOCTYPE_COLLNAME);
        if (obj == null) return null;
        LocType l = createLocTypeFromDBObject(obj);
        return l;
    }
    
    protected static LocType createLocTypeFromDBObject(DBObject obj)
    {
        LocType u = new LocType();

        u.setId((ObjectId) obj.get("_id"));
        u.setCod((String) obj.get("cod"));
        u.setDesc((String) obj.get("desc"));
        
        return u;
    }
    
    @Override
    public int saveLocType(LocType l) throws DaoException 
    {
        DB db = null;
        try
        {
            db = getDB();
            db.requestStart();
            DBCollection locTypes = db.getCollection(LOCTYPE_COLLNAME);
            BasicDBObject loc = createDBObjectFromLocType(l);
            return locTypes.insert(loc).getN();
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
    // For first insert
    protected static BasicDBObject createDBObjectFromLocType (LocType l)
    {
        BasicDBObject _l = new BasicDBObject();
        _l.put("cod", l.getCod().trim());
        _l.put("desc", l.getDesc());
        
        return _l;
    }
    
}
