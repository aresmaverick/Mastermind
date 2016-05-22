package br.com.vanhackathon.axiomzen.mastermind.model;

import javax.xml.bind.annotation.XmlElement;

public class User {

	private String user;
	
	@XmlElement(name="game_key", nillable=false)
	private String gameKey;
	
	public String getUser() {
		return this.user;
	}
	
	public void setUser(String userName) {
		this.user = userName;
	}
	
	public String getGameKey() {
		return this.gameKey;
	}
	
	public void setGameKey(String gameKey) {
		this.gameKey = gameKey;
	}
}
