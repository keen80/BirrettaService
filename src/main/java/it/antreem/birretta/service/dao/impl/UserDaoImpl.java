package it.antreem.birretta.service.dao.impl;

import com.mongodb.MongoException;
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
        try
        {
            return null;
        }
        catch(MongoException ex){
            log.error(ex.getLocalizedMessage(), ex);
            throw new DaoException(ex.getLocalizedMessage(), ex);
        }
    }   
}
