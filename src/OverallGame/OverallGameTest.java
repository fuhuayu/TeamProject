package OverallGame;
import static org.junit.Assert.*;

import java.awt.event.MouseEvent;

import javax.swing.JTree;

import org.junit.Test;

import Game2.Animal;
import Game2.CrabCatcherGame;
import Game3.Game3;

/**
 * @author Brendan, David, Danielle, Zhanglong and Huayu
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
		CrabCatcherGame crabGame = new CrabCatcherGame(0, 0, animals, 0, 3, 10, null, 3, false, new OverallGame(), null);
		crabGame.setScore(150);
		crabGame.endGame();
		assertEquals(testOverallGame.getOverallScore(), 250);
		boolean [] test2GamesComplete = {false, true, true};
		assertEquals(testOverallGame.getGamesComplete(), testGamesComplete);
		
		
	}
	/**
	 * Test 1: Tests if the game starts another game if pressed on the game 1 icon
	 * Test 2: Tests if the game stops when the exit button is pressed
	 */
	public void testOnClick() {
		OverallGame testOverallGame = new OverallGame() ;
		JTree source = new JTree();
		MouseEvent e = new MouseEvent(source, 0, 0, 0, 1, 1, 1, false); //location of click is temporary
		testOverallGame.onClick(e);
		assertFalse(testOverallGame.getGamesRunning() == 0);
		assertFalse(testOverallGame.getGame1() == null);
		MouseEvent e2 = new MouseEvent(source, 0, 0, 0, 1, 2, 1, false); //location of click is temporary
		testOverallGame.onClick(e2);
		assertFalse(testOverallGame.getGamesRunning() == 0);
	}
	

}
