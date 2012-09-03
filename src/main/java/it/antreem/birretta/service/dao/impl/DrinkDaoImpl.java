package it.antreem.birretta.service.dao.impl;

import com.mongodb.*;
import it.antreem.birretta.service.dao.DaoException;
import it.antreem.birretta.service.dao.DaoFactory;
import it.antreem.birretta.service.dao.DrinkDao;
import it.antreem.birretta.service.model.Drink;
import it.antreem.birretta.service.model.User;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bson.types.ObjectId;

/**
 *
 * @author alessio
 */
public class DrinkDaoImpl extends AbstractMongoDao implements DrinkDao {

    public final static String DRINKS_COLLNAME = "drinks";
    private static final Log log = LogFactory.getLog(DrinkDaoImpl.class);

    @Override
    public int saveDrink(Drink d) throws DaoException {
        DB db = null;
        try {
            db = getDB();
            db.requestStart();
            DBCollection drinks = db.getCollection(DRINKS_COLLNAME);
            BasicDBObject drink = createDBObjectFromDrink(d);
            return drinks.insert(drink).getN();
        } catch (MongoException ex) {
            log.error(ex.getLocalizedMessage(), ex);
            throw new DaoException(ex.getLocalizedMessage(), ex);
        } finally {
            if (db != null) {
                db.requestDone();
            }
        }
    }

    @Override
    public Drink findLastDrinkByUsername(String username) throws DaoException {
        User u = DaoFactory.getInstance().getUserDao().findUserByUsername(username);
        if (u == null) {
            return null;
        }
        String idUser = u.getIdUser();

        DB db = null;
        try {
            db = getDB();
            db.requestStart();
            DBCollection beers = db.getCollection(DRINKS_COLLNAME);
            BasicDBObject query = new BasicDBObject();
            query.put("idUser", idUser);
            DBCursor cur = beers.find(query).sort(new BasicDBObject("insertedOn", -1));

            while (cur.hasNext()) {
                DBObject _d = cur.next();
                Drink d = createDrinkFromDBObject(_d);
                return d;
            }

            return null;
        } catch (MongoException ex) {
            log.error(ex.getLocalizedMessage(), ex);
            throw new DaoException(ex.getLocalizedMessage(), ex);
        } finally {
            if (db != null) {
                db.requestDone();
            }
        }
    }

    @Override
    public List<Drink> findDrinksByUsername(String username, Integer limit) throws DaoException {
        List<Drink> list = new ArrayList<Drink>();

        User u = DaoFactory.getInstance().getUserDao().findUserByUsername(username);
        if (u == null) {
            return list;
        }
        String idUser = u.getIdUser();

        if (limit == null || limit < 0) {
            limit = 10;
        }

        BasicDBObject query = new BasicDBObject();
        query.put("idUser", idUser);
        DBCursor cur = getDrinkBy(query).sort(new BasicDBObject("insertedOn", -1)).limit(limit);

        return generateListOfDrink(cur);
    }

    @Override
    public List<Drink> findRecentDrinks(String username, Integer minutesAgo) throws DaoException {
        if (minutesAgo == null || minutesAgo < 0 || minutesAgo > 3600) {
            minutesAgo = 10;
        }



        User u = DaoFactory.getInstance().getUserDao().findUserByUsername(username);
        if (u == null) {
            return new ArrayList<Drink>();
        }
        String idUser = u.getIdUser();

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE, -minutesAgo);
        Date _date = cal.getTime();

        BasicDBObject query = new BasicDBObject();
        query.put("idUser", idUser);
        query.put("insertedOn", new BasicDBObject("$gte", _date));
        DBCursor cur = getDrinkBy(query);

