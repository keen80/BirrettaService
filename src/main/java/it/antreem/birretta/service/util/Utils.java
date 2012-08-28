package it.antreem.birretta.service.util;

import it.antreem.birretta.service.dao.DaoFactory;
import it.antreem.birretta.service.dao.impl.AbstractMongoDao;
import it.antreem.birretta.service.dto.ErrorDTO;
import it.antreem.birretta.service.model.Badge;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Varie statiche ed eventuali.
 * 
 * @author alessio
 */
public class Utils 
{
    private static final Log log = LogFactory.getLog(Utils.class);
    
    public final static String SALT = "BirrettaServiceSaltXXX";
    
    public static String SHAsum(byte[] convertme)
    {
        MessageDigest md;
        
        try
        {
            md = MessageDigest.getInstance("SHA-1"); 
        }
        catch(NoSuchAlgorithmException ex)
        {
            log.error("Impossibile applicare algoritmo di hashing" + ex.getLocalizedMessage(), ex);
            return null;
        }
        
        return byteArray2Hex(md.digest(convertme));
    }

    private static String byteArray2Hex(final byte[] hash) 
    {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        return formatter.toString();
    }
    
    public static ErrorDTO createError (ErrorCodes err, Object... actionType)
    {
        ErrorDTO error = new ErrorDTO();
        ErrorDTO.Error _e = new ErrorDTO.Error();
        _e.setCode(err.getTitle());
        _e.setDesc(err.getMessage());
        _e.setTitle(err.getTitle());
        _e.setActionType((actionType != null && actionType.length > 0) ? actionType[0] : null);
        error.setError(_e);
        
        return error;
    }
    
    /*
     * Logica di assegnazione dei badge. Modificare eventualmente in futuro.
     */
    public static List<Badge> checkBadges(String username)
    {
        List<Badge> badges = new ArrayList<Badge>();
        
        if (DaoFactory.getInstance().getDrinkDao().countDrinksByUsername(username) >= 10
                && !DaoFactory.getInstance().getBadgeDao().hasBadge(username, Badge.COD_10_BEERS)){
            badges.add(DaoFactory.getInstance().getBadgeDao().findByCod(Badge.COD_10_BEERS));
        }
        
        return badges;
    }
}
