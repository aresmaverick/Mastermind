package br.com.vanhackathon.axiomzen.mastermind.resource;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import br.com.vanhackathon.axiomzen.mastermind.model.Attempt;
import br.com.vanhackathon.axiomzen.mastermind.model.MultiplayerAttempt;
import br.com.vanhackathon.axiomzen.mastermind.model.MultiplayerGame;
import br.com.vanhackathon.axiomzen.mastermind.model.User;
import br.com.vanhackathon.axiomzen.mastermind.webserver.Server;

public class TestMultiplayerGuess {
	Server server;
	
	@Before
	public void init() {
		server = new Server();
	}
	
	@After
	public void shutdown() {
		server.shutdown();
	}
	
	@Test
	public void test() throws IOException {
			Client client = server.getClient();
			
			WebResource webResource = client.resource(Server.BASE_URI + Server.MULTIPLAYER_NEW_GAME);
			
			User user = new User();
			user.setUser("Frederico de Oliveira");
			
			ClientResponse response = webResource.type(Server.CONTENT_TYPE).post(ClientResponse.class, user);
			
			MultiplayerGame mGame = response.getEntity(MultiplayerGame.class);

			String firstPlayerGameKey = mGame.getLastGameKey();
			
			User secondUser = new User();
			secondUser.setUser("Patricia Lopes de Oliveira");
			secondUser.setGameKey(mGame.getGameKey());

			response = webResource.type(Server.CONTENT_TYPE).post(ClientResponse.class, secondUser);
			
			mGame = response.getEntity(MultiplayerGame.class);
			
			webResource = client.resource(Server.BASE_URI + Server.MULTIPLAYER_GUESS);
			
			Attempt attempt = new Attempt();
			
			attempt.setGameKey(firstPlayerGameKey);
			attempt.setCode("RPYGOGOP");

			MultiplayerAttempt mAttempt = new MultiplayerAttempt();
			mAttempt.setAttempt(attempt);
			mAttempt.setMultiplayerGameKey(mGame.getGameKey());
			
			response = webResource.type(Server.CONTENT_TYPE).post(ClientResponse.class, mAttempt);
			
//			BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntityInputStream()));
//			
//			System.out.println(br.readLine());
			
			mGame = response.getEntity(MultiplayerGame.class);
			
			attempt = new Attempt();
			
			attempt.setGameKey(firstPlayerGameKey);
			attempt.setCode("RPYGOGOP");

			mAttempt = new MultiplayerAttempt();
			mAttempt.setAttempt(attempt);
			mAttempt.setMultiplayerGameKey(mGame.getGameKey());
			
			response = webResource.type(Server.CONTENT_TYPE).post(ClientResponse.class, mAttempt);
			
			mGame = response.getEntity(MultiplayerGame.class);
			
			attempt = new Attempt();
			
//			attempt.setGameKey(game.getGameKey());
//			attempt.setCode("CPYGOGOP");
//			
//			response = webResource.type(appType).post(ClientResponse.class, attempt);
//			
//			br = new BufferedReader(new InputStreamReader(response.getEntityInputStream()));
//			
//			System.out.println(br.readLine());
			
			assertEquals("Alex Zimmerman", null);
	}
}
