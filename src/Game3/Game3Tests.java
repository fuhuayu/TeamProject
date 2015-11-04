package Game3;
import static org.junit.Assert.*;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JTree;

import org.junit.Test;

import OverallGame.OverallGame;

/**
 * @author Brendan, David, Danielle, Zhanglong and Huayu
 * @version 0.1
 * @since   2015-11-02
 * Handles the tests for game 3
 */
public class Game3Tests {

	/**
	 * Tests that the game properly causes the runoff to move and the score and money to update
	 */
	@Test
	public void testUpdate() {
		OverallGame testBigGame = new OverallGame();
		Game3 testGame = new Game3(testBigGame) ;
		testGame.addPlant(1, 1, "Grass");
		testGame.addRunoff(1, 3);
		testGame.setTime(5);
		testGame.update(); //runoff will move next to plant
		testGame.setGameRunning(true);
		testGame.setGameOver(true);
		//Score and Money will Go up by 5 every 5 seconds
		assertEquals(testGame.getScore(), 5);
		assertEquals(testGame.getMoney(), 105);
		assertEquals(testGame.getRunoff().get(0).getCol(), 2);
		testGame.setTime(5);
		testGame.update(); //runoff will fight the plant
		testGame.setGameRunning(true);
		testGame.setGameOver(true);
		assertEquals(testGame.getScore(), 10);
		assertEquals(testGame.getMoney(), 110);
		assertEquals(testGame.getRunoff().get(0).getHealth(), 7);
		assertEquals(testGame.getPlants().get(0).getHealth(), 8);
		assertEquals(testGame.getRunoff().get(0).getCol(), 2);
	}

	@Test
	public void testOnClick() {
		OverallGame testBigGame = new OverallGame();
		Game3 testGame = new Game3(testBigGame) ;
		JTree source = new JTree();
		MouseEvent e = new MouseEvent(source, 1, 0, 0, 1, 1, 0, false);
		testGame.onClick(e) ;
		Plant testPlant = new Plant(1,1,"Grass")  ; 
		ArrayList<Plant> testPlants = new ArrayList<Plant>();
		testPlants.add(testPlant);
		assertEquals(testGame.getPlants(), testPlants);
		//Simulate the addition of another plant
		MouseEvent e2 = new MouseEvent(source, 1, 0, 0, 2, 1, 0, false);
		Plant testPlant2 = new Plant(2,1,"Grass")  ; 
		testPlants.add(testPlant2);
		testGame.onClick(e2) ;
		assertEquals(testGame.getPlants(), testPlants);
	}
	
	/**
	 * 4 main tests
	 * Test 1: Tests to see that planting plants works
	 * Test 2: Tests planting at another plant
	 * Test 3: Tests the player clicking on a mussel for money
	 * Test 4: Tests the plater exiting the game
	 */
	public void testonClick() {
		OverallGame testBigGame = new OverallGame();
		Game3 testGame = new Game3(testBigGame) ;
		MouseEvent e = new MouseEvent(null, 0, 0, 0, 1, 1, 1, false);
		testGame.onClick(e) ; //Simulate player input of placing grass at 1,1
		Plant testPlant = new Plant(1,1,"Grass")  ; 
		assertEquals(testGame.getPlants().get(0), testPlant);
		//Simulate the addition of another plant
		MouseEvent e2 = new MouseEvent(null, 0, 0, 0, 2, 1, 1, false);
		testGame.onClick(e2) ;
		Plant testPlant2 = new Plant(2,1,"Grass") ;
		ArrayList<Plant> testPlants = new ArrayList<Plant>() ;
		testPlants.add(testPlant) ; testPlants.add(testPlant2);
		assertEquals(testGame.getPlants(),testPlants);
		//Simulate the player clicking on a mussel for 50 money
		MouseEvent e3 = new MouseEvent(null, 1, 0, 0, 10, 10, 0, false);
		testGame.getMussels().get(0).setXLoc(10);
		testGame.getMussels().get(0).setYLoc(10);
		testGame.getMussels().get(0).setStage(3);
		testGame.onClick(e3) ;
		assertEquals(testGame.getScore(), 150 );
		//Test Exiting the game
		MouseEvent e4 = new MouseEvent(null, 0, 0, 0, 99, 99, 1, false);
		testGame.onClick(e4) ;
		assertTrue( testGame.getGameEnded() );
	}

	/**
	 * Test 1: Tests the addition of a plant to a row and column
	 * Test 2: Tests the addition of a plant to a different row and column
	 */
	@Test
	public void testAddPlant() {
		OverallGame testBigGame = new OverallGame();
		Game3 testGame = new Game3(testBigGame) ;
		testGame.addPlant(1,1,"Grass") ;
		ArrayList<Plant> testPlants = new ArrayList<Plant>(1);
		testPlants.add(new Plant(1,1,"Grass"));
		assertEquals(testGame.getPlants(), testPlants);
		testGame.addPlant(1,2,"Grass") ;
		testPlants.add(new Plant(1,2,"Grass"));
		assertEquals(testGame.getPlants(), testPlants);
	}

	/**
	 * Test 1: Tests the addition of runoff to a row and column
	 * Test 2: Tests the addition of runoff to a different row and column
	 */
	@Test
	public void testAddRunoff() {
		OverallGame testBigGame = new OverallGame();
		Game3 testGame = new Game3(testBigGame) ;
		testGame.addRunoff(1,1) ;
		ArrayList<Runoff> testRunoff = new ArrayList<Runoff>(1);
		testRunoff.add(new Runoff(1,1));
		assertEquals(testGame.getRunoff(), testRunoff);
		testGame.addRunoff(1,1) ;
		testRunoff.add(new Runoff(2,1));
		assertEquals(testGame.getRunoff(), testRunoff);
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
		assertEquals(testGame.getRunoff(), new ArrayList<Runoff>(0)) ;
		//Now we will kill the plant
		testGame.addRunoff(1, 2);
		testGame.battle(testGame.getPlants().get(0), testGame.getRunoff().get(0));
		assertEquals(testGame.getPlants(), new ArrayList<Plant>(0)) ;
		assertEquals(testGame.getRunoff().get(0).getHealth(), 7)	;
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
		assertEquals(testBigGame.getGameRunning(), true);
	}
}
