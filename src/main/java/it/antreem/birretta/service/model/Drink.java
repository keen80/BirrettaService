package it.antreem.birretta.service.model;

import java.util.Date;

/**
 * Oggetto di modello che rappresenta una bevuta (check-in).
 * 
 * @author alessio
 */
public class Drink extends MongoDBObject
{
    private String idUser;
    private String idBeer;
    private String idLocation;
    private String comment;
    private Integer score;
    private Date timestamp;
    private byte[] picture;

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getIdBeer() {
        return idBeer;
    }

    public void setIdBeer(String idBeer) {
        this.idBeer = idBeer;
    }

    public String getIdLocation() {
        return idLocation;
    }

    public void setIdLocation(String idLocation) {
        this.idLocation = idLocation;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
