package it.antreem.birretta.service.util;

import it.antreem.birretta.service.dao.impl.AbstractMongoDao;
import it.antreem.birretta.service.dto.ErrorDTO;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
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
    
    public static ErrorDTO createError (String code, String title, String desc, Object actionType)
    {
        ErrorDTO error = new ErrorDTO();
        ErrorDTO.Error _e = new ErrorDTO.Error();
        _e.setCode(code);
        _e.setDesc(desc);
        _e.setTitle(title);
        _e.setActionType(actionType);
        error.setError(_e);
        
        return error;
    }
}
