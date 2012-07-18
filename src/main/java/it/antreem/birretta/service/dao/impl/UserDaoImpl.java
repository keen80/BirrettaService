package it.antreem.birretta.service.dao.impl;

import com.mongodb.*;
import it.antreem.birretta.service.dao.DaoException;
import it.antreem.birretta.service.dao.UserDao;
import it.antreem.birretta.service.model.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author alessio
 */
public class UserDaoImpl extends AbstractMongoDao implements UserDao 
{
    private static final Log log = LogFactory.getLog(UserDaoImpl.class);
    
    @Override
    public User findUserByUsername(String username) throws DaoException 
    {
        DB db = null;
        try
        {
            db = getDB();
            db.requestStart();
            DBCollection users = db.getCollection("users");
            BasicDBObject query = new BasicDBObject();
            query.put("username", username);
            DBCursor cur = users.find(query);
            
            while (cur.hasNext()){
                DBObject _u = cur.next();
                User u = new User();
                
                u.setUsername((String) _u.get("username"));
                assert u.getUsername().equals(username);
                u.setFirstName((String) _u.get("first_name"));
                u.setLastName((String) _u.get("last_name"));
                u.setSex((String) _u.get("sex"));
                u.setAge((Integer) _u.get("age"));
                u.setEmail((String) _u.get("email"));
                u.setPwdHash((String) _u.get("pwdHash"));
                
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
    public int saveUser(User u) throws DaoException 
    {
        DB db = null;
        try
        {
            db = getDB();
            db.requestStart();
            DBCollection users = db.getCollection("users");
            BasicDBObject user = createUserDBObject(u);
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
    
    protected static BasicDBObject createUserDBObject (User u){
        BasicDBObject _u = new BasicDBObject();
        _u.put("username", u.getUsername());
        _u.put("age", u.getAge());
        _u.put("email", u.getEmail());
        _u.put("first_name", u.getFirstName());
        _u.put("last_name", u.getLastName());
        _u.put("pwdHash", u.getPwdHash());
        _u.put("sex", u.getSex());
        return _u;
    }
}
