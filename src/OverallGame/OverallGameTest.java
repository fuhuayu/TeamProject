package OverallGame;
import static org.junit.Assert.*;

import org.junit.Test;

import Game1.Animal;
import Game1.CrabCatcherGame;
import Game3.Game3;


public class OverallGameTest {

	@Test
	public void testOverallGame() {
		OverallGame testGame = new OverallGame() ;
		assertEquals(testGame.getOverallScore(), 0);
		boolean [] testGamesComplete = {false, false, false};
		assertEquals(testGame.getGamesComplete(), testGamesComplete);
		assertEquals(testGame.getTimeInIdle(), 0); //OK Because set at 0 initially
	}

	@Test
	public void testUpdate() {
		OverallGame testOverallGame = new OverallGame() ;
		Game3 testGame = new Game3(testOverallGame) ;
		testGame.setTime(0);
		testGame.addScore(100);
		testGame.update();
		assertEquals(testOverallGame.getOverallScore(), 100) ;
		boolean [] testGamesComplete = {false, false, true};
		assertEquals(testOverallGame.getGamesComplete(), testGamesComplete);
		testOverallGame.setTimeInIdle(180);
		testOverallGame.setIsGameRunning(false);
		testOverallGame.update();
		testGamesComplete[2] = false ;
		assertEquals(testOverallGame.getGamesComplete(), testGamesComplete);
		
		//Test crab game: does end game update score?
		Animal[] animals = new Animal[3];
		CrabCatcherGame crabGame = new CrabCatcherGame(0, 0, animals, 0, 3, 10, null, 3, new OverallGame());
		crabGame.setScore(150);
		crabGame.endGame();
		assertEquals(testOverallGame.getOverallScore(), 250);
		boolean [] test2GamesComplete = {false, true, true};
		assertEquals(testOverallGame.getGamesComplete(), testGamesComplete);
		
		
	}

}
