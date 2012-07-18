package it.antreem.birretta.service.test.generic;

import it.antreem.birretta.service.util.Utils;
import java.security.NoSuchAlgorithmException;
import org.apache.log4j.Logger;
import org.junit.Test;

/**
 *
 * @author alessio
 */
public class SHA1Test 
{
    private final static Logger log = Logger.getLogger(SHA1Test.class);
    
    @Test
    public void testSha1() throws NoSuchAlgorithmException
    {
        String password =  "myPwd";
        log.debug(Utils.SHAsum(password.getBytes()));
    }
}
