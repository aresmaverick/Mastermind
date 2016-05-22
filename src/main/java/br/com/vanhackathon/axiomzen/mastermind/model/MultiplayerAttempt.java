package br.com.vanhackathon.axiomzen.mastermind.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class MultiplayerAttempt {

	@XmlElement(name="multiplayer_game_key")
	private String multiplayerGameKey;

	@XmlElement(name="attempt")
	Attempt attempt;
	
	public void setMultiplayerGameKey(String multiplayerGameKey) {
		this.multiplayerGameKey = multiplayerGameKey;
	}

	public String getMultiplayerGameKey() {
		return this.multiplayerGameKey;
	}

	public void setAttempt(Attempt attempt) {
		this.attempt = attempt;
	}
	
	public Attempt getAttempt() {
		return this.attempt;
	}
}
