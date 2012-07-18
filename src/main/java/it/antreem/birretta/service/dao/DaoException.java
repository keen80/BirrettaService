package it.antreem.birretta.service.dao;

import com.mongodb.MongoException;

/**
 *
 * @author alessio
 */
public class DaoException extends MongoException 
{
    
    public DaoException(String msg){
        super(msg);
    }
    
    public DaoException(String msg, Throwable t){
        super(msg, t);
    }
    
}
