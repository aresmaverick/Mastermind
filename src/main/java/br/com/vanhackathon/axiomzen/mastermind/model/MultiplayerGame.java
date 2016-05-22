package br.com.vanhackathon.axiomzen.mastermind.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.vanhackathon.axiomzen.mastermind.util.RandomUtil;

@XmlRootElement
public class MultiplayerGame {
	
	Map<String, Game> gameMap = new HashMap<String, Game>();
	
	private char[] secretCode;
	
	@XmlElement(name="game_key")
	private String gameKey;
	
	@XmlElement(name="number_of_players")
	private Integer playersNumber;
	
	@XmlElement(name="player_game_key", nillable=false)
	private String lastPlayerGameKey;
	
	public MultiplayerGame() {
		this.gameKey = String.valueOf(RandomUtil.generateRandom());
		this.secretCode = new Game().generateSecretCode(Long.parseLong(this.gameKey));
	}
	
	public boolean addGame(Game game) {
		if (gameMap.put(game.getGameKey(), game) == null) {
			playersNumber = gameMap.size();
			
			return true;
		} else {
			return false;
		}
	}
	
	public MultiplayerGame setPlayerGameKey(Game game) {
		this.lastPlayerGameKey = game.getGameKey();
		
		return this;
	}
	
	public Game getGame(String gameCode) {
		return gameMap.get(gameCode);
	}
	
	public String getGameKey() {
		return this.gameKey;
	}
	
	public String getLastGameKey() {
		return this.lastPlayerGameKey;
	}

	public char[] getSecretCode() {
		return secretCode;
	}
	
	public Integer getNumberPlayer() {
		return playersNumber;
	}
	
	public boolean canIPlay(String gameCode) {
		Long minPlays = Long.MAX_VALUE;
		
		for (Game game : gameMap.values()) {
			if (minPlays > game.getNumGuesses()) {
				minPlays = game.getNumGuesses();
			}
		}
		
		if (gameMap.get(gameCode).getNumGuesses() == minPlays) {
			return true;
		} else {
			return false;
		}
	}
	
	public Collection<Game> getGames() {
		return gameMap.values();
	}
}
