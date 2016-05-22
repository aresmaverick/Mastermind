package br.com.vanhackathon.axiomzen.mastermind.model;

import static org.junit.Assert.*;

import org.junit.Test;

import br.com.vanhackathon.axiomzen.mastermind.model.MastermindSolver;
import br.com.vanhackathon.axiomzen.mastermind.model.Result;

public class TestMastermindSolver {

	String correctAnswer = "RBPOCBCB";
	String guess = "RRBPPCBC";
	
	@Test
	public void testCorrectResult() {
		MastermindSolver mms = new MastermindSolver(correctAnswer);
		
		Result correctResult = new Result();
		correctResult.setExact(8);
		correctResult.setGuess(correctAnswer.toCharArray());
		correctResult.setNear(0);
		
		assertEquals(correctResult, mms.solve(correctAnswer));
	}

	@Test
	public void testWrongResult() {
		MastermindSolver mms = new MastermindSolver(correctAnswer);
		
		Result correctResult = new Result();
		correctResult.setExact(1);
		correctResult.setGuess(guess.toCharArray());
		correctResult.setNear(5);
		
		assertEquals(correctResult, mms.solve(guess));
	}

	
}
