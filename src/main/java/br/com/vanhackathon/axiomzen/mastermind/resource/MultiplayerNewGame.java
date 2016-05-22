package br.com.vanhackathon.axiomzen.mastermind.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.vanhackathon.axiomzen.mastermind.MastermindMultiplayerManager;
import br.com.vanhackathon.axiomzen.mastermind.model.Game;
import br.com.vanhackathon.axiomzen.mastermind.model.MultiplayerGame;
import br.com.vanhackathon.axiomzen.mastermind.model.User;

@Path("/multiplayer/new_game")
public class MultiplayerNewGame {

	@POST
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Response createNewMultiplayerGame(User user) {
		
		if (user.getUser() == null) {
			return Response.status(404).entity("No User, No Game!").build();
		}
		
		MastermindMultiplayerManager manager = MastermindMultiplayerManager.getManager();
		
		MultiplayerGame mGame;
		
		Game game;
		
		if (user.getGameKey() == null) {
			mGame = new MultiplayerGame();
			
			while (!manager.addGame(mGame)) {
				mGame = new MultiplayerGame();
			}
			
			System.out.println("Welcome: " + user.getUser() + ". This is yours multiplayer´s game  " + mGame.getGameKey());
		} else {
			mGame = manager.getGame(user.getGameKey());
			
			if (mGame == null) {
				return Response.status(404).entity("Game Key Not Found!").build();
			}
		}
		
		game = new Game(mGame.getSecretCode());
		game.setUser(user);
		
		if(mGame.addGame(game)) {
			System.out.println("Hi " + user.getUser() + " yours Game Key is " + game.getGameKey());
		} else {
			Response.status(404).entity("I´m having some problems creating your game.").build();
		}

		return Response.status(200).entity(mGame.setPlayerGameKey(game)).build();
	}
}
