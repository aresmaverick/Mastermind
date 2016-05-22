package br.com.vanhackathon.axiomzen.mastermind.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.vanhackathon.axiomzen.mastermind.MastermindManager;
import br.com.vanhackathon.axiomzen.mastermind.model.Attempt;
import br.com.vanhackathon.axiomzen.mastermind.model.Game;

@Path("/guess")
public class Guess {

	@POST
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Response guess(Attempt attempt) {
		if (attempt.getCode() == null || attempt.getGameKey() == null) {
			System.out.println("Don´t waste my resources with false attempts!");
			return Response.status(404).entity("Don´t waste my resources with false attempts!").build();
		}
		
		MastermindManager manager = MastermindManager.getManager();
		
		Game game = manager.getGame(attempt.getGameKey());
		
		if (game == null) {
			System.out.println("This Game Key " + attempt.getGameKey() + " doesnt exists!");
			
			return Response.status(404).entity("This Game Key " + attempt.getGameKey() + " doesnt exists!").build();
		} else {
			game.tryAnswer(attempt.getCode().toCharArray());
			
			return Response.status(200).entity(game).build();
		}
	}
}
