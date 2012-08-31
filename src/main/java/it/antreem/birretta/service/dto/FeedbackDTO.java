package it.antreem.birretta.service.dto;
import it.antreem.birretta.service.model.*;

import java.util.Date;
import javax.ws.rs.FormParam;

/**
 *
 * @author gmorlini
 */
public class FeedbackDTO extends MongoDBObject{
    @FormParam("comment")
    private String comment;
    @FormParam("idUser")
    private String idUser;
    @FormParam("idActivity")
    private String idActivity;
    @FormParam("type")
    private String type;//rate o altro

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

   

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
