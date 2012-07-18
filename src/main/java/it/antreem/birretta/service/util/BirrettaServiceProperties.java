package it.antreem.birretta.service.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Configurazione delle properties statiche.
 */
public class BirrettaServiceProperties 
{
    // Properties caricate solo in fase di startup
    public static final String MONGODB_HOST;
    public static final String MONGODB_PORT;
    
    /**
     * Il logger per questa classe.
     */
    private static final Log LOG = LogFactory.getLog(BirrettaServiceProperties.class);

    static 
    {
        Properties prop;
        LOG.debug("Caricamento delle properties statiche...");
        try 
        {
            prop = loadPropertiesFromFile("birrettaservice.properties");
        } 
        catch (IOException e) {
            LOG.error(e.getMessage(), e);
            throw new ExceptionInInitializerError(e);
        }
        if (LOG.isDebugEnabled()) 
        {
            LOG.debug("Properties statiche caricate: " + prop);
        }

        
        MONGODB_HOST = prop.getProperty("mongodb.host");
        if (MONGODB_HOST == null) throw new ExceptionInInitializerError("MONGODB_HOST == null");
        MONGODB_PORT = prop.getProperty("mongodb.port");
        if (MONGODB_PORT == null) throw new ExceptionInInitializerError("MONGODB_PORT == null");

    }

    private BirrettaServiceProperties() {
    }

    /**
     * Caricamento delle properties.
     *
     * @param properties
     */
    private static Properties loadPropertiesFromFile(final String fileName) 
            throws IOException 
    {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream stream = loader.getResourceAsStream(fileName);

        if (stream == null) {
            throw new IOException("File " + fileName + " non trovato");
        }
        
        try 
        {
            Properties prop = new Properties();
            prop.load(stream);
            return prop;
        } 
        finally 
        {
            try 
            {
                stream.close();
            } 
            catch (IOException e) 
            {
                LOG.warn(e.getLocalizedMessage(), e);
            }
        }
    }
}
