package br.com.vanhackathon.axiomzen.mastermind.resource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import br.com.vanhackathon.axiomzen.mastermind.model.Game;
import br.com.vanhackathon.axiomzen.mastermind.model.User;
import br.com.vanhackathon.axiomzen.mastermind.webserver.Server;

public class TestNewGame {
	
	private static final String USER_NAME = "Frederico de Oliveira";
	Server server;
	
	User user;
	
	@Before
	public void init() {
		server = new Server();
		
		user = new User();
		user.setUser(USER_NAME);
	}
	
	@After
	public void shutdown() {
		server.shutdown();
	}
	
	@Test
	public void test() {
		Client client = server.getClient();

		WebResource webResource = client.resource(Server.BASE_URI + Server.NEW_GAME);
		
		ClientResponse response = webResource.type(Server.CONTENT_TYPE).post(ClientResponse.class, user);
		
		Game game = response.getEntity(Game.class);
		
		assertNotNull(game);
	}
	
	@Test
	public void testLoad() {
		final Map<String, Game> gameMap = new HashMap<String, Game>();
		int copies = 100;
		
		Runnable execute = new Runnable() {
			
			@Override
			public void run() {
				Client client = server.getClient();

				WebResource webResource = client.resource(Server.BASE_URI + Server.NEW_GAME);
				
				ClientResponse response = webResource.type(Server.CONTENT_TYPE).post(ClientResponse.class, user);
				
				Game game = response.getEntity(Game.class);
				
				gameMap.put(game.getGameKey(), game);
			}
		};
		
		for (int i = 0; i < copies; i++) {
			execute.run();			
		}
		
		assertEquals(copies, gameMap.size());
	}

}
