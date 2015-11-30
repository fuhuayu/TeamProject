package Game3;
import static org.junit.Assert.*;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.MouseEvent;
import java.util.ArrayList;


import org.junit.Test;

import OverallGame.OverallGame;

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
		OverallGame testBigGame = new OverallGame();
		testBigGame.setGamesRunning(3);
		testBigGame.setGame3(new Game3(testBigGame));
		testBigGame.getGame3().getTimer().stop();
		assertEquals(0, testBigGame.getGame3().getMussels().get(0).getStage());
		testBigGame.getGame3().update();
		testBigGame.getGame3().update();
		testBigGame.getGame3().update();
		testBigGame.getGame3().update();
		testBigGame.getGame3().update();
		assertEquals(0, testBigGame.getGame3().getMussels().get(0).getStage());
	}

	/**
	 * this will test for both the addition of plants
	 * and harvesting of mussels
	 */
	@Test
	public void testOnClick() {
		try {
			rob = new Robot();
		}
		catch (AWTException e) {
			e.printStackTrace();
		}
		OverallGame testBigGame = new OverallGame();
		testBigGame.setGamesRunning(3);
		testBigGame.setGame3(new Game3(testBigGame));
		testBigGame.getGame3().getTimer().stop();
		testBigGame.getGame3().addMoney(100);
		//Simulate placing a plant at 1,1
		rob.mouseMove(500, 250);
		rob.mousePress(MouseEvent.BUTTON1_MASK);
		rob.mouseRelease(MouseEvent.BUTTON1_MASK);
		Plant testPlant = new Plant(1,1,"Grass")  ; 
		ArrayList<Plant> testPlants = new ArrayList<Plant>();
		testPlants.add(testPlant);
		assertEquals(testBigGame.getGame3().getPlants(), testPlants);
		//Simulate the addition of another plant at 2,1
		rob.mouseMove(500, 480);
		rob.mousePress(MouseEvent.BUTTON1_MASK);
		rob.mouseRelease(MouseEvent.BUTTON1_MASK);
		Plant testPlant2 = new Plant(2,1,"Grass")  ; 
		testPlants.add(testPlant2);
		assertEquals(testBigGame.getGame3().getPlants(), testPlants);
		testBigGame.getGame3().getMussels().get(0).setStage(100);
		rob.mouseMove(testBigGame.getGame3().getMussels().get(0).getXLoc()+20, testBigGame.getGame3().getMussels().get(0).getYLoc()+20);
		rob.mousePress(MouseEvent.BUTTON1_MASK);
		rob.mouseRelease(MouseEvent.BUTTON1_MASK);
		assertEquals(100, testBigGame.getGame3().getMoney());
	}

	/**
	 * Test 1: Tests the addition of a plant to a row and column
	 * Test 2: Tests the addition of a plant to a different row and column
	 */
	@Test
	public void testAddPlant() {
		try {
			rob = new Robot();
		}
		catch (AWTException e) {
			e.printStackTrace();
		}
		OverallGame testBigGame = new OverallGame();
		testBigGame.setGamesRunning(3);
		testBigGame.setGame3(new Game3(testBigGame));
		testBigGame.getGame3().getTimer().stop();
		//Simulate placing a plant at 1,1
		rob.mouseMove(500, 250);
		rob.mousePress(MouseEvent.BUTTON1_MASK);
		rob.mouseRelease(MouseEvent.BUTTON1_MASK);
		Plant testPlant = new Plant(1,1,"Grass")  ; 
		ArrayList<Plant> testPlants = new ArrayList<Plant>();
		testPlants.add(testPlant);
		assertEquals(testBigGame.getGame3().getPlants(), testPlants);
		//Simulate the addition of another plant at 2,1
		rob.mouseMove(500, 480);
		rob.mousePress(MouseEvent.BUTTON1_MASK);
		rob.mouseRelease(MouseEvent.BUTTON1_MASK);
		Plant testPlant2 = new Plant(2,1,"Grass")  ; 
		testPlants.add(testPlant2);
		assertEquals(testBigGame.getGame3().getPlants(), testPlants);
	}
	

	/**
	 * Test 1: Tests the addition of runoff to a row and column
	 */
	@Test
	public void testAddRunoff() {
		OverallGame testBigGame = new OverallGame();
		testBigGame.setGamesRunning(3);
		testBigGame.setGame3(new Game3(testBigGame));
		testBigGame.getGame3().getTimer().stop();
		testBigGame.getGame3().addRunoff() ;
		boolean foundRunoff = false ;
		for(Runoff current : testBigGame.getGame3().getEnemies()) {
			foundRunoff = true;
		}
		assertEquals(true, foundRunoff);
		
	}
	
	/**
	 * Test 1: Tests the addition of runoff to a row and column
	 * Followed by checking to see if the runoff moves a column to the left after the move function
	 */
	@Test
	public void testMoveRunoff() {
		OverallGame testBigGame = new OverallGame();
		testBigGame.setGamesRunning(3);
		testBigGame.setGame3(new Game3(testBigGame));
		testBigGame.getGame3().getTimer().stop();
		testBigGame.getGame3().addRunoff() ;
		boolean foundRunoff = false ;
		for(Runoff current : testBigGame.getGame3().getEnemies()) {
			if (current instanceof Runoff) {
				foundRunoff = true;
				((Runoff) current).setTicksSinceMoved(31);
			}
		}
		assertEquals(true, foundRunoff);
		testBigGame.getGame3().moveRunoff();
		Runoff movedRunoff = null;
		for(Runoff current : testBigGame.getGame3().getEnemies()) {
			if (current instanceof Runoff) {
				foundRunoff = true;
				movedRunoff = (Runoff) current;
			}
		}
		assertEquals(5,movedRunoff.getFront());
		
	}

	/**
	 * Tests the fighting of plants and runoff 3 times
	 * First time tests the result of one fight
	 * Second time tests the death of the runoff
	 * Third time tests the death of the plant
	 */
	@Test
	public void testBattle() {
		OverallGame testBigGame = new OverallGame();
		Game3 testGame = new Game3(testBigGame) ;
		Plant testPlant = new Plant(1,1,"Grass") ;
		Runoff testRunoff = new Runoff(1, 2);
		testGame.battle(testPlant, testRunoff);
		//We will check the health of both afterwards
		assertEquals(testPlant.getHealth(), 8) ;
		assertEquals(testRunoff.getHealth(), 7) ;
		//Now we will kill the runoff
		testGame.battle(testPlant, testRunoff);
		testGame.battle(testPlant, testRunoff);
		testGame.battle(testPlant, testRunoff);
		assertEquals(testGame.getPlants().get(0).getHealth(), 2) ;
		assertEquals(testGame.getEnemies(), new ArrayList<Runoff>(0)) ;
		//Now we will kill the plant
		testGame.addRunoff();
		testGame.battle(testGame.getPlants().get(0), testGame.getEnemies().get(0));
		assertEquals(testGame.getPlants(), new ArrayList<Plant>(0)) ;
		assertEquals(testGame.getEnemies().get(0).getHealth(), 7)	;
	}
	
	/**
	 * Test 1: Tests the addition of runoff to a row and column
	 * Followed by checking to see if the runoff moves a column to the left after the move function
	 */
	@Test
	public void testAddMussel() {
		OverallGame testBigGame = new OverallGame();
		testBigGame.setGamesRunning(3);
		testBigGame.setGame3(new Game3(testBigGame));
		testBigGame.getGame3().getTimer().stop();
		testBigGame.getGame3().addMussel();
		assertEquals(5, testBigGame.getGame3().getMussels().size());
		int xloc = testBigGame.getGame3().getMussels().get(4).getXLoc();
		int yloc = testBigGame.getGame3().getMussels().get(4).getYLoc();
		Rectangle newMussel = new Rectangle(xloc, yloc, 132, 80);
		for (int i = 0 ; i < testBigGame.getGame3().getMussels().size() - 2 ; i++) {
			Mussel current = testBigGame.getGame3().getMussels().get(i);
			Rectangle curMussel = new Rectangle(current.getXLoc(), current.getYLoc(), 132, 80);
			assertFalse(newMussel.intersects(curMussel));
		}
		
	}
	
	/**
	 * Tests the passing of the score to the overall game
	 * Tests that the function alerts the big game that this game was completed
	 * Tests that the function makes the big game start running
	 */
	@Test
	public void testEndGame() {
		OverallGame testBigGame = new OverallGame();
		Game3 testGame = new Game3(testBigGame) ;
		testGame.addScore(100);
		testGame.endGame();
		boolean [] winLastGame = {false, false, true};
		assertEquals(testBigGame.getGamesComplete(),  winLastGame);
		assertEquals(testBigGame.getOverallScore(), 100);
		assertEquals(testBigGame.getGamesRunning(), 0);
	}
}
