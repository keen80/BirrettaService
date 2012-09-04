package it.antreem.birretta.service.dao;

import it.antreem.birretta.service.model.LocationCategory;
import java.util.List;

/**
 *
 * @author gmorlini
 */
public interface LocationCategoryDao {
    //alcuni id di fourSquare
    public String ID_PUB="4bf58dd8d48988d11b941735";
    public String ID_RESTAURANT="4bf58dd8d48988d1c4941735";
    public String ID_OTHER_OUTDOORS="4bf58dd8d48988d162941735";
    public String ID_PIZZA="4bf58dd8d48988d1ca941735";
    public String ID_HOME="4bf58dd8d48988d103941735";
    public List<LocationCategory> findLocationCategoryByNameLike(String cod) throws DaoException;
    public LocationCategory findLocationCategoryByIdCategory(String cod) throws DaoException;
    public LocationCategory findById(String id) throws DaoException;
    public int saveLocationCategory(LocationCategory l) throws DaoException;
    public List<LocationCategory> findAll() throws DaoException;
}
