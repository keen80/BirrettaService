package it.antreem.birretta.service.model;

import java.util.Date;

/**
 *
 * @author gmorlini
 */
public class Feedback extends MongoDBObject{
    private String idFeedback;
    private String comment;
    private String idUser;
    private String idActivity;
    private String type;//rate o altro
    private Integer like=0;
    private Date insertedOn;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getIdFeedback() {
        return super.getId().toString();
    }
/*
    public void setIdFeedback(String idFeedback) {
        this.idFeedback = idFeedback;
    }*/

    public String getIdActivity() {
        return idActivity;
    }

    public void setIdActivity(String idActivity) {
        this.idActivity = idActivity;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public Date getInsertedOn() {
        return insertedOn;
    }

    public void setInsertedOn(Date insertedOn) {
        this.insertedOn = insertedOn;
    }

    public Integer getLike() {
        return like;
    }

    public void setLike(Integer like) {
        this.like = like;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
