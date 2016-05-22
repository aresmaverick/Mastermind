package br.com.vanhackathon.axiomzen.mastermind.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.vanhackathon.axiomzen.mastermind.model.colors.Colors;

@XmlRootElement
public class MultiplayerGameResult {

	@XmlElement(name="colors")
	private Colors[] avaiableColors = Colors.values();
	
	@XmlElement(name="code_lenght")
	private Integer code;
	
	@XmlElement(name="futher_instructions")
	private String futherInstructions = "Fim de Jogo!";
	
	@XmlElement(name="num_guesses")
	private Long numGuesses = 0l;

	@XmlElement(name="multiplayer_game_key")
	private String gameKey;

	@XmlElement(name="solved")
	private Boolean solved = true;

	@XmlElement(name="time_taken", nillable=false)
	private Double totalTime;
	
	@XmlElement(name="winner_user")
	private String winnerPlayer;
	
	@XmlElement(name="attempt_history")
	private List<PlayerResults> attemptHistory = new ArrayList<PlayerResults>();

	public Colors[] getAvaiableColors() {
		return avaiableColors;
	}

	public void setAvaiableColors(Colors[] avaiableColors) {
		this.avaiableColors = avaiableColors;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getFutherInstructions() {
		return futherInstructions;
	}

	public void setFutherInstructions(String futherInstructions) {
		this.futherInstructions = futherInstructions;
	}

	public Long getNumGuesses() {
		return numGuesses;
	}

	public void setNumGuesses(Long numGuesses) {
		this.numGuesses = numGuesses;
	}

	public String getGameKey() {
		return gameKey;
	}

	public void setGameKey(String gameKey) {
		this.gameKey = gameKey;
	}

	public Boolean getSolved() {
		return solved;
	}

	public void setSolved(Boolean solved) {
		this.solved = solved;
	}

	public Double getTotalTime() {
		return totalTime;
	}

	public void setTotalTime(Double totalTime) {
		this.totalTime = totalTime;
	}

	public String getWinnerPlayer() {
		return winnerPlayer;
	}

	public void setWinnerPlayer(String winnerPlayer) {
		this.winnerPlayer = winnerPlayer;
	}

	public List<PlayerResults> getAttemptHistory() {
		return attemptHistory;
	}

	public void setAttemptHistory(List<PlayerResults> attemptHistory) {
		this.attemptHistory = attemptHistory;
	} 
}
