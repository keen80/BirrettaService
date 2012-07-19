package it.antreem.birretta.service.dao;

import it.antreem.birretta.service.dao.impl.GeoLocDaoImpl;
import it.antreem.birretta.service.dao.impl.LocTypeDaoImpl;
import it.antreem.birretta.service.dao.impl.SessionDaoImpl;
import it.antreem.birretta.service.dao.impl.UserDaoImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author alessio
 */
public final class DaoFactory 
{
    private static final Log log = LogFactory.getLog(DaoFactory.class);
    
    private final static DaoFactory factory = new DaoFactory();
    
    public static DaoFactory getInstance() {
        return factory;
    }
    
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
    
    public SessionDao getSessionDao() throws DaoException {
        return instanciateDao(SessionDaoImpl.class);
    }
    
    public GeoLocDao getGeoLocDao() throws DaoException {
        return instanciateDao(GeoLocDaoImpl.class);
    }
    
    public LocTypeDao getLocTypeDao() throws DaoException {
        return instanciateDao(LocTypeDaoImpl.class);
    }
}
