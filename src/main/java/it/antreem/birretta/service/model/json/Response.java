package it.antreem.birretta.service.model.json;

/**
 *
 * @author gmorlini
 */
public class Response {
    private Status status;
    private Body body;
    private Metadata metaData;

    public Response(Status status, Body body, Metadata metadata) {
        this.metaData = metadata;
        this.status = status;
        this.body = body;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public Metadata getMetaData() {
        return metaData;
    }

    public void setMetaData(Metadata metaData) {
        this.metaData = metaData;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}
