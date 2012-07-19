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
    public Location findById(String id) throws DaoException;
    public int saveLocation(Location l) throws DaoException;
}
