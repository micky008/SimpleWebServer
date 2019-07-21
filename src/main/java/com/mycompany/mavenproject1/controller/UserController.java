
package com.mycompany.mavenproject1.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author local
 */
@Path("/user")
public class UserController {
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)    
    public Response isOk() {
        return Response.ok().build();
    
    }
    
    
}
