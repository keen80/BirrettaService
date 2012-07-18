package it.antreem.birretta.service;

import it.antreem.birretta.service.dto.ArtifactListDTO;
import it.antreem.birretta.service.dto.FolderListDTO;
import java.io.IOException;
import java.rmi.RemoteException;
import javax.ws.rs.*;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.util.JSONPObject;

/** 
 * BirrettaService
 * TODO: Commenti
 */
@Path("/birrettaservice")
public class BirrettaService
{
    /**
     * Restituisce tutti gli artifact presenti nel product backlog in formato
     * JSONP.
     * 
     * @param recursive Se includere anche gli artifact delle sottocartelle
     * @return Lista di artifact
     * @throws RemoteException 
     */
    @GET
    @Path("/jsonp/artifacts")
    @Produces("text/javascript")
    public String getProductBacklogArtifacts_jsonp
            (@DefaultValue("true") @QueryParam("recursive") boolean recursive,
             @DefaultValue("callback") @QueryParam("callback") String callbackName) 
                throws RemoteException, IOException 
    {
        JSONPObject obj = new JSONPObject(callbackName, "{prova: 10}");//new JSONPObject(callbackName, Backlog.getProductBacklogArtifacts(recursive));
        ObjectMapper mapper = new ObjectMapper();
        String s = mapper.writeValueAsString(obj);
        return s;
    }
    
    /**
     * Restituisce tutte le planning-folder del backlog in formato JSONP.
     */
    @GET
    @Path("/jsonp/folders")
    @Produces("application/json")
    public JSONPObject getProductBacklogFolders_jsonp
            (@DefaultValue("true") @QueryParam("recursive") boolean recursive,
             @DefaultValue("callback") @QueryParam("callback") String callbackName) 
                throws RemoteException
    {
        return null;
//        return new JSONPObject(callbackName, Backlog.getProductBacklogFolders(recursive));
    }

    /**
     * Restituisce tutti gli artifact presenti nel product backlog in formato
     * JSON.
     * 
     * @param recursive Se includere anche gli artifact delle sottocartelle
     * @return Lista di artifact
     * @throws RemoteException 
     */
    @GET
    @Path("/json/artifacts")
    @Produces("application/json")
    public ArtifactListDTO getProductBacklogArtifacts_json
            (@DefaultValue("true") @QueryParam("recursive") boolean recursive,
             @DefaultValue("callback") @QueryParam("callback") String callbackName) 
                throws RemoteException 
    {
        return null;
        //return Backlog.getProductBacklogArtifacts(recursive);
    }
    
    /**
     * Restituisce tutte le planning-folder del backlog in formato JSON.
     */
    @GET
    @Path("/json/folders")
    @Produces("application/json")
    public FolderListDTO getProductBacklogFolders_json
            (@DefaultValue("true") @QueryParam("recursive") boolean recursive,
             @DefaultValue("callback") @QueryParam("callback") String callbackName) 
                throws RemoteException 
    {
        return null;
//        return Backlog.getProductBacklogFolders(recursive);
    }
}
