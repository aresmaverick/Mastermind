package br.com.vanhackathon.axiomzen.mastermind.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.vanhackathon.axiomzen.mastermind.MastermindManager;
import br.com.vanhackathon.axiomzen.mastermind.model.Game;
import br.com.vanhackathon.axiomzen.mastermind.model.User;

@Path("/new_game")
public class NewGame {

	@POST
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Response createNewGame(User user) {
		
		if (user.getUser() == null) {
			return Response.status(404).entity("No User, No Game!").build();
		}
		
		Game game = new Game();
		
		MastermindManager manager = MastermindManager.getManager();
		
		while (!manager.addGame(game)) {
			game = new Game();
		}
		
		System.out.println("Welcome: " + user.getUser() + " yours Game Key is " + game.getGameKey());
		game.setUser(user);
		
		return Response.status(200).entity(game).build();
	}
}
