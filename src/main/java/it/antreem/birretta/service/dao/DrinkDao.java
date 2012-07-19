package it.antreem.birretta.service.dao;

import it.antreem.birretta.service.model.Drink;
import java.util.List;

/**
 *
 * @author alessio
 */
public interface DrinkDao 
{
    public Drink findLastDrinkByUsername(String username) throws DaoException;
    public List<Drink> findDrinksByUsername(String username, Integer limit) throws DaoException;
    public List<Drink> findRecentDrinks(String username, Integer minutesAgo) throws DaoException;
    public int saveDrink(Drink d) throws DaoException;
}
