package it.antreem.birretta.service.dao;

import it.antreem.birretta.service.dao.impl.*;
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
    
    public LocationDao getLocationDao() throws DaoException {
        return instanciateDao(LocationDaoImpl.class);
    }
    
    public BeerDao getBeerDao() throws DaoException {
        return instanciateDao(BeerDaoImpl.class);
    }
    
    public DrinkDao getDrinkDao() throws DaoException {
        return instanciateDao(DrinkDaoImpl.class);
    }
    
    public FriendDao getFriendDao() throws DaoException {
        return instanciateDao(FriendDaoImpl.class);
    }
    
    public FriendReqDao getFriendReqDao() throws DaoException {
        return instanciateDao(FriendReqDaoImpl.class);
    }
    
    public BadgeDao getBadgeDao() throws DaoException {
        return instanciateDao(BadgeDaoImpl.class);
    }
}
