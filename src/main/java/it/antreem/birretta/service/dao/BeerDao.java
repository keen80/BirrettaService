package it.antreem.birretta.service.dao;

import it.antreem.birretta.service.model.Beer;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alessio
 */
public interface BeerDao 
{
    public List<Beer> findBeersByNameLike(String name) throws DaoException;
    public Beer findBeerByName(String name) throws DaoException;
    public Beer findById(String id) throws DaoException;
    public int saveBeer(Beer b) throws DaoException;
    public ArrayList<Beer>  listBeer(int maxElement) throws DaoException ;
}
