/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.antreem.birretta.service;

import java.io.IOException;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.ext.Provider;
import org.jboss.resteasy.annotations.interception.ServerInterceptor;
import org.jboss.resteasy.spi.interception.MessageBodyWriterContext;
import org.jboss.resteasy.spi.interception.MessageBodyWriterInterceptor;

/**
 *
 * @author gmorlini
 */
@Path("/bserv")
@Provider
@ServerInterceptor
public class RESTv1 implements RESTInterfaceV1, MessageBodyWriterInterceptor
{
    @Override
    public void write(final MessageBodyWriterContext context) throws IOException, WebApplicationException
    {   
        System.out.println("write RESTv1");
        context.getHeaders().add(RESTInterfaceV1.ACCESS_CONTROL_ALLOW_HEADERS, "origin");
        context.getHeaders().add(RESTInterfaceV1.ACCESS_CONTROL_ALLOW_ORIGIN_HEADER, "*");
        context.proceed();      
    }

    @OPTIONS
    @Path("/{path:.*}")
    public Response handleCORSRequestOPTIONS(@HeaderParam(RESTInterfaceV1.ACCESS_CONTROL_REQUEST_METHOD) final String requestMethod, @HeaderParam(RESTInterfaceV1.ACCESS_CONTROL_REQUEST_HEADERS) final String requestHeaders)
    {
           System.out.println("write handleCORSRequest OPTIONS");
        final ResponseBuilder retValue = Response.ok();

        if (requestHeaders != null)
            retValue.header(RESTInterfaceV1.ACCESS_CONTROL_ALLOW_HEADERS, requestHeaders);

        if (requestMethod != null)
            retValue.header(RESTInterfaceV1.ACCESS_CONTROL_ALLOW_METHODS, requestMethod);

        retValue.header(RESTInterfaceV1.ACCESS_CONTROL_ALLOW_ORIGIN_HEADER, "*");

        return retValue.build();
    }
    
    @POST
    @Path("/{path:.*}")
    public Response handleCORSRequestPOST(@HeaderParam(RESTInterfaceV1.ACCESS_CONTROL_REQUEST_METHOD) final String requestMethod, @HeaderParam(RESTInterfaceV1.ACCESS_CONTROL_REQUEST_HEADERS) final String requestHeaders)
    {
           System.out.println("write handleCORSRequest POST");
        final ResponseBuilder retValue = Response.ok();

        if (requestHeaders != null)
            retValue.header(RESTInterfaceV1.ACCESS_CONTROL_ALLOW_HEADERS, requestHeaders);

        if (requestMethod != null)
            retValue.header(RESTInterfaceV1.ACCESS_CONTROL_ALLOW_METHODS, requestMethod);

        retValue.header(RESTInterfaceV1.ACCESS_CONTROL_ALLOW_ORIGIN_HEADER, "*");

        return retValue.build();
    }
}
