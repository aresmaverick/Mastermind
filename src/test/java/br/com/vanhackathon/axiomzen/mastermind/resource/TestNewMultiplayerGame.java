package br.com.vanhackathon.axiomzen.mastermind.resource;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import br.com.vanhackathon.axiomzen.mastermind.model.MultiplayerGame;
import br.com.vanhackathon.axiomzen.mastermind.model.User;
import br.com.vanhackathon.axiomzen.mastermind.webserver.Server;

public class TestNewMultiplayerGame {
	Server server;
	
	User user = new User();
	
	@Before
	public void init() {
		server = new Server();
		
		user.setUser("Frederico de Oliveira");
	}
	
	@After
	public void shutdown() {
		server.shutdown();
	}
	
	@Test
	public void test() {
		Client client = server.getClient();

		WebResource webResource = client.resource(Server.BASE_URI + Server.MULTIPLAYER_NEW_GAME);
		
		ClientResponse response = webResource.type(Server.CONTENT_TYPE).post(ClientResponse.class, user);
		
		MultiplayerGame mGame = response.getEntity(MultiplayerGame.class);
		
		assertNotNull(mGame);
	}
	
	@Test
	public void testMorePlayers() throws IOException {
		Client client = server.getClient();

		WebResource webResource = client.resource(Server.BASE_URI + Server.MULTIPLAYER_NEW_GAME);
		
		User user = new User();
		user.setUser("Frederico de Oliveira");
		
		ClientResponse response = webResource.type(Server.CONTENT_TYPE).post(ClientResponse.class, user);
		
		MultiplayerGame mGame = response.getEntity(MultiplayerGame.class);
		
		User secondUser = new User();
		secondUser.setGameKey(mGame.getGameKey());
		secondUser.setUser("Patricia Lopes de Oliveira");
		
		response = webResource.type(Server.CONTENT_TYPE).post(ClientResponse.class, secondUser);
		
		mGame = response.getEntity(MultiplayerGame.class);
		
		assertNotNull(mGame);
	}
}
