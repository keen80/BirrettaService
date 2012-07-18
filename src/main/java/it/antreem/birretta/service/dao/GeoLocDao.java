package it.antreem.birretta.service.dao;

/**
 *
 * @author alessio
 */
public interface GeoLocDao 
{
    public int updateLoc(String idUser, double lat, double lon) throws DaoException;
}
