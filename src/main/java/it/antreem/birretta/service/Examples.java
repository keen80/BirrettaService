package it.antreem.birretta.service;

import it.antreem.birretta.service.dto.UserExample;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Classe contenente qualche metodo di esempio utile.
 */
@Path("/examples")
public class Examples 
{    
    @POST
    @Path("/provaPost")
    @Consumes("application/json")
    @Produces("application/json")
    // http://localhost:8080/birrettaservice/rest/examples/provaPost
    public Response provaPost(UserExample u) 
    {
        Response.ResponseBuilder builder = Response.ok(u, MediaType.APPLICATION_JSON);
        return builder.build();
    }
    
//    @GET
//    @Path("/get")
//    @Produces("application/pdf")
//    public Response getFile() 
//    {
//            File file = new File(FILE_PATH);
//
//            ResponseBuilder response = Response.ok((Object) file);
//            response.header("Content-Disposition", "attachment; filename=new-android-book.pdf");
//            return response.build();
//    }
    
    
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
}
