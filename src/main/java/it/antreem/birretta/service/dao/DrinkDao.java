package it.antreem.birretta.service.dao;

import it.antreem.birretta.service.model.Drink;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author alessio
 */
public interface DrinkDao 
{
    public Drink findLastDrinkByUsername(String username) throws DaoException;

    public List<Drink> findDrinksByIdUser(String idUser, Integer limit) throws DaoException;

    public int countDrinksByUsername(String username) throws DaoException;

    public List<Drink> findRecentDrinks(String username, Integer minutesAgo) throws DaoException;

    public int saveDrink(Drink d) throws DaoException;

    public ArrayList<Drink> getDrinksList(Integer maxElement) throws DaoException;

    public int countDrinksByPlace(String idLocation) throws DaoException;
    
    public ArrayList<Drink> listDrinksByPlace(String idLocation,int limit) throws DaoException;

    public ArrayList<Drink> findDrinksByUsernameAndPlace(ArrayList<String> idUsers, String idPlace, int maxElemet);
     public List<Drink> findDrinksInInterval(String idUser, Date after,Date before) throws DaoException ;
}
