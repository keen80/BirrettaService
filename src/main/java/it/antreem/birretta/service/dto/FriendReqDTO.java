package it.antreem.birretta.service.dto;

import it.antreem.birretta.service.model.MongoDBObject;
import javax.ws.rs.FormParam;


public class FriendReqDTO extends MongoDBObject
{
    @FormParam("idRequestor")
    private String idRequestor;
    @FormParam("idRequested")
    private String idRequested;

    public String getIdRequested() {
        return idRequested;
    }

    public void setIdRequested(String idRequested) {
        this.idRequested = idRequested;
    }

    public String getIdRequestor() {
        return idRequestor;
    }

    public void setIdRequestor(String idRequestor) {
        this.idRequestor = idRequestor;
    }
}
