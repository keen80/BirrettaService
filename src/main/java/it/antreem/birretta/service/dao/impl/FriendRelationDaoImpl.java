package it.antreem.birretta.service.dao.impl;

import com.mongodb.*;
import it.antreem.birretta.service.dao.DaoException;
import it.antreem.birretta.service.dao.FriendDao;
import it.antreem.birretta.service.dao.FriendRelationDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import it.antreem.birretta.service.model.FriendsRelation;
import java.util.ArrayList;
import org.bson.types.ObjectId;

/**
 *
 * @author alessio
 */
public class FriendRelationDaoImpl extends AbstractMongoDao implements FriendRelationDao
{
    public final static String FRIENDS_RELATION_COLLNAME = "friendsrelations";
    
    private static final Log log = LogFactory.getLog(FriendRelationDaoImpl.class);
    
    @Override
    public int saveFriendsRelation(FriendsRelation fr) throws DaoException 
    {
        DB db = null;
        try
        {
            db = getDB();
            db.requestStart();
            DBCollection frr = db.getCollection(FRIENDS_RELATION_COLLNAME);
            BasicDBObject f = createDBObjectFromFriendsRelation(fr);
            return frr.insert(f).getN();
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
    public ArrayList<FriendsRelation> getMyFriends(String id_user, int maxElement) throws DaoException 
    {
        DB db = null;
        ArrayList<FriendsRelation> list= new ArrayList<FriendsRelation>();      
        try
        {
            db = getDB();
            db.requestStart();
            DBCollection friendsRelations = db.getCollection(FRIENDS_RELATION_COLLNAME);
            BasicDBObject query = new BasicDBObject();
            query.put("idUser1", id_user);
            DBCursor cur = friendsRelations.find(query);
           if(maxElement>0)
                   cur.limit(maxElement);
           while (cur.hasNext()){
                DBObject _f = cur.next();
                FriendsRelation f = createFriendsRelationFromDBObject(_f);
               list.add(f);
            }
            
           return list;
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
    public int deleteFriendship(String id1, String id2) throws DaoException 
    {
        DB db = null;
        try
        {
            db = getDB();
            db.requestStart();
            DBCollection friends = db.getCollection(FRIENDS_RELATION_COLLNAME);
            BasicDBObject req = new BasicDBObject();
            req.put("isUser1", id1);
            req.put("isUser2", id2);
            int a = friends.remove(req).getN();
            req = new BasicDBObject();
            req.put("isUser1", id2);
            req.put("isUser2", id1);
            int b = friends.remove(req).getN();
            return a+b;
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
    public boolean areFriends(String id1, String id2) throws DaoException 
    {
        DB db = null;
        try
        {
            db = getDB();
            db.requestStart();
            DBCollection users = db.getCollection(FRIENDS_RELATION_COLLNAME);
            BasicDBObject query = new BasicDBObject();
            query.put("idUser1", id1);
            query.put("idUser2", id2);
            DBCursor cur = users.find(query);
            if (cur.hasNext()) return true;
            
            query = new BasicDBObject();
            query.put("idUser1", id2);
            query.put("idUser2", id1);
            cur = users.find(query);
            if (cur.hasNext()) return true;
            
            return false;
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
    public boolean updateFriendsRelation(FriendsRelation fr)  throws DaoException 
    {
    DB db = null;
        try
        {
            db = getDB();
            db.requestStart();
            DBCollection friendsrelations = db.getCollection(FRIENDS_RELATION_COLLNAME);
           DBObject obj= createDBObjectFromFriendsRelation(fr);
            WriteResult wr = friendsrelations.update(new BasicDBObject().append("_id", fr.getId()),obj);
           if(wr.getN()<1)
           {
               log.error("impossibile fare update");
               return false;
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
        return true;
    }
    @Override
    public FriendsRelation getFriendsRelation(String id1, String id2) throws DaoException 
    {
        DB db = null;
        try
        {
            db = getDB();
            db.requestStart();
            DBCollection friendsrelations = db.getCollection(FRIENDS_RELATION_COLLNAME);
            BasicDBObject query = new BasicDBObject();
            query.put("idUser1", id1);
            query.put("idUser2", id2);
            DBCursor cur = friendsrelations.find(query);
            
            if(cur.hasNext()){
                DBObject _d = cur.next();
                FriendsRelation d = createFriendsRelationFromDBObject(_d);
                return d;
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
    
    // For first insert
    protected static BasicDBObject createDBObjectFromFriendsRelation (FriendsRelation fr)
    {
        BasicDBObject _d = new BasicDBObject();
        _d.put("idUser1",fr.getIdUser1());
        _d.put("idUser2", fr.getIdUser2());
        _d.put("friend", fr.isFriend());
        return _d;
    }

    private FriendsRelation createFriendsRelationFromDBObject(DBObject obj) {
        FriendsRelation f = new FriendsRelation();
        f.setId((ObjectId)obj.get("_id"));
        f.setIdUser1((String)obj.get("idUser1"));
        f.setIdUser2((String)obj.get("idUser2"));
        f.setFriend((obj.get("friend")!=null?(Boolean)obj.get("friend"):false));
        return f;
    }
    
}
