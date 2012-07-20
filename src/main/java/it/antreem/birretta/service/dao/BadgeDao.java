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
    public Badge findByCod(String cod) throws DaoException;
    public List<Badge> findUserBadges(String username) throws DaoException;
    public boolean hasBadge(String username, String badgeCode) throws DaoException;
    public int saveUserBadges(String username, List<Badge> badges) throws DaoException;
}
