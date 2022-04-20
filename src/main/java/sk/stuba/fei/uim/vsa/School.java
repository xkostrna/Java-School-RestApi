package sk.stuba.fei.uim.vsa;

import jakarta.ws.rs.core.UriBuilder;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;

public class School {

    public static final Logger LOGGER = Logger.getLogger(School.class.getName());
    public static final String BASE_URI = "http://localhost/";

    public static HttpServer startServer() {
        final ResourceConfig config = ResourceConfig.forApplicationClass(SchoolApplication.class);
        URI baseUri = UriBuilder.fromUri(BASE_URI).port(8080).build();
        LOGGER.info("Starting Jetty server...");
        LOGGER.info("Server listening on http://localhost:8080/");
        return GrizzlyHttpServerFactory.createHttpServer(baseUri, config);
    }

    public static void main(String[] args) {
        try {
            final HttpServer server = startServer();
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                try {
                    System.out.println("Shutting down the application...");
                    server.shutdown();
                    System.out.println("Exiting...");
                } catch (Exception e) {
                    LOGGER.log(Level.SEVERE, null, e);
                }
            }));
            System.out.printf("Application started.%nStop the application using CTRL+C%n");
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            LOGGER.log(Level.SEVERE, null, e);
        }
    }

}
