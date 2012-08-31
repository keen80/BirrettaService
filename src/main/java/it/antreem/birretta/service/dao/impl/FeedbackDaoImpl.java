package it.antreem.birretta.service.dao.impl;

import it.antreem.birretta.service.dao.FeedbackDao;
import com.mongodb.*;
import it.antreem.birretta.service.dao.DaoException;
import it.antreem.birretta.service.model.Feedback;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bson.types.ObjectId;

/**
 *
 * @author alessio
 */
public class FeedbackDaoImpl extends AbstractMongoDao implements FeedbackDao
{
    public final static String FEEDBACKS_COLLNAME = "feedbacks";
    
    
    private static final Log log = LogFactory.getLog(FeedbackDaoImpl.class);
    

    @Override
    public int udateFeedback(Feedback f) throws DaoException 
    {
         if (f == null) return 0;
        
        DB db = null;
        try
        {
            db = getDB();
            db.requestStart();
            DBCollection feeds = db.getCollection(FEEDBACKS_COLLNAME);
            int count = 0;
            BasicDBObject _feed=createDBObjectFromFeedback(f);  
            return feeds.update(new BasicDBObject("_id",f.getId()), _feed).getN();
        }
        catch(MongoException ex){
            log.error(ex.getLocalizedMessage(), ex);
            throw new DaoException(ex.getLocalizedMessage(), ex);
        }
        finally {
            if (db != null){
                db.requestDone();
            }
        }
    }
    
    @Override
    public Feedback findById(String id) throws DaoException 
    {
        DBObject obj = findById(id, FEEDBACKS_COLLNAME);
        if (obj == null) return null;
        Feedback f= createFeedbackFromDBObject(obj);
        return f;
    }
    
    @Override
    public List<Feedback> findByIdActivity(String idActivity) throws DaoException 
    {
        List<Feedback> list=new ArrayList<Feedback>();
        DB db = null;
        try
        {
            db = getDB();
            db.requestStart();
            DBCollection feedbacks = db.getCollection(FEEDBACKS_COLLNAME);
            BasicDBObject query = new BasicDBObject();
            query.put("idActivity", idActivity);
            DBCursor cur = feedbacks.find(query);
            
            while (cur.hasNext()){
                DBObject _f = cur.next();
                Feedback f = createFeedbackFromDBObject(_f);
                assert f.getIdActivity().equals(idActivity);
                list.add(f);
            }
            
            return list;
        }
        catch(MongoException ex){
            log.error(ex.getLocalizedMessage(), ex);
            throw new DaoException(ex.getLocalizedMessage(), ex);
        }
        finally {
            if (db != null){
                db.requestDone();
            }
        }
    }
    
    @Override
    public List<Feedback> findByIdUser(String idUser) throws DaoException 
    {
        List<Feedback> list=new ArrayList<Feedback>();
        DB db = null;
        try
        {
            db = getDB();
            db.requestStart();
            DBCollection feedbacks = db.getCollection(FEEDBACKS_COLLNAME);
            BasicDBObject query = new BasicDBObject();
            query.put("idUser", idUser);
            DBCursor cur = feedbacks.find(query);
            
            while (cur.hasNext()){
                DBObject _f = cur.next();
                Feedback f = createFeedbackFromDBObject(_f);
                assert f.getIdActivity().equals(idUser);
                list.add(f);
            }
            
            return list;
        }
        catch(MongoException ex){
            log.error(ex.getLocalizedMessage(), ex);
            throw new DaoException(ex.getLocalizedMessage(), ex);
        }
        finally {
            if (db != null){
                db.requestDone();
            }
        }
    }
    
    
    @Override
    public int saveFeedback(Feedback feedback) throws DaoException 
    {
        if (feedback == null) return 0;
        
        DB db = null;
        try
        {
            db = getDB();
            db.requestStart();
            DBCollection feeds = db.getCollection(FEEDBACKS_COLLNAME);
            int count = 0;
            BasicDBObject _feed=createDBObjectFromFeedback(feedback);  
            return feeds.insert(_feed).getN();
        }
        catch(MongoException ex){
            log.error(ex.getLocalizedMessage(), ex);
            throw new DaoException(ex.getLocalizedMessage(), ex);
        }
        finally {
            if (db != null){
                db.requestDone();
            }
        }
    }

    
    protected static Feedback createFeedbackFromDBObject(DBObject obj)
    {
        Feedback f = new Feedback();

        f.setId((ObjectId) obj.get("_id"));
        f.setIdActivity((String) obj.get("idActivity"));
        f.setIdUser((String) obj.get("idUser"));
        f.setInsertedOn((Date) obj.get("insertedOn"));
        f.setLike((Integer)obj.get("like"));
        f.setType((String)obj.get("type"));
        f.setComment((String)obj.get("comment"));
        
        return f;
    }

    private BasicDBObject createDBObjectFromFeedback(Feedback feedback) {
       BasicDBObject obj=new BasicDBObject();
       obj.put("idUser", feedback.getIdUser());
       obj.put("comment", feedback.getComment());
       obj.put("idActivity", feedback.getIdActivity());
       obj.put("type", feedback.getType());
       obj.put("like", feedback.getLike());
       obj.put("insertedOn", feedback.getInsertedOn());
       return obj;
    }
}
