package br.com.vanhackathon.axiomzen.mastermind.model;

public class MastermindSolver {

	private static final char SPACE = ' ';
	private char[] correctAnswer;
	
	public MastermindSolver(String correctAnswer) {
		this(correctAnswer.toCharArray());
	}
	
	public MastermindSolver(char[] correctAnswer) {
		this.correctAnswer = correctAnswer.clone();
	}
	
	public Result solve(String guess) {
		return solve(guess.toCharArray());
	}

	public Result solve(char[] guess) {
		Result triedResult = new Result();

		char[] guessArray;
		
		if (isResizeNeeded(guess)) {
			guessArray = resizeArray(guess);
		} else {
			guessArray = guess.clone();
		}
		
		int exact = 0;
		
		for (int i = 0; i < Game.CODE_LENGTH; i++) {
			if (guessArray[i] == correctAnswer[i]) {
				exact++;
				guessArray[i] = SPACE;
				correctAnswer[i] = SPACE;
			}
		}

		int near = 0;
		
		for (int i = 0; i < Game.CODE_LENGTH; i++) {
			if (guessArray[i] != SPACE) {
				if (replaceChar(guessArray[i], correctAnswer)) {
					near++;
				}
			}
		}
		
		triedResult.setGuess(guess);
		triedResult.setExact(exact);
		triedResult.setNear(near);
		
		return triedResult;
	}
	
	private boolean isResizeNeeded(char[] guess) {
		return guess.length != Game.CODE_LENGTH; 
	}

	private char[] resizeArray(char[] guess) {
		char[] newGuess = new char[Game.CODE_LENGTH];
		
		for (int i = 0; i < Game.CODE_LENGTH; i++) {
			if (guess.length <= i) {
				newGuess[i] = SPACE;
			} else {
				newGuess[i] = guess[i];	
			}
		}
		
		return newGuess;
	}
	
	private boolean replaceChar(char c, char[] resultArray) {
		for (int i = 0; i < resultArray.length; i++) {
			if (c == resultArray[i]) {
				resultArray[i] = SPACE;
				return true;
			}
		}
		
		return false;
	}
}
