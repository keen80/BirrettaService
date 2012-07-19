package it.antreem.birretta.service.dao.impl;

import com.mongodb.*;
import it.antreem.birretta.service.dao.DaoException;
import it.antreem.birretta.service.dao.UserDao;
import it.antreem.birretta.service.model.User;
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
public class UserDaoImpl extends AbstractMongoDao implements UserDao 
{
    public final static String USERS_COLLNAME = "users";
    
    private static final Log log = LogFactory.getLog(UserDaoImpl.class);
    
    @Override
    public User findUserByUsername(String username) throws DaoException 
    {
        DB db = null;
        try
        {
            db = getDB();
            db.requestStart();
            DBCollection users = db.getCollection(USERS_COLLNAME);
            BasicDBObject query = new BasicDBObject();
            query.put("username", username);
            DBCursor cur = users.find(query);
            
            while (cur.hasNext()){
                DBObject _u = cur.next();
                User u = createUserFromDBObject(_u);
                assert u.getUsername().equals(username);
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
    public List<User> findUsers(String username, String first, String last) throws DaoException 
    {
        List<User> list = new ArrayList<User>();
        
        DB db = null;
        try
        {
            db = getDB();
            db.requestStart();
            DBCollection users = db.getCollection(USERS_COLLNAME);
            BasicDBObject query = new BasicDBObject();
            
            if (username != null){
                Pattern pattern = Pattern.compile(/*"^" + */username.trim(), Pattern.CASE_INSENSITIVE);
                query.put("username", pattern);
            }
            
            if (first != null){
                Pattern pattern = Pattern.compile(/*"^" + */first.trim(), Pattern.CASE_INSENSITIVE);
                query.put("first_name", pattern);
            }
            
            if (last != null){
                Pattern pattern = Pattern.compile(/*"^" + */last.trim(), Pattern.CASE_INSENSITIVE);
                query.put("last_name", pattern);
            }
            
            DBCursor cur = users.find(query).limit(10);
            
            while (cur.hasNext()){
                DBObject _l = cur.next();
                User u = createUserFromDBObject(_l);
                list.add(u);
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
    public int saveUser(User u) throws DaoException 
    {
        DB db = null;
        try
        {
            db = getDB();
            db.requestStart();
            DBCollection users = db.getCollection(USERS_COLLNAME);
            BasicDBObject user = createDBObjectFromUser(u);
            return users.insert(user).getN();
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
    public User findById(String id) throws DaoException 
    {
        DBObject obj = findById(id, USERS_COLLNAME);
        if (obj == null) return null;
        User u = createUserFromDBObject(obj);
        return u;
    }
    
    protected static User createUserFromDBObject(DBObject obj)
    {
        User u = new User();

        u.setId((ObjectId) obj.get("_id"));
        u.setUsername((String) obj.get("username"));
        u.setFirstName((String) obj.get("first_name"));
        u.setLastName((String) obj.get("last_name"));
        u.setSex((String) obj.get("sex"));
        u.setAge((Integer) obj.get("age"));
        u.setEmail((String) obj.get("email"));
        u.setPwdHash((String) obj.get("pwdHash"));
        
        return u;
    }
    
    // For first insert
    protected static BasicDBObject createDBObjectFromUser (User u)
    {
        BasicDBObject _u = new BasicDBObject();
        _u.put("username", u.getUsername());
        _u.put("age", u.getAge());
        _u.put("email", u.getEmail());
        _u.put("first_name", u.getFirstName().trim());
        _u.put("last_name", u.getLastName().trim());
        _u.put("pwdHash", u.getPwdHash());
        _u.put("sex", u.getSex());
        return _u;
    }

}
