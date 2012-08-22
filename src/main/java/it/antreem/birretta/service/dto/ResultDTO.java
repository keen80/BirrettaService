package it.antreem.birretta.service.dto;

import it.antreem.birretta.service.model.Body;
import it.antreem.birretta.service.model.json.Metadata;
import it.antreem.birretta.service.model.json.Response;
import it.antreem.birretta.service.model.json.Status;

/**
 *
 * @author gmorlini
 */
public class ResultDTO {
    private Response response;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

}
