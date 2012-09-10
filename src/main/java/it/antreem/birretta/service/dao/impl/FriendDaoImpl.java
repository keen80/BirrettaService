package it.antreem.birretta.service.dao.impl;

import com.mongodb.*;
import it.antreem.birretta.service.dao.DaoException;
import it.antreem.birretta.service.dao.DaoFactory;
import it.antreem.birretta.service.dao.FriendDao;
import it.antreem.birretta.service.model.Friend;
import it.antreem.birretta.service.model.FriendsRelation;
import it.antreem.birretta.service.model.User;
import it.antreem.birretta.service.util.DateAdapter;
import it.antreem.birretta.service.util.FriendStatusCodes;
import java.util.ArrayList;
import java.util.Date;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author alessio
 */
public class FriendDaoImpl extends AbstractMongoDao implements FriendDao
{
    public final static String FRIENDS_RELATION_COLLNAME = "users";
    
    private static final Log log = LogFactory.getLog(FriendDaoImpl.class);
    
    @Override
    public int saveFriend(String id1, String id2) throws DaoException 
    {
        DB db = null;
        try
        {
            db = getDB();
            db.requestStart();
            DBCollection friends = db.getCollection(FRIENDS_RELATION_COLLNAME);
            BasicDBObject req = new BasicDBObject();
            req.put("id_1", id1);
            req.put("id_2", id2);
            return friends.insert(req).getN();
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
    public int deleteFriend(String id1, String id2) throws DaoException 
    {
        DB db = null;
        try
        {
            db = getDB();
            db.requestStart();
            DBCollection friends = db.getCollection(FRIENDS_RELATION_COLLNAME);
            BasicDBObject req = new BasicDBObject();
            req.put("id_1", id1);
            req.put("id_2", id2);
            int a = friends.remove(req).getN();
            req = new BasicDBObject();
            req.put("id_1", id2);
            req.put("id_2", id1);
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
    public ArrayList<Friend> getAllFriends(int maxElement) throws DaoException 
    {
        ArrayList<Friend> list=new ArrayList<Friend>();
        DB db = null;
        try
        {
            db = getDB();
            db.requestStart();
            DBCollection users = db.getCollection(FRIENDS_RELATION_COLLNAME);
      /*      BasicDBObject param = new BasicDBObject();
            query.put("id_1", maxElement);*/
            DBCursor cur = users.find();
       //     DBCursor curOrdered= cur.sort(param);            
            if(maxElement>0)
                   cur.limit(maxElement);

            while (cur.hasNext()){
                DBObject _f = cur.next();
                Friend f = createFriendFromDBObject(_f);
               list.add(f);
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
    public ArrayList<Friend> getAllMyFriends(int maxElemet, String idUser) {
        ArrayList<Friend> list= new ArrayList<Friend>();
        ArrayList<FriendsRelation> myFriends = DaoFactory.getInstance().getFriendRelationDao().getMyFriends(idUser, maxElemet);
        for(FriendsRelation friendRelation: myFriends)
        {
            User user = DaoFactory.getInstance().getUserDao().findUserByIdUser(friendRelation.getIdUser2());
            if(user==null)
            {
                log.error("FriendDaoImpl - getAllMyFriends - utente non trovato: " +friendRelation.getIdUser2());
            }
            Friend friend = createFriendFromUserAndRelation(user,friendRelation);
            list.add(friend);
        }
        return list;
    }
    private Friend createFriendFromDBObject(DBObject obj) {
        Friend f=new Friend();
         f.setIdUser((String) obj.get("idUser"));
         //friend non è un un oggetto mongodb
   //     f.setId((ObjectId) obj.get("_id"));
        f.setUsername((String) obj.get("username"));
        f.setDisplayName((String)obj.get("displayName"));
        f.setFirstName((String) obj.get("firstName"));
        f.setLastName((String) obj.get("lastName"));
        f.setDescription((String)obj.get("description"));
        f.setEmail((String)obj.get("email"));
        f.setGender((Integer) obj.get("gender"));
        f.setNationality((String)obj.get("nationality"));
        f.setIdFacebook((String)obj.get("idFacebook"));
        Object birthDate= obj.get("birthDate");
        if(birthDate!=null)
        {
            DateAdapter da=new DateAdapter();
            da.setDate((Date) birthDate);
             f.setBirthDate(da.toString());
        }
        f.setAvatar((String)obj.get("avatar"));
        
      
        f.setRole((Integer) obj.get("role"));
        f.setStatus((Integer) obj.get("status"));
      
        
        Object activatedOn=obj.get("activatedOn");
        if(activatedOn!=null)
        {
            DateAdapter da=new DateAdapter();
            da.setDate((Date) activatedOn);
                f.setActivatedOn(da.toString());
        }
        
        Object lastLoginOn=obj.get("lastLoginOn");
        if(lastLoginOn!=null)
        {
            DateAdapter da=new DateAdapter();
            da.setDate((Date) lastLoginOn);
                f.setActivatedOn(da.toString());
        }
        
//        f.setBadges((String)obj.get("badges"));
        f.setFavorites((String) obj.get("favorites"));
        f.setLiked((String)obj.get("liked"));
        f.setCounterCheckIns((Integer)obj.get("counterCheckIns"));
        f.setCounterFriends((Integer)obj.get("counterFriends"));
        f.setCounterBadges((Integer)obj.get("counterBadges"));
        
        /** 
         * campi user non necessari in friend
         * 
          f.setShareFacebook((obj.get("shareFacebook")!=null ? (Boolean) obj.get("shareFacebook") : false));
        f.setShareTwitter((obj.get("shareFacebook")!=null ? (Boolean)obj.get("shareTwitter"): false));
        f.setEnableNotification(obj.get("enableNotification")!=null?(Boolean)obj.get("enableNotification"): false );
          f.setPwdHash((String) obj.get("pwdHash"));
          **/
        return f;  
    }

    private Friend createFriendFromUserAndRelation(User user, FriendsRelation friendRelation) {
        Friend f = new Friend();
        f.setIdUser(user.getIdUser());
        //friend non è un un oggetto mongodb
        //     f.setId((ObjectId) obj.get_id());
        f.setUsername(user.getUsername());
        f.setIdFacebook(user.getIdFacebook());
        f.setDisplayName(user.getDisplayName());
        f.setFirstName(user.getFirstName());
        f.setLastName(user.getLastName());
        f.setDescription(user.getDescription());
        f.setEmail(user.getEmail());
        f.setGender(user.getGender());
        f.setNationality(user.getNationality());
        DateAdapter da = new DateAdapter();
        da.setDate(user.getBirthDate());
        f.setBirthDate(da.toString());
        f.setAvatar(user.getAvatar());
        f.setRole(user.getRole());
        
        //impostazione status:
        int status = 0;
        if (friendRelation.isFriend()) //richiesta accettata
        {
            status = FriendStatusCodes.FRIEND.getStatus();
        } else //il mio amico deve ancora accettare
        {
            status = FriendStatusCodes.PENDING.getStatus();
        }

        f.setStatus(status);
        DateAdapter da1 = new DateAdapter();
        da1.setDate(user.getActivatedOn());
        f.setActivatedOn(da1.toString());
        DateAdapter da2 = new DateAdapter();
        da2.setDate(user.getLastLoginOn());
        f.setActivatedOn(da2.toString());
        //rimosso lista badge richiesta a parte
      //  f.setBadges(user.getBadges());
        f.setFavorites(user.getFavorites());
        f.setLiked(user.getLiked());
        f.setCounterCheckIns(user.getCounterCheckIns());
        f.setCounterFriends(user.getCounterFriends());
        f.setCounterBadges(user.getCounterBadges());

        return f;
    }
    
}
