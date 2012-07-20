package it.antreem.birretta.service.dao.impl;

import com.mongodb.*;
import it.antreem.birretta.service.dao.BadgeDao;
import it.antreem.birretta.service.dao.DaoException;
import it.antreem.birretta.service.dao.DaoFactory;
import it.antreem.birretta.service.model.Badge;
import it.antreem.birretta.service.model.User;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bson.types.ObjectId;

/**
 *
 * @author alessio
 */
public class BadgeDaoImpl extends AbstractMongoDao implements BadgeDao 
{
    public final static String BADGES_COLLNAME = "badges";
    public final static String ACHIEVEMENTS_COLLNAME = "achievements";
    
    
    private static final Log log = LogFactory.getLog(DrinkDaoImpl.class);
    
    @Override
    public boolean hasBadge(String username, String badgeCode) throws DaoException 
    {
        User u = DaoFactory.getInstance().getUserDao().findUserByUsername(username);
        Badge b = findByCod(badgeCode);
        
        DB db = null;
        try
        {
            db = getDB();
            db.requestStart();
            DBCollection as = db.getCollection(ACHIEVEMENTS_COLLNAME);
            BasicDBObject query = new BasicDBObject();
            query.put("id_user", u.getIdUser());
            query.put("id_badge", b.getIdBadge());
            
            return as.find(query).count() > 0;
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
    public Badge findById(String id) throws DaoException 
    {
        DBObject obj = findById(id, BADGES_COLLNAME);
        if (obj == null) return null;
        Badge b = createBadgeFromDBObject(obj);
        return b;
    }
    
    @Override
    public Badge findByCod(String cod) throws DaoException 
    {
        DB db = null;
        try
        {
            db = getDB();
            db.requestStart();
            DBCollection badges = db.getCollection(BADGES_COLLNAME);
            BasicDBObject query = new BasicDBObject();
            query.put("cod", cod);
            DBCursor cur = badges.find(query);
            
            while (cur.hasNext()){
                DBObject _b = cur.next();
                Badge b = createBadgeFromDBObject(_b);
                assert b.getCod().equals(cod);
                return b;
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
    public List<Badge> findUserBadges(String username) throws DaoException 
    {
        List<Badge> list = new ArrayList<Badge>();
        
        User u = DaoFactory.getInstance().getUserDao().findUserByUsername(username);
        if (u == null) return list;
        String idUser = u.getIdUser();
        
        DB db = null;
        try
        {
            db = getDB();
            db.requestStart();
            DBCollection as = db.getCollection(ACHIEVEMENTS_COLLNAME);
            BasicDBObject query = new BasicDBObject();
            query.put("id_user", idUser);
            DBCursor cur = as.find(query).sort(new BasicDBObject("timestamp", -1));
            
            while (cur.hasNext()){
                DBObject _d = cur.next();
                String idBadge = (String) _d.get("id_badge");
                Badge b = findById(idBadge);
                list.add(b);
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
    public int saveUserBadges(String username, List<Badge> badges) throws DaoException 
    {
        if (badges == null || badges.isEmpty()) return 0;
        
        User u = DaoFactory.getInstance().getUserDao().findUserByUsername(username);
        if (u == null) return 0;
        String idUser = u.getIdUser();
        
        DB db = null;
        try
        {
            db = getDB();
            db.requestStart();
            DBCollection as = db.getCollection(ACHIEVEMENTS_COLLNAME);
            int count = 0;
            
            for (Badge b : badges)
            {
                BasicDBObject a = new BasicDBObject();
                a.put("id_user", idUser);
                a.put("id_badge", b.getIdBadge());
                a.put("timestamp", new Date());
                count += as.insert(a).getN();
            }
            
            return count;
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

    
    protected static Badge createBadgeFromDBObject(DBObject obj)
    {
        Badge b = new Badge();

        b.setId((ObjectId) obj.get("_id"));
        b.setCod((String) obj.get("cod"));
        b.setMessage((String) obj.get("message"));
        
        return b;
    }
}
