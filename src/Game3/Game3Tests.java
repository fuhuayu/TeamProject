package Game3;
import static org.junit.Assert.*;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.MouseEvent;
import java.util.ArrayList;


import org.junit.Test;

import OverallGame.OverallGame;
import OverallGame.gameWindow;

/**
 * @author Brendan, David, Danielle, Zhanglong and Huayu
 * @version 0.1
 * @since   2015-11-02
 * Handles the tests for game 3
 */
public class Game3Tests {
	private static Robot rob = null;
	/**
	 * Tests that the game properly causes the mussels to grow per update
	 */
	@Test
	public void testUpdate() {
		
	}

	/**
	 * this will test for both the addition of plants
	 * and harvesting of mussels
	 */
	@Test
	public void testOnClick() {
		
	}

	/**
	 * Test 1: Tests the addition of a plant to a row and column
	 * Test 2: Tests the addition of a plant to a different row and column
	 */
	@Test
	public void testAddPlant() {
		
	}
	

	/**
	 * Test 1: Tests the addition of runoff to a row and column
	 */
	@Test
	public void testAddRunoff() {
		
		
	}
	
	/**
	 * Test 1: Tests the addition of runoff to a row and column
	 * Followed by checking to see if the runoff moves a column to the left after the move function
	 */
	@Test
	public void testMoveRunoff() {
		
	}

	/**
	 * Tests the fighting of plants and runoff 3 times
	 * First time tests the result of one fight
	 * Second time tests the death of the runoff
	 * Third time tests the death of the plant
	 */
	@Test
	public	void	testBattle()	{
		
	}
	
	/**
	 * 
	 * 
	 */
	@Test
	public	void	testAddMussel()	{
		OverallGame testGame	=	new	OverallGame();
		testGame.setGameWindow(new	gameWindow(testGame));

		Game3		testGame3	=	new	Game3(testGame);
		
	}
	
	/**
	 * First Test:	Make sure initialization has worked
	 * Second Test: Tests adding the money and adding coins to the images
	 * Third Test:	Tests the addition of an additional coin, which changes
	 * 				the format from a list to a stack with a number
	 * Fourth Test:	Tests taking money away and thus changing the format back
	 * 				into the list of coins
	 */
	@Test
	public	void	testAddMoney()	{
		OverallGame testGame	=	new	OverallGame();
		testGame.setGameWindow(new	gameWindow(testGame));

		Game3		testGame3	=	new	Game3(testGame);
		assertEquals(testGame3.getMoney()/100,testGame3.getCoins().size());
		testGame3.setMoney(900);
		testGame3.addMoney(100);
		assertEquals(1000,testGame3.getMoney());
		assertEquals(10,testGame3.getCoins().size());
		testGame3.addMoney(100);
		assertEquals(1,testGame3.getCoins().size());
		assertEquals(" X11",testGame3.getTotalCoin().getText());
		testGame3.addMoney(-200);
		assertEquals(9,testGame3.getCoins().size());
	}
	
	/**
	 * First Assert Equals are there to test that the values have 
	 * initialized properly and no false positives can be read
	 * 
	 * Second set checks to see if endGame() correctly affected 
	 * the overall game
	 */
	@Test
	public	void	testEndGame()	{
		OverallGame testGame	=	new	OverallGame();
		testGame.setGameWindow(new	gameWindow(testGame));

		Game3		testGame3	=	new	Game3(testGame);
		assertEquals(0,testGame.getOverallScore());
		assertEquals(false,testGame.getGamesComplete()[2]);
		testGame3.setScore(-500);	//Don't want the game to affect the actual high scores
		//testGame3.endGame("testHighScores.txt");
		assertEquals(-500,testGame.getOverallScore());
		assertEquals(true,testGame.getGamesComplete()[2]);
		
	}
}
