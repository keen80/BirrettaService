package it.antreem.birretta.service.dto;

import it.antreem.birretta.service.model.Body;
import it.antreem.birretta.service.model.Metadata;
import it.antreem.birretta.service.model.Response;
import it.antreem.birretta.service.model.Status;

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
