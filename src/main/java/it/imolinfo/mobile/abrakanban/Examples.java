package it.imolinfo.mobile.abrakanban;

import it.imolinfo.mobile.abrakanban.dto.User;
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
    public Response provaPost(User u) 
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
    
}
