package it.antreem.birretta.service.model;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author alessio
 */
public class GeoLoc extends MongoDBObject
{
    private String idUser;
    private ArrayList<Double> pos = new ArrayList<Double>();
    private Date timestamp;

    public GeoLoc(){
        this.pos.clear();
        this.pos.add(0.0);
        this.pos.add(0.0);
        this.timestamp = new Date();
    }
    
    public GeoLoc(String idUser, double lat, double lon){
        this.pos.clear();
        this.pos.add(0.0);
        this.pos.add(0.0);
        
        this.idUser = idUser;
        this.pos.set(0, lat);
        this.pos.set(1, lon);
        this.timestamp = new Date();
    }
    
    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public ArrayList<Double> getPos() {
        return pos;
    }

    public void setPos(ArrayList<Double> pos) {
        this.pos = pos;
    }
    
    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
