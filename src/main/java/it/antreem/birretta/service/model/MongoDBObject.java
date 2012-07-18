package it.antreem.birretta.service.model;

import org.bson.types.ObjectId;

/**
 *
 * @author alessio
 */
public class MongoDBObject 
{
    private ObjectId id;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }
}
