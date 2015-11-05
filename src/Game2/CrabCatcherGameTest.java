package Game2;
import static org.junit.Assert.*;

import java.awt.event.MouseEvent;

import org.junit.Test;

import OverallGame.OverallGame;


/**
 * @author Dwegrzyn
 *
 */
public class CrabCatcherGameTest {	
	/**
	 * tests if tick-dependent conditions are updated in onTick()
	 */
	
	
	@Test
	public void tickTest(){
		OverallGame bigGame = new OverallGame();
		CrabCatcherGame game = new CrabCatcherGame(0, 0, null, 0, 3, 10, null, 5, false, bigGame, null);
		Animal crab = new Animal(0, 0, "crab", 5, 3, true);
		game.addAnimal(crab);
		
		//check if game timer increases
		//check if gameOver is triggered by lives = 0
		//check if crab time remaining decreases
		game.setLives(0);
		game.tickGame();
		
		assertEquals("on tick: game time should increase from 0 to 1", 1, game.getTime());
		assertTrue("on tick: lives = 0 should trigger gameOver", game.isGameOver());
		assertEquals("on tick: crab time remaining should decrease from 3 to 2", 1, crab.getTimeLeftOnScreen());
		
		//check if gameOver is triggered by time = end time
		game.setGameOver(false);
		game.setLives(3);
		game.setTime(9);
		game.tickGame();
		assertTrue("on tick: time = gameLength should trigger gameOver", game.isGameOver());
		
		//check if expired crab is regenerated
		game.setGameOver(false);
		game.setTime(0);
		crab.setTimeLeftOnScreen(1);
		
		int x = crab.getXloc();
		int y = crab.getYloc();
		game.tickGame();
		assertEquals("on tick: expired crab time remaining should reset to 3", 3, crab.getTimeLeftOnScreen());
		assertTrue("on tick: regenerated crab visibility should reset to false", !crab.isVisible());
		assertTrue("crab location should change", (crab.getXloc() != x || crab.getYloc() != y));
	}
	
	/**
	 * tests if game is created correctly
	 */
	@Test
	public void setupTest(){
		Animal[] animals = new Animal[3];
		CrabCatcherGame game1 = new CrabCatcherGame(0, 0, animals, 0, 3, 10, null, 3, false,  new OverallGame(), null);
		CrabCatcherGame game2 = new CrabCatcherGame(0, 0, animals, 0, 3, 10, null, 3, false, new OverallGame(), null);
		
		//check if generate animals generates 3 animals
		game1.generateAnimals();
		for (int i=0; i < game1.getAnimals().length; i++){
			assertTrue("generateAnimals: 3 animals should exist", game1.getAnimals()[i] != null);
		}
		
	}
	
	/**
	 * tests if mouse handler detects animal clicks and adjusts score accordingly
	 */
	@Test
	public void mouseInputTest(){
		CrabCatcherGame game = new CrabCatcherGame(0, 0, null, 0, 3, 10, null, 5, false, new OverallGame(), null);
		Animal crab = new Animal(1, 1, "crab", -5, 3, true);
		Animal fish = new Animal(2, 2, "fish", -3, 3, true);
		Animal mittencrab = new Animal(3, 3, "mittencrab", 5, 3, true);
		game.addAnimal(crab);
		game.addAnimal(mittencrab);
		game.addAnimal(fish);

		
		//check if getAnimalClicked returns correct animal
		assertEquals("get animal clicked: should be crab", crab, game.getAnimalClicked(1, 1));
		assertEquals("get animal clicked: should be fish", crab, game.getAnimalClicked(2, 2));
		assertEquals("get animal clicked: should be mittencrab", crab, game.getAnimalClicked(3, 3));
		assertEquals("get animal clicked: should be nothing", crab, game.getAnimalClicked(0, 0));
		
		
		//check if clicking animals effects score
		//mitten crab increase by 5
		game.onClickTest(3,3);
		assertEquals("on click mitten crab: game score should increase to 5", 5, game.getScore());
		
		//fish decrease by 3
		game.onClickTest(2,2);
		assertEquals("on click fish: game score should decrease to 2", 2, game.getScore());
		
		//score should not be negative
		game.onClickTest(1,1);
		assertEquals("on click crab: game score should decrease to 0 (non-negative)", 0, game.getScore());
		
		//mitten crab decrease by 5
		game.setScore(10);
		game.onClickTest(1,1);
		assertEquals("on click crab: game score should decrease to 5", 5, game.getScore());	
		
		//click nothing
		game.onClickTest(0,0);
		assertEquals("on click nothing: game score should stay at 5", 5, game.getScore());	
	}
		

}
