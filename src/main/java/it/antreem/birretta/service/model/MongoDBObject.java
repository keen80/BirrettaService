package it.antreem.birretta.service.model;

import org.bson.types.ObjectId;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author alessio
 */
public class MongoDBObject 
{
    private ObjectId id;

    @JsonIgnore
    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }
}