        return generateListOfDrink(cur);
    }

    @Override
    public int countDrinksByUsername(String username) throws DaoException {
        User u = DaoFactory.getInstance().getUserDao().findUserByUsername(username);
        if (u == null) {
            return 0;
        }
        String idUser = u.getIdUser();
        BasicDBObject query = new BasicDBObject();
        query.put("idUser", idUser);
        return getDrinkBy(query).count();
    }

    //metodi comuni
    private ArrayList<Drink> generateListOfDrink(DBCursor cur) throws MongoException {
        ArrayList<Drink> list = new ArrayList<Drink>();
        while (cur.hasNext()) {
            DBObject _d = cur.next();
            Drink d = createDrinkFromDBObject(_d);
            list.add(d);
        }

        return list;
    }

    @Override
    public int countDrinksByPlace(String idLocation) throws DaoException {
        return getDrinksByPlace(idLocation).count();
    }

    @Override
    public ArrayList<Drink> listDrinksByPlace(String idLocation, int limit) {
        ArrayList<Drink> list = new ArrayList<Drink>();
        DBCursor cur = null;
        if (limit > 0) {
            cur = getDrinksByPlace(idLocation).limit(limit);
        } else {
            cur = getDrinksByPlace(idLocation);
        }

        while (cur.hasNext()) {
            
            DBObject _d = cur.next();
            log.info("listDrinksByPlace - "+ _d);
            Drink d = createDrinkFromDBObject(_d);
            log.info("listDrinksByPlace - "+ d);
            list.add(d);
        }
        return list;
    }

    @Override
    public ArrayList<Drink> getDrinksList(Integer maxElement) throws DaoException {
        ArrayList<Drink> list = new ArrayList<Drink>();

        DB db = null;
        try {
            db = getDB();
            db.requestStart();
            DBCollection drinks = db.getCollection(DRINKS_COLLNAME);
            BasicDBObject param = new BasicDBObject();
            //ordinamento crescente(1) per il nome
            //decrescente sarebbe stato -1
            param.put("displayName", 1);
            log.info("sort param:" + param.toString());
            DBCursor cur = null;
            cur = drinks.find();
            DBCursor curOrdered = cur.sort(param);
            if (maxElement > 0) {
                curOrdered.limit(maxElement);
            }
            while (curOrdered.hasNext()) {
                DBObject _d = curOrdered.next();
                Drink d = createDrinkFromDBObject(_d);
                list.add(d);
            }
        } catch (MongoException ex) {
            log.error(ex.getLocalizedMessage(), ex);
            throw new DaoException(ex.getLocalizedMessage(), ex);
        } finally {
            if (db != null) {
                db.requestDone();
            }
        }

        return list;
    }

    @Override
    public ArrayList<Drink> findDrinksByUsernameAndPlace(ArrayList<String> idUsers, String idPlace, int maxElemet) {
        ArrayList<Drink> list = new ArrayList<Drink>();
        //QUERY PRINCIPALE
        BasicDBObject query = new BasicDBObject();
        //QUERY PER INDICARE BEVUTE DI QUALI UTENTI
        BasicDBObject users=new BasicDBObject();
        users.put("$in", idUsers);
        
        query.put("idUser",users);
        query.put("idLocation", idPlace);
        DBCursor cur = null;
        if (maxElemet > 0) {
            cur = getDrinkBy(query).sort(new BasicDBObject("insertedOn", -1)).limit(maxElemet);
        }
        return generateListOfDrink(cur);
    }

    private DBCursor getDrinksByPlace(String idLocation) throws DaoException {
            BasicDBObject query = new BasicDBObject();
            query.put("idLocation", idLocation);
            return getDrinkBy(query).sort(new BasicDBObject("insertedOn", -1));
    }

    private DBCursor getDrinkBy(BasicDBObject query) throws DaoException {
        DB db = null;
        try {
            db = getDB();
            db.requestStart();
            DBCollection drinks = db.getCollection(DRINKS_COLLNAME);

            return drinks.find(query);
        } catch (MongoException ex) {
            log.error(ex.getLocalizedMessage(), ex);
            throw new DaoException(ex.getLocalizedMessage(), ex);
        } finally {
            if (db != null) {
                db.requestDone();
            }
        }
    }

    protected static Drink createDrinkFromDBObject(DBObject obj) {
        Drink d = new Drink();
        d.setId((ObjectId) obj.get("_id"));
        d.setDisplayName((String) obj.get("displayName"));
        d.setImage((String) obj.get("image"));
        d.setIdUser((String) obj.get("idUser").toString());
        d.setIdBeer((String) obj.get("idBeer").toString());
        //nel db non c'è nessun place ma solo location, problema dovuto a utilizzo oggetto sia per DTO che per modello
        d.setIdPlace((String) obj.get("idLocation"));
        d.setBeerName((String) obj.get("beerName").toString());
        d.setPlaceName((String) obj.get("locationName"));
        d.setRate((Integer) obj.get("rate"));
        d.setRate((Integer) obj.get("rate2"));
        d.setRate((Integer) obj.get("rate3"));
        Object date = (Object) obj.get("insertedOn");
        if (date != null &&date.getClass().equals(Date.class)) {
            d.setInsertedOn((Date)date);
        }
        return d;

    }

    // For first insert
    protected static BasicDBObject createDBObjectFromDrink(Drink d) {
        BasicDBObject _d = new BasicDBObject();
        _d.put("displayName", d.getDisplayName());
        _d.put("image", d.getImage());
        _d.put("idUser", d.getIdUser());
        _d.put("idBeer", d.getIdBeer());
         //nel db non c'è nessun place ma solo location, problema dovuto a utilizzo oggetto sia per DTO che per modello
        _d.put("idLocation", d.getIdPlace());
        _d.put("beerName", d.getBeerName());
        _d.put("locationName", d.getPlaceName());
        _d.put("rate", d.getRate());
        _d.put("rate2", d.getRate2());
        _d.put("rate3", d.getRate3());
        _d.put("insertedOn", d.getInsertedOn());
        return _d;
    }
}
