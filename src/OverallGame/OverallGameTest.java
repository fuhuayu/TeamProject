package OverallGame;
import static org.junit.Assert.*;

import java.awt.event.MouseEvent;

import org.junit.Test;

import Game1.Animal;
import Game1.CrabCatcherGame;
import Game3.Game3;

/**
 * @author Brendan, Danielle, David, Huayu and Zhanglong
 * @version 0.1
 * @since   2015-11-02
 * A Class for testing the Overall Game Tests
 */
public class OverallGameTest {

	/**
	 * Tests that the game can properly update to run games and exit them to add
	 * scores and return to the main screen
	 */
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
		testOverallGame.setGameRunning(false);
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
		assertEquals(testOverallGame.getGamesComplete(), test2GamesComplete);
		
		
	}
	/**
	 * Test 1: Tests if the game starts another game if pressed on the game 1 icon
	 * Test 2: Tests if the game stops when the exit button is pressed
	 */
	public void testOnClick() {
		OverallGame testOverallGame = new OverallGame() ;
		MouseEvent e = new MouseEvent(null, 0, 0, 0, 1, 1, 1, false); //location of click is temporary
		testOverallGame.onClick(e);
		assertEquals(testOverallGame.getGameRunning(), false);
		assertFalse(testOverallGame.getGame1() == null);
		MouseEvent e2 = new MouseEvent(null, 0, 0, 0, 1, 2, 1, false); //location of click is temporary
		testOverallGame.onClick(e2);
		assertEquals(testOverallGame.getGameRunning(), false);
	}
	

}
