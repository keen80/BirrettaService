package it.antreem.birretta.service.dao;

import it.antreem.birretta.service.model.Badge;
import java.util.List;

/**
 *
 * @author alessio
 */
public interface BadgeDao 
{
    public Badge findById(String id) throws DaoException;
    public List<Badge> findByCod(int cod) throws DaoException;
    public Badge findByIdBadge(int idBadge) throws DaoException;
    public int saveBadge(Badge b) throws DaoException ;
}
