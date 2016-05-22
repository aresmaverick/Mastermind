package br.com.vanhackathon.axiomzen.mastermind;

import java.util.HashMap;

import br.com.vanhackathon.axiomzen.mastermind.model.Game;

public class MastermindManager {

	private static MastermindManager manager;
	
	private HashMap<String, Game> mapGames = new HashMap<String, Game>();
	
	private MastermindManager() {
		
	}
	
	public static MastermindManager getManager() {
		if (manager == null) {
			manager = new MastermindManager();
		}
		
		return manager;
	}
	
	public synchronized boolean addGame(Game game) {
		if (mapGames.containsKey(game.getGameKey())) {
			System.out.println("Key Already Inserted, try again!");
			return false;
		} else {
			Game tempGame = mapGames.put(game.getGameKey(), game);
			
			if (tempGame == null) {
				System.out.println("Gratz, this is really a new game! Game Key is " + game.getGameKey());
				return true;
			} else {
				mapGames.put(tempGame.getGameKey(), tempGame);
				System.out.println("I don´t have this game, but a hash collision has occurred! :-(");
				return false;
			}
		}
	}

	public Game getGame(String gameKey) {
		return mapGames.get(gameKey);
	}
}
