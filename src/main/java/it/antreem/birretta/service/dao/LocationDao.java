package it.antreem.birretta.service.dao;

import it.antreem.birretta.service.model.Location;
import java.util.List;

/**
 *
 * @author alessio
 */
public interface LocationDao 
{
    public List<Location> findLocationsByNameLike(String name) throws DaoException;
    public Location findLocationByName(String name) throws DaoException;
    public Location findById(String id) throws DaoException;
    public List<Location> findLocationNear(Double lat, Double lon, Double radius) throws DaoException;
    public int saveLocation(Location l) throws DaoException;

    public Object findByIdLocation(String name)throws DaoException;

    public int updateLocation(Location l)throws DaoException;
}
