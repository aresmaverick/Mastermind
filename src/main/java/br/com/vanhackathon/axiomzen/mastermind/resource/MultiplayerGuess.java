package br.com.vanhackathon.axiomzen.mastermind.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.vanhackathon.axiomzen.mastermind.MastermindMultiplayerManager;
import br.com.vanhackathon.axiomzen.mastermind.model.Attempt;
import br.com.vanhackathon.axiomzen.mastermind.model.Game;
import br.com.vanhackathon.axiomzen.mastermind.model.MultiplayerAttempt;
import br.com.vanhackathon.axiomzen.mastermind.model.MultiplayerGame;
import br.com.vanhackathon.axiomzen.mastermind.model.MultiplayerGameResult;
import br.com.vanhackathon.axiomzen.mastermind.model.PlayerResults;

@Path("/multiplayer/guess")
public class MultiplayerGuess {

	@POST
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Response guess(MultiplayerAttempt attempt) {
		if (attempt.getMultiplayerGameKey() == null || 
				attempt.getAttempt() == null || 
				attempt.getAttempt().getCode() == null ||
				attempt.getAttempt().getGameKey() == null) {
			return Response.status(404).entity("Don´t waste my resources with false attempts!").build();
		}
		
		MastermindMultiplayerManager manager = MastermindMultiplayerManager.getManager();
		
		MultiplayerGame mGame = manager.getGame(attempt.getMultiplayerGameKey());
		
		if (mGame == null) {
			System.out.println("This Game Key " + attempt.getMultiplayerGameKey() + " doesnt exists!");
			
			return Response.status(404).entity("This Game Key " + attempt.getMultiplayerGameKey() + " doesnt exists!").build();

		} else {
			Attempt singlePlayerAttempt = attempt.getAttempt();
			
			while (!mGame.canIPlay(singlePlayerAttempt.getGameKey())) {
				try {
					Thread.sleep(1);
					
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			Game game = mGame.getGame(singlePlayerAttempt.getGameKey());
			game.tryAnswer(singlePlayerAttempt.getCode().toCharArray());
			
			if (game.isSolved()) {
				MultiplayerGameResult mgr = new MultiplayerGameResult();
				
				mgr.setCode(Game.CODE_LENGTH);
				mgr.setNumGuesses(game.getNumGuesses());
				mgr.setGameKey(mGame.getGameKey());
				mgr.setTotalTime(game.getTotalTime());
				mgr.setWinnerPlayer(game.getUserName());
				
				for (Game actualGame : mGame.getGames()) {
					PlayerResults pr = new PlayerResults();
					pr.setUser(actualGame.getUserName());
					pr.setResults(actualGame.getPastResults());
					mgr.getAttemptHistory().add(pr);
				}
				
				return Response.status(200).entity(mgr).build();
			} else {
				return Response.status(200).entity(game).build();
			}
		}
	}
}
