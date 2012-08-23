package it.antreem.birretta.service.model;

import java.util.Date;

/**
 *
 * @author alessio
 */
public class Notification extends MongoDBObject
{
    private String idNotification;
    private String idUser;
    private String displayName;
    private String idBeer;
    private String beerName;
    private String idFriend;
    private String friendName;
    private String idPlace;
    private String placeName;
    private String description;
    private String type;
    private Boolean read;
    private Date insertedOn;


    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getRead() {
        return read;
    }

    public void setRead(Boolean read) {
        this.read = read;
    }

    public Date getInsertedOn() {
        return insertedOn;
    }

    public void setInsertedOn(Date insertedOn) {
        this.insertedOn = insertedOn;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
