package it.antreem.birretta.service.model;

import java.util.Date;

/**
 *
 * @author alessio
 */
public class Session extends MongoDBObject
{
    private String sid;
    private String username;
    private Date timestamp;

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
