package it.antreem.birretta.service;

import it.antreem.birretta.service.dto.CredentialsDTO;
import it.antreem.birretta.service.dto.ErrorDTO;
import it.antreem.birretta.service.dto.LoginResponseDTO;
import it.antreem.birretta.service.util.ErrorCodes;
import it.antreem.birretta.service.util.Utils;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/** 
 * BirrettaService
 */
@Path("/bserv")
public class BirrettaService
{
    private static final Log log = LogFactory.getLog(BirrettaService.class);
    
    /**
     * Operazione di login dell'utente.<br/>
     * Nel caso l'utente faccia login correttamente, viene creata una sessione
     * attiva su DB oppure si continua ad utilizzare quella corrente.<br/>
     * In caso di login fallito si ritorna esito negativo (possibile estensione
     * futura con blocco utente, etc.).
     * 
     * @param c Credenziali dell'utente, ovvero username + password.
     * @return Esito della login
     */
    @POST
    @Path("/login")
    @Consumes("application/json")
    @Produces("application/json")
    public Response login(CredentialsDTO c) 
    {
        // Pre-conditions
        if (c == null)
        {
            log.debug("Credenziali di login passate a null. Errore.");
            ErrorDTO err = Utils.createError(ErrorCodes.LOGIN_FAILED.getCode(), 
                                           ErrorCodes.LOGIN_FAILED.getTitle(),
                                           ErrorCodes.LOGIN_FAILED.getMessage(), 
                                           null);
            Response.ResponseBuilder builder = Response.ok(err, MediaType.APPLICATION_JSON);
            return builder.build();
        }
        
        LoginResponseDTO response = null;
        
        String username = c.getUsername() != null ? c.getUsername() : "";
        String password = c.getPassword() != null ? c.getPassword() : "";
        String hash = Utils.SHAsum(Utils.SALT.concat(password).getBytes());
        
        if (log.isDebugEnabled()){
            log.debug("Tentativo di login di username: " + username + " con hash pwd: " + hash); 
        }
        
        /*
         * Login successful
         *  - is there an active session? use that one
         *  - else create a new session
         */
        
        /*
         * Login failed
         *  - return an error
         */
        
        Response.ResponseBuilder builder = Response.ok(response, MediaType.APPLICATION_JSON);
        return builder.build();
    }
    
    
//    /**
//     * Restituisce tutti gli artifact presenti nel product backlog in formato
//     * JSONP.
//     * 
//     * @param recursive Se includere anche gli artifact delle sottocartelle
//     * @return Lista di artifact
//     * @throws RemoteException 
//     */
//    @GET
//    @Path("/jsonp/artifacts")
//    @Produces("text/javascript")
//    public String getProductBacklogArtifacts_jsonp
//            (@DefaultValue("true") @QueryParam("recursive") boolean recursive,
//             @DefaultValue("callback") @QueryParam("callback") String callbackName) 
//                throws RemoteException, IOException 
//    {
//        JSONPObject obj = new JSONPObject(callbackName, "{prova: 10}");//new JSONPObject(callbackName, Backlog.getProductBacklogArtifacts(recursive));
//        ObjectMapper mapper = new ObjectMapper();
//        String s = mapper.writeValueAsString(obj);
//        return s;
//    }
//    
//    /**
//     * Restituisce tutte le planning-folder del backlog in formato JSONP.
//     */
//    @GET
//    @Path("/jsonp/folders")
//    @Produces("application/json")
//    public JSONPObject getProductBacklogFolders_jsonp
//            (@DefaultValue("true") @QueryParam("recursive") boolean recursive,
//             @DefaultValue("callback") @QueryParam("callback") String callbackName) 
//                throws RemoteException
//    {
//        return null;
////        return new JSONPObject(callbackName, Backlog.getProductBacklogFolders(recursive));
//    }
//
//    /**
//     * Restituisce tutti gli artifact presenti nel product backlog in formato
//     * JSON.
//     * 
//     * @param recursive Se includere anche gli artifact delle sottocartelle
//     * @return Lista di artifact
//     * @throws RemoteException 
//     */
//    @GET
//    @Path("/json/artifacts")
//    @Produces("application/json")
//    public ArtifactListDTO getProductBacklogArtifacts_json
//            (@DefaultValue("true") @QueryParam("recursive") boolean recursive,
//             @DefaultValue("callback") @QueryParam("callback") String callbackName) 
//                throws RemoteException 
//    {
//        return null;
//        //return Backlog.getProductBacklogArtifacts(recursive);
//    }
//    
//    /**
//     * Restituisce tutte le planning-folder del backlog in formato JSON.
//     */
//    @GET
//    @Path("/json/folders")
//    @Produces("application/json")
//    public FolderListDTO getProductBacklogFolders_json
//            (@DefaultValue("true") @QueryParam("recursive") boolean recursive,
//             @DefaultValue("callback") @QueryParam("callback") String callbackName) 
//                throws RemoteException 
//    {
//        return null;
////        return Backlog.getProductBacklogFolders(recursive);
//    }
}
