package it.antreem.birretta.service.dao.impl;

import com.mongodb.*;
import it.antreem.birretta.service.dao.DaoException;
import it.antreem.birretta.service.dao.LocTypeDao;
import it.antreem.birretta.service.model.LocType;
import java.util.ArrayList;
import java.util.List;
import org.bson.types.ObjectId;
import java.util.regex.Pattern;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author alessio
 */
public class LocTypeDaoImpl extends AbstractMongoDao implements LocTypeDao 
{
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
            DBCollection users = db.getCollection("loctypes");
            BasicDBObject query = new BasicDBObject();
            Pattern pattern = Pattern.compile(/*"^" + */cod, Pattern.CASE_INSENSITIVE);
            query.put("cod", pattern);
            DBCursor cur = users.find(query).limit(10);
            
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
    
    protected static LocType createLocTypeFromDBObject(DBObject obj)
    {
        LocType u = new LocType();

        u.setId((ObjectId) obj.get("_id"));
        u.setCod((String) obj.get("cod"));
        u.setDesc((String) obj.get("desc"));
        
        return u;
    }
}
