package br.com.vanhackathon.axiomzen.mastermind.webserver;

import java.net.URI;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

public class Server {
    private static final String resources = "br.com.vanhackaton.axiomzen.mastermind.resource";
	
    public static final String BASE_URI = "http://localhost:8080/mastermind/";
	public static final String CONTENT_TYPE = "application/json";
	public static final String NEW_GAME = "new_game";
	public static final String GUESS = "guess";
	public static final String MULTIPLAYER_NEW_GAME = "multiplayer/new_game";
	public static final String MULTIPLAYER_GUESS = "multiplayer/guess";
	
    final HttpServer server;
    
    public Server() {
    	this.server = startServer(); 
    }
    
    public HttpServer startServer() {
        final ResourceConfig rc = new ResourceConfig().packages(resources);

        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }

    public void shutdown() {
    	this.server.shutdown();
    }
    
    public Client getClient() {
		ClientConfig clientConfig = new DefaultClientConfig();
		clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
		
		return Client.create(clientConfig);
    }
}
