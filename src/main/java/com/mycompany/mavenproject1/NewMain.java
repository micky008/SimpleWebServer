package com.mycompany.mavenproject1;

import com.mycompany.mavenproject1.controller.UserController;
import com.mycompany.mavenproject1.dao.FactoryDAO;
import com.mycompany.mavenproject1.provider.ProviderAuth;
import com.sun.net.httpserver.HttpServer;
import java.net.URI;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

/**
 *
 * @author local
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        FactoryDAO.init();
        URI baseUri = UriBuilder.fromUri("http://localhost/").port(9998).build();
        ResourceConfig config = new ResourceConfig(UserController.class);//, ProviderAuth.class);        
        HttpServer server = JdkHttpServerFactory.createHttpServer(baseUri, config);
        System.out.println("Server started");
    }

}
