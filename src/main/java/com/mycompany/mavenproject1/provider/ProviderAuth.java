package com.mycompany.mavenproject1.provider;

import com.mycompany.mavenproject1.dao.FactoryDAO;
import com.mycompany.mavenproject1.entity.User;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.StringTokenizer;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author local
 */
@Provider
public class ProviderAuth implements javax.ws.rs.container.ContainerRequestFilter {

    @Context
    private ResourceInfo resourceInfo;

    private static final String AUTHORIZATION_PROPERTY = "Authorization";
    private static final String AUTHENTICATION_SCHEME = "Basic";

    @Override
    public void filter(ContainerRequestContext requestContext) {

        //Get request headers
        final MultivaluedMap<String, String> headers = requestContext.getHeaders();

         //Fetch authorization header
        final List<String> authorization = headers.get(AUTHORIZATION_PROPERTY);

        //If no authorization information present; block access
        if (authorization == null || authorization.isEmpty()) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                    .entity("You cannot access this resource").build());
            return;
        }

        //Get encoded username and password
        final String encodedUserPassword = authorization.get(0).replaceFirst(AUTHENTICATION_SCHEME + " ", "");

        //Decode username and password
        String usernameAndPassword = new String(Base64.getDecoder().decode(encodedUserPassword.getBytes()));;

        //Split username and password tokens
        final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");
        final String username = tokenizer.nextToken();
        final String password = tokenizer.nextToken();

        //Verifying Username and password
        System.out.println(username);
        System.out.println(password);
        
        
        User u = FactoryDAO.getUserDAO().getUserByLoginPassword(username, password);
        
        if (u == null){
            requestContext.abortWith(Response.status(Response.Status.FORBIDDEN).entity("Access blocked for all users !!").build());                   
        }

    }

}
