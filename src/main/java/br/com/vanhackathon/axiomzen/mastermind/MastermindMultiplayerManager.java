package br.com.vanhackathon.axiomzen.mastermind;

import java.util.HashMap;

import br.com.vanhackathon.axiomzen.mastermind.model.MultiplayerGame;

public class MastermindMultiplayerManager {

	private static MastermindMultiplayerManager manager;
	
	private HashMap<String, MultiplayerGame> mapGames = new HashMap<String, MultiplayerGame>();
	
	private MastermindMultiplayerManager() {
		
	}
	
	public static MastermindMultiplayerManager getManager() {
		if (manager == null) {
			manager = new MastermindMultiplayerManager();
		}
		
		return manager;
	}
	
	public synchronized boolean addGame(MultiplayerGame mGame) {
		if (mapGames.containsKey(mGame.getGameKey())) {
			System.out.println("Eu ja tenho essa chave!");
			return false;
		} else {
			MultiplayerGame tempGame = mapGames.put(mGame.getGameKey(), mGame);
			
			if (tempGame == null) {
				System.out.println("Jogo adicionado com sucesso!");
				return true;
			} else {
				mapGames.put(tempGame.getGameKey(), tempGame);
				System.out.println("Eu nao tenho a chave, mas colidi!");
				return false;
			}
		}
	}
	
	public MultiplayerGame getGame(String gameKey) {
		return mapGames.get(gameKey);
	}
}
