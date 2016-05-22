package br.com.vanhackathon.axiomzen.mastermind.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import br.com.vanhackathon.axiomzen.mastermind.model.colors.Colors;
import br.com.vanhackathon.axiomzen.mastermind.util.DateUtil;
import br.com.vanhackathon.axiomzen.mastermind.util.RandomUtil;

@XmlRootElement
public class Game {

	@XmlElement(name="colors")
	private Colors[] avaiableColors = Colors.values();
	
	@XmlTransient
	public static final Integer CODE_LENGTH = 8;

	@XmlElement(name="code_lenght")
	public final Integer code = CODE_LENGTH;
	
	@XmlElement(name="futher_instructions", nillable=false)
	private String futherInstructions;
	
	@XmlElement(name="num_guesses")
	private Long numGuesses = 0l;
	
	@XmlTransient
	private String futherInstructionsText = "Solve the challenge to see this!";
	
	@XmlElement(name="game_key")
	private String gameKey;
	
	@XmlTransient
//	@XmlElement(name="secret_code")
	private char[] secretCode;
	
	@XmlElement(name="solved")
	private Boolean solved = false;
	
	@XmlElement(name="past_results")
	private List<Result> pastResults = new ArrayList<Result>();
	
	@XmlElement(name="result_exact")
	private Integer lastResultExact;
	
	@XmlElement(name="result_near")
	private Integer lastResultNear;
	
	@XmlElement(name="time_taken", nillable=false)
	private Double totalTime;
	
	@XmlTransient
	private Long startTime;
	
	@XmlTransient
	private Long lastTime;
	
	@XmlElement(name="user")
	private User winnerPlayer;
	
	@XmlTransient
	private User attemptPlayer;
	
	public Game() {
		this.gameKey = String.valueOf(RandomUtil.generateRandom());
		this.secretCode = generateSecretCode(Long.parseLong(this.gameKey));
		this.startTime = DateUtil.getActualTimeInMillis();
	}

	public Game(char[] secretCode) {
		this.gameKey = String.valueOf(RandomUtil.generateRandom());
		this.secretCode = secretCode.clone();
		this.startTime = DateUtil.getActualTimeInMillis();
	}
	
	public void setUser(User user) {
		this.attemptPlayer = user;
	}
	
	public String getUserName() {
		return attemptPlayer.getUser();
	}
	
	public char[] generateSecretCode(Long gameKey) {
		char[] generatedSecretCode = new char[CODE_LENGTH];
		
		Random random = new Random(gameKey);
		
		for (int i = 0; i < CODE_LENGTH; i++) {
			int colorCode = random.nextInt(avaiableColors.length);
			
			generatedSecretCode[i] = avaiableColors[colorCode].getColor();
		}
		
		return generatedSecretCode;
	}
	
	public String getGameKey() {
		return gameKey;
	}
	
	public char[] getSecretCode() {
		return this.secretCode;
	}
	
	public boolean isSolved() {
		futherInstructions = futherInstructionsText;
		
		return solved;
	}
	
	public Result tryAnswer(char[] guess) {
		MastermindSolver ms = new MastermindSolver(this.getSecretCode());
		Result partialResult = ms.solve(guess);
		
		addResult(partialResult);
		increaseNumberGuesses();

		lastResultExact = partialResult.getExact();
		lastResultNear = partialResult.getNear();
		
		if (partialResult.getExact() == Game.CODE_LENGTH) {
			setSolved();
		}
		
		return partialResult;
	}
	
	private void increaseNumberGuesses() {
		numGuesses = (long) pastResults.size();
	}
	
	private void setSolved() {
		lastTime = DateUtil.getActualTimeInMillis();
		totalTime =  ((double)(lastTime - startTime) / 1000);
		winnerPlayer = attemptPlayer;
		futherInstructions = futherInstructionsText;
		solved = true;
	}
	
	private boolean addResult(Result result) {
		return this.pastResults.add(result);
	}

	public Double getTotalTime() {
		return totalTime;
	}
	
	public Long getNumGuesses() {
		return this.numGuesses;
	}
	
	public List<Result> getPastResults() {
		return this.pastResults;
	}
}
