package br.com.vanhackathon.axiomzen.mastermind.model;

import static org.junit.Assert.*;

import org.junit.Test;

import br.com.vanhackathon.axiomzen.mastermind.model.Game;

public class TestGame {

	@Test
	public void testGenerateGameKey() {
		Game game1 = new Game();
		Game game2 = new Game();
		
		System.out.println(game1.getGameKey());
		System.out.println(game2.getGameKey());
		
		assertFalse(game1.getGameKey() == game2.getGameKey());
	}
	
	@Test
	public void testGenerateSecretCode() {
		Game game1 = new Game();
		Game game2 = new Game();

		System.out.println(game1.getGameKey());
		System.out.println(game1.getSecretCode());

		System.out.println(game2.getGameKey());
		System.out.println(game2.getSecretCode());
		
		assertNotSame(game1.getSecretCode(), game2.getSecretCode());
	}
}
