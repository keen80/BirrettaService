package it.antreem.birretta.service.dao.impl;

import it.antreem.birretta.service.dao.NotificationDao;
import com.mongodb.*;
import it.antreem.birretta.service.dao.DaoException;
import it.antreem.birretta.service.model.Notification;
import java.util.ArrayList;
import java.util.Date;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bson.types.ObjectId;

/**
 *
 * @author gmorlini
 */
public class NotificationDaoImpl extends AbstractMongoDao implements NotificationDao{
    public final static String ACTIVITY_COLLNAME = "notifications";
     
     private static final Log log = LogFactory.getLog(NotificationDaoImpl.class);
     
    @Override
    public ArrayList<Notification> findByUser(String user) throws DaoException {
        DB db = null;
        ArrayList<Notification> list = new ArrayList<Notification>();
        try
        {
            db = getDB();
            db.requestStart();
            DBCollection beers = db.getCollection(ACTIVITY_COLLNAME);
            BasicDBObject query = new BasicDBObject();
            query.put("id_user", user);
            DBCursor cur = beers.find(query);
            
            while (cur.hasNext()){
                DBObject _b = cur.next();
                Notification act = createNotificationFromDBObject(_b);
                list.add(act);
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
    public Notification findById(String id) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int saveNotification(Notification n) throws DaoException {
         DB db = null;
        try
        {
            db = getDB();
            db.requestStart();
            DBCollection activities = db.getCollection(ACTIVITY_COLLNAME);
            BasicDBObject activity = createDBObjectFromNotification(n);
            return activities.insert(activity).getN();
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

    private BasicDBObject createDBObjectFromNotification(Notification n) {
        BasicDBObject _n = new BasicDBObject();
        _n.put("id_notification", n.getIdNotification());
        _n.put("id_user", n.getIdUser());
        _n.put("displayName", n.getDisplayName());
        _n.put("id_beer", n.getIdBeer());
        _n.put("beerName", n.getBeerName());
        _n.put("id_friend", n.getIdFriend());
        _n.put("friendName", n.getFriendName());
        _n.put("id_place", n.getIdPlace());
        _n.put("placeName", n.getPlaceName());
        //solo di notification
        _n.put("description", n.getDescription());  
        _n.put("type", n.getType());
        _n.put("read", n.getRead());
        _n.put("insertedOn", n.getInsertedOn());
        /*
        _n.put("status", n.getStatus());
        _n.put("like", n.getLike());
        _n.put("date", n.getDate());
        _n.put("idActivity", n.getIdActivity());
        _n.put("avatar", n.getAvatar());
        * */
        
        return _n;
    }

    private Notification createNotificationFromDBObject(DBObject obj) {
        Notification a = new Notification();
        a.setId((ObjectId) obj.get("_id"));
        //comuni ad Activity
        a.setIdNotification((String)obj.get("id_notification"));
        a.setIdUser((String) obj.get("id_User"));
        a.setDisplayName((String) obj.get("displayName"));
        a.setIdBeer((String) obj.get("id_Beer"));      
        a.setBeerName((String) obj.get("beerName"));
        a.setIdPlace((String) obj.get("id_Place"));
        a.setPlaceName((String) obj.get("placeName"));
        a.setIdFriend((String) obj.get("id_Friend"));
        a.setFriendName((String) obj.get("friendName"));
        //solo di notification
        a.setDescription((String)obj.get("description"));
        a.setType((String)obj.get("type"));
        a.setRead(obj.get("isRead")!=null?(Boolean)obj.get("isRead"): false );
        String insertedOn=(String) obj.get("insertedOn");
        if(insertedOn!=null && !"".equals(insertedOn))
            a.setInsertedOn(new Date(insertedOn));
        /*Activity
        a.setType((Integer) obj.get("Type"));
        a.setStatus((Integer) obj.get("Status"));
        a.setLike((Integer) obj.get("Like"));
        a.setDate(new Date((String) obj.get("Date")));
                a.setAvatar((String) obj.get("Avatar"));
                * /
                */
        return a;
    }
     
}
