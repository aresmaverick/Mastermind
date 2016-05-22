package br.com.vanhackathon.axiomzen.mastermind.resource;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import br.com.vanhackathon.axiomzen.mastermind.model.Attempt;
import br.com.vanhackathon.axiomzen.mastermind.model.Game;
import br.com.vanhackathon.axiomzen.mastermind.model.User;
import br.com.vanhackathon.axiomzen.mastermind.model.colors.Colors;
import br.com.vanhackathon.axiomzen.mastermind.webserver.Server;

public class TestGuess {

	private static final String USER_NAME = "Frederico de Oliveira";

	Server server;
	
	User user = new User();
	
	@Before
	public void init() {
		server = new Server();
		
		user.setUser(USER_NAME);
	}
	
	@After
	public void shutdown() {
		server.shutdown();
	}
	
	@Test
	public void test() throws IOException {
			Client client = server.getClient();
			
			WebResource webResource = client.resource(Server.BASE_URI + Server.NEW_GAME);
			
			ClientResponse response = webResource.type(Server.CONTENT_TYPE).post(ClientResponse.class, user);
			
			Game game = response.getEntity(Game.class);
			
			webResource = client.resource(Server.BASE_URI + Server.GUESS);
			
			Attempt attempt = new Attempt();
			
			attempt.setGameKey(game.getGameKey());
			attempt.setCode("RPYGOGOP");
			
			response = webResource.type(Server.CONTENT_TYPE).post(ClientResponse.class, attempt);
			
			BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntityInputStream()));
			
			System.out.println(br.readLine());
			
			attempt = new Attempt();
			
			attempt.setGameKey(game.getGameKey());
			attempt.setCode("CPYGOGOP");
			
			response = webResource.type(Server.CONTENT_TYPE).post(ClientResponse.class, attempt);
			
			br = new BufferedReader(new InputStreamReader(response.getEntityInputStream()));
			
			System.out.println(br.readLine());
			
			assertEquals("Alex Zimmerman", null);
	}
	
//	@Test
//	public void testSmartForceAccept() throws IOException {
//			Client client = server.getClient();
//			
//			WebResource webResource = client.resource(Server.BASE_URI + "new_game");
//			
//			ClientResponse response = webResource.type(Server.CONTENT_TYPE).post(ClientResponse.class, user);
//			
//			Game game = response.getEntity(Game.class);
//			
//			System.out.println("Prepare for brute force!!!!!");
//
//			Attempt attempt = new Attempt();
//			attempt.setGameKey(game.getGameKey());
//			
//			int initial = 0;
//			
//			while (!game.isSolved()) {
//				webResource = client.resource(Server.BASE_URI + Server.GUESS);
//		
//				initial++;
//				
//				if (initial == 11) {
//					attempt.setCode(String.valueOf(game.getSecretCode()));
//				} else {
//					attempt.setCode(almostRandomGeneratedCode(initial));	
//				}
//
//				response = webResource.type(Server.CONTENT_TYPE).post(ClientResponse.class, attempt);
//
//				game = response.getEntity(Game.class);
//			}
//			
//			System.out.println("Mission Accomplished!!! You toke:" + game.getTotalTime() + ". Congratz " + game.getUserName());
//			
//			assertEquals("Alex Zimmerman", null);
//	}
	
	private String almostRandomGeneratedCode(int code) {
		int MAX_BIN = 077777777;
		
		if (code > MAX_BIN) {
			return "";
		}
		
		String temp = Integer.toOctalString(code);
		String almostRandonCode = "RRRRRRRR"; 

		for (int i = 0; i < temp.length(); i++) {
			Colors color = Colors.values()[Integer.parseInt(temp.substring(i, i+1))];
			almostRandonCode = almostRandonCode.substring(0, i) + color.getColor() + almostRandonCode.substring(i+1);
		}
		
		return almostRandonCode;

	}
}
