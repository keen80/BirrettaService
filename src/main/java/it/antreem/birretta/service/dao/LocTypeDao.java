package it.antreem.birretta.service.dao;

import it.antreem.birretta.service.model.LocType;
import java.util.List;

/**
 *
 * @author alessio
 */
public interface LocTypeDao {
    public List<LocType> findLocTypesByCodLike(String cod) throws DaoException;
    public LocType findLocTypeByCod(String cod) throws DaoException;
    public LocType findById(String id) throws DaoException;
}
