package it.antreem.birretta.service.dao;

import it.antreem.birretta.service.dao.impl.UserDaoImpl;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author alessio
 */
public final class DaoFactory 
{
    private static final Log log = LogFactory.getLog(DaoFactory.class);
    
    private static <T> T instanciateDao (Class clazz)
    {
        try 
        {
            return (T) clazz.newInstance();
        } catch (InstantiationException ex) {
            log.error(ex.getLocalizedMessage(), ex);
            throw new DaoException("Errore nell'istanziazione del Dao: " + clazz.getCanonicalName(), ex);
        } catch (IllegalAccessException ex) {
            log.error(ex.getLocalizedMessage(), ex);
            throw new DaoException("Errore nell'istanziazione del Dao: " + clazz.getCanonicalName(), ex);
        }
        
    }
    
    public UserDao getUserDao() throws DaoException {
        return instanciateDao(UserDaoImpl.class);
    }
    
}
