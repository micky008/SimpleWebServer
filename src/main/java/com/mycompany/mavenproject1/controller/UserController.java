package com.mycompany.mavenproject1.controller;

import com.mycompany.mavenproject1.entity.User;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postUser(String user) {
        System.out.println(user);
        return Response.ok().build();
    }

}
