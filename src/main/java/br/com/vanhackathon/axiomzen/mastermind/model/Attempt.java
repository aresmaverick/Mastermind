package br.com.vanhackathon.axiomzen.mastermind.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Attempt {

	@XmlElement(name="code")
	private String code;
	
	@XmlElement(name="game_key")
	private String gameKey;

	public void setCode(String code) {
		this.code = code;
	}
	
	public void setGameKey(String gameKey) {
		this.gameKey = gameKey;
	}
	
	public String getGameKey() {
		return this.gameKey;
	}
	
	public String getCode() {
		return this.code;
	}
}
