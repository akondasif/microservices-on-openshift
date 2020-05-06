package org.jnd.microservices.quarkus;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import javax.ws.rs.POST;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;


@Path("/")
@RegisterRestClient
@RegisterProvider(LoggingFilter.class)
public interface BrokerClient {

    // -H "content-type: application/json"
    // -H "ce-specversion: 1.0"
    // -H "ce-source: curl-command"
    // -H "ce-type: curl.demo"
    // -H "ce-id: 123-abc"
    
    @POST
    //@Produces("application/json")
    Response send(String data, 
            @HeaderParam("ce-specversion") String specversion,
            @HeaderParam("ce-source") String source,
            @HeaderParam("ce-type") String type,
            @HeaderParam("ce-id") String id);
}