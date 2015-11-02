package Game1;
import static org.junit.Assert.*;

import java.awt.event.MouseEvent;

import org.junit.Test;


public class CrabCatcherGameTest {	
	@Test
	public void tickTest(){
		CrabCatcherGame game = new CrabCatcherGame(0, 0, null, 0, 3, 10, null, 5);
		Animal crab = new Animal(0, 0, "crab", 5, 3, true);
		game.addAnimal(crab);
		
		//check if gameOver is triggered by lives = 0
		//check if crab timer increases
		//check if crab time remaining decreases
		game.setLives(0);
		game.tickGame();
		
		assertEquals("on tick: game time should increase from 0 to 1", 1, game.getTime());
		assertTrue("on tick: lives = 0 should trigger gameOver", game.getGameOver());
		assertEquals("on tick: crab time remaining should decrease from 3 to 2", 1, crab.getTimeLeftOnScreen());
		
		//check if gameOver is triggered by time = end time
		game.setGameOver(false);
		game.setLives(3);
		game.setTime(9);
		game.tickGame();
		assertTrue("on tick: time = gameLength should trigger gameOver", game.getGameOver());
		
		//check if expired crab is regenerated
		game.setGameOver(false);
		game.setTime(0);
		crab.setTimeLeftOnScreen(1);
		game.tickGame();
		assertEquals("on tick: expired crab time remaining should reset to 3", 3, crab.getTimeLeftOnScreen());
		assertTrue("on tick: regenerated crab visibility should reset to false", !crab.isVisible());
		//change x y location
	}
	
	@Test
	public void setupAndEndTest(){
		CrabCatcherGame game = new CrabCatcherGame(0, 0, null, 0, 3, 10, null, 3);
		
		//check if we make all the animals on start
		game.startGame();
		assertEquals("start game: number animals should be max animals (3)", 3, game.getAnimals().length);
		
		//check if score sends on end game			
	}
	
	@Test
	public void mouseInputTest(){
		CrabCatcherGame game = new CrabCatcherGame(0, 0, null, 0, 3, 10, null, 5);
		Animal crab = new Animal(1, 1, "crab", -5, 3, true);
		Animal fish = new Animal(2, 2, "fish", -3, 3, true);
		Animal mittencrab = new Animal(3, 3, "mittencrab", 5, 3, true);
		game.addAnimal(crab);
		game.addAnimal(mittencrab);
		game.addAnimal(fish);
		
		MouseEvent clickCrab = new MouseEvent(null, MouseEvent.MOUSE_CLICKED, 0, 0, 1, 1, 0, false);
		MouseEvent clickFish = new MouseEvent(null, MouseEvent.MOUSE_CLICKED, 0, 0, 2, 2, 0, false);
		MouseEvent clickMittenCrab = new MouseEvent(null, MouseEvent.MOUSE_CLICKED, 0, 0, 3, 3, 0, false);
		MouseEvent clickNothing = new MouseEvent(null, MouseEvent.MOUSE_CLICKED, 0, 0, 0, 0, 0, false);
		
		//check if getAnimalClicked returns correct animal
		assertEquals("get animal clicked: should be crab", crab, game.getAnimalClicked(1, 1));
		assertEquals("get animal clicked: should be fish", crab, game.getAnimalClicked(2, 2));
		assertEquals("get animal clicked: should be mittencrab", crab, game.getAnimalClicked(3, 3));
		assertEquals("get animal clicked: should be nothing", crab, game.getAnimalClicked(0, 0));
		
		
		//check if clicking animals effects score
		//mitten crab increase by 5
		game.onClick(clickMittenCrab);
		assertEquals("on click mitten crab: game score should increase to 5", 5, game.getScore());
		
		//fish decrease by 3
		game.onClick(clickFish);
		assertEquals("on click fish: game score should decrease to 2", 2, game.getScore());
		
		//score should not be negative
		game.onClick(clickCrab);
		assertEquals("on click crab: game score should decrease to 0 (non-negative)", 0, game.getScore());
		
		//mitten crab decrease by 5
		game.setScore(10);
		game.onClick(clickCrab);
		assertEquals("on click crab: game score should decrease to 5", 5, game.getScore());	
		
		//click nothing
		game.onClick(clickCrab);
		assertEquals("on click nothing: game score should stay at 5", 5, game.getScore());	
	}
		

}
