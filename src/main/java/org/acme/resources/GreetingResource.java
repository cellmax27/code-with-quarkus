package org.acme.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.acme.model.Greeting;
import org.acme.services.GreetingService;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource {

    @Inject
    GreetingService service;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/greeting/{name}")
    public String greeting(String name) {
        return service.greeting(name);
    }

//    @GET
//    @Produces(MediaType.TEXT_PLAIN)
//    public String hello() {
//        return "Hello from Quarkus REST";
//    }
    
    @GET
    @Transactional
    @Produces(MediaType.TEXT_PLAIN)
    public String hello(@QueryParam("name") String name) {
       Greeting greeting = new Greeting();
       greeting.name = name;
       greeting.persist();
       return "Hello " + name;
    }
    
    @GET
    @Path("names")
    @Produces(MediaType.TEXT_PLAIN)
    public String names() {
        List<Greeting> greetings = Greeting.listAll();
        String names = greetings.stream().map(g-> g.name)
           .collect(Collectors.joining (", "));
        return "I've said hello to " + names;
    }
}