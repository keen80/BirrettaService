package it.antreem.birretta.service.dao;

import it.antreem.birretta.service.model.Activity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gmorlini
 */
public interface ActivityDao 
{
    public ArrayList<Activity> findByUser(String user) throws DaoException;
    public Activity findById(String id) throws DaoException;
    public int saveActivity(Activity b) throws DaoException;
}
