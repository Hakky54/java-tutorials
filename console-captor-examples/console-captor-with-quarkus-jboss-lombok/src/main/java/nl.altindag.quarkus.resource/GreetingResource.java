package nl.altindag.quarkus.resource;

import lombok.extern.jbosslog.JBossLog;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@JBossLog
@Path("/hello")
public class GreetingResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        log.info("Saying hello from the logger!");
        return "Hello from RESTEasy Reactive";
    }

}