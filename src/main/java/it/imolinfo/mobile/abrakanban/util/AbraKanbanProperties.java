package it.imolinfo.mobile.abrakanban.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Configurazione delle properties statiche.
 */
public class AbraKanbanProperties 
{
    // Properties caricate solo in fase di startup
    public static final String SOURCEFORGE_URL;
    public static final String SOURCEFORGE_USER;
    public static final String SOURCEFORGE_PASSWORD;
  
    public static final String PRODUCT_BACKLOG_ID;
    /**
     * Il logger per questa classe.
     */
    private static final Log LOG = LogFactory.getLog(AbraKanbanProperties.class);

    static 
    {
        Properties prop;
        LOG.debug("Caricamento delle properties statiche...");
        try 
        {
            prop = loadPropertiesFromFile("abrakanban.properties");
        } 
        catch (IOException e) {
            LOG.error(e.getMessage(), e);
            throw new ExceptionInInitializerError(e);
        }
        if (LOG.isDebugEnabled()) 
        {
            LOG.debug("Properties statiche caricate: " + prop);
        }

        SOURCEFORGE_URL = prop.getProperty("sourceforge.url");
        if (SOURCEFORGE_URL == null) throw new ExceptionInInitializerError("SOURCEFORGE_URL == null");
        
        SOURCEFORGE_USER = prop.getProperty("sourceforge.user");
        if (SOURCEFORGE_USER == null) throw new ExceptionInInitializerError("SOURCEFORGE_USER == null");
        
        SOURCEFORGE_PASSWORD=prop.getProperty("sourceforge.password");
        if (SOURCEFORGE_PASSWORD == null) throw new ExceptionInInitializerError("SOURCEFORGE_PASSWORD == null");
        
        PRODUCT_BACKLOG_ID=prop.getProperty("product.backlog.id");
        if (PRODUCT_BACKLOG_ID == null) throw new ExceptionInInitializerError("PRODUCT_BACKLOG_ID == null");
    }

    private AbraKanbanProperties() {
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
