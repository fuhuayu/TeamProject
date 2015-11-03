package OverallGame;
import static org.junit.Assert.*;

import org.junit.Test;

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
		testOverallGame.setTimeInIdle(180) ;
		testOverallGame.setIsGameRunning(false);
		testOverallGame.update();
		testGamesComplete[2] = false ;
		assertEquals(testOverallGame.getGamesComplete(), testGamesComplete);
		
		
	}

}
