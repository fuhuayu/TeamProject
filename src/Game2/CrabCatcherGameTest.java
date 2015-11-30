package Game2;
import static org.junit.Assert.*;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashSet;
import javax.swing.Timer;

import javax.swing.JFrame;

import org.junit.Test;

import Game1.RipRapGame;
import Game3.Game3;
import OverallGame.OverallGame;
import OverallGame.gameWindow;


/**
 * @author Dwegrzyn
 *
 */
public class CrabCatcherGameTest {	
	
	JFrame frame = new JFrame();
	OverallGame bigGame = makeTestBigGame();
	
	/**makes an overall game for testing
	 * @return
	 */
	public OverallGame makeTestBigGame(){
		OverallGame bigGame = new OverallGame();
		frame.setBounds(100, 100, bigGame.frameWidth, bigGame.frameHeight);
		//frame.setVisible(false);
		return bigGame;
	}
	
	/**ends game without panel/visual settings
	 * @param g crab game to be ended
	 */
	public void fakeEndGame(CrabCatcherGame g){
		g.setGameOver(true);
		//send score to send to big game
		bigGame.setOverallScore(bigGame.getOverallScore() + g.getScore());
		//stop timer
		g.getTimer().stop();
		//set big game running to true
		bigGame.setGamesRunning(0);
		
	}
	
	/**updates game without panel/visual settings
	 * @param g crab game to be updated
	 */
	public void fakeUpdateGame(CrabCatcherGame g){
		g.setTime(g.getTime()+1);
		if (g.getLives() == 0 || g.getTime() >= g.getGameLength()){
    		//System.out.println("time is " + time + ">= " + gameLength);
			fakeEndGame(g);
		}
		//updates game's timed aspects - call animal.onTick() for all animals
		for (Animal each : g.getAnimals()) {
			if(each != null){each.onTickTest();}
		}
	}
	
	/**makes a crab game for testing with only animals and a timer
	 * @return
	 */
	public CrabCatcherGame makeTestCrabGame(){
		ArrayList<Animal> animals = new ArrayList<Animal>();
		CrabCatcherGame game = new CrabCatcherGame(0, animals, 0, 3, 10, null, 5, false, bigGame, frame);
		//Animal crab = new Animal(0, 0, "crab", 5, 3, true);
		//game.addAnimal(crab);
		game.setTimer(new Timer(0, null));
		return game;
	}
	
	/**
	 * tests if tick-dependent conditions are updated in onTick()
	 */
	@Test
	public void tickTest(){
		makeTestBigGame();
		ArrayList<Animal> animals = new ArrayList<Animal>();
		CrabCatcherGame game = new CrabCatcherGame(0, animals, 0, 3, 10, null, 5, false, bigGame, frame);
		Animal crab = new Animal(0, 0, "crab", 5, 3, true);
		game.addAnimal(crab);
		game.setTimer(new Timer(0, null));
		
		//check if game timer increases
		//check if gameOver is triggered by lives = 0
		//check if crab time remaining decreases
		game.setLives(0);
		fakeUpdateGame(game);
		
		assertEquals("on tick: game time should increase from 0 to 1", 1, game.getTime(), 0);
		assertTrue("on tick: lives = 0 should trigger gameOver", game.isGameOver());
		assertEquals("on tick: crab time remaining should decrease from 3 to 2", 2, crab.getTimeLeftOnScreen(), 0);
		
		//check if gameOver is triggered by time = end time
		game.setGameOver(false);
		game.setLives(3);
		game.setTime(game.getGameLength());
		fakeUpdateGame(game);
		assertTrue("on tick: time = gameLength should trigger gameOver", game.isGameOver());
		
		//check if expired crab is regenerated
		game.setGameOver(false);
		game.setTime(0);
		crab.setTimeLeftOnScreen(1);
		
		int x = crab.getXloc();
		int y = crab.getYloc();
		fakeUpdateGame(game);//crab time = 0
		fakeUpdateGame(game);//process expired crab
		
		assertEquals("on tick: expired crab time remaining should reset to 3", 3, crab.getTimeLeftOnScreen(), 0);
		//assertTrue("on tick: regenerated crab visibility should reset to false", !crab.isVisible());
		assertTrue("crab location should change", (crab.getXloc() != x || crab.getYloc() != y));
	}
	
	/**
	 * tests if game is created correctly
	 */
	@Test
	public void setupTest(){
		//HashSet<Animal> animals = new HashSet<Animal>();
		//CrabCatcherGame game1 = new CrabCatcherGame(0, animals, 0, 3, 10, null, 3, false,  new OverallGame(), null);
		CrabCatcherGame game1 = makeTestCrabGame();	
		
		//check if generate animals generates 3 animals
		game1.generateAnimals();
		for (int i=0; i < game1.getMaxAnimalsOnScreen(); i++){
			assertTrue("generateAnimals: 3 animals should exist", !game1.getAnimals().isEmpty());
		}
		
	}
	
	@Test
	public void animalOverlapTest(){
		CrabCatcherGame gamet = makeTestCrabGame();
		gamet.setTimer(null);
		ArrayList<Animal> animals1 = new ArrayList<Animal>();
		
		Animal crab = new Animal(10, 15, "crab", -5,
				10, true);
		Animal fish = new Animal(410, 15, "fish", -3, 10, true);
		Animal fish2 = new Animal(400, 20, "fish", -4, 10, true);
		Animal fish3 = new Animal(950, 950, "fish", -6, 10, true);
		fish3.setImageWidth(250);
		fish3.setImageHeight(200);
		
		animals1.add(crab);
		animals1.add(fish);
		gamet.setAnimals(animals1);
		System.out.println("animal list ---- " + gamet.getAnimals());
	
		for (Animal a: gamet.getAnimals()){
			a.setImageWidth(250);
			a.setImageHeight(200);
			if(a.overlapsWith(fish3)){
				System.out.println("Overlap detected with: " + a.toString());
			}
			if(a.overlapsWith(fish3)){
				System.out.println("Overlap detected with: " + a.toString());
			}
		}
		System.out.println("animal listNOW ---- " + gamet.getAnimals());
		System.out.println("starting hereeee");
		assertTrue("fish 3 should have a unique location", gamet.uniqueLocation(fish3));
		assertFalse("fish 2 should have its location taken", gamet.uniqueLocation(fish2));
		
		
		
	}
	
	/**
	 * tests if mouse handler detects animal clicks and adjusts score accordingly
	 */
	@Test
	public void mouseInputTest(){
		System.out.println("starting mouseInputTest");
		CrabCatcherGame game = makeTestCrabGame();
		Animal crab = new Animal(100, 100, "crab", -5, 3, true);
		Animal fish = new Animal(500, 500, "fish", -3, 3, true);
		Animal mittencrab = new Animal(800, 800, "mittencrab", 5, 3, true);
		game.setAnimals(new ArrayList<Animal>());
		game.addAnimal(crab);
		game.addAnimal(mittencrab);
		game.addAnimal(fish);

		
		//check if getAnimalClicked returns correct animal
		assertEquals("get animal clicked: should be crab", crab, game.getAnimalClicked(100, 100));
		assertEquals("get animal clicked: should be fish", fish, game.getAnimalClicked(500, 500));
		System.out.println("clicking on 800, 800....");
		game.getAnimalClicked(800, 800);
		assertEquals("get animal clicked: should be mittencrab", mittencrab, game.getAnimalClicked(800, 800));
		assertEquals("get animal clicked: should be nothing", null, game.getAnimalClicked(0, 0));
		
		
		//check if clicking animals effects score
		//mitten crab increase by 5
		game.onClickTest(800,800);
		assertEquals("on click mitten crab: game score should increase to 5", 5, game.getScore());
		
		//fish decrease by 3
		game.onClickTest(500,500);
		assertEquals("on click fish: game score should decrease to 2", 2, game.getScore());
		
		//score should not be negative
		game.onClickTest(100,100);
		assertEquals("on click crab: game score should decrease to 0 (non-negative)", 0, game.getScore());
		
		//mitten crab increase by 5
		game.setScore(10);
		crab.setXloc(100);
		crab.setYloc(100);
		game.onClickTest(100,100);
		assertEquals("on click crab: game score should decrease by 5", 5, game.getScore());	
		
		//click nothing
		game.onClickTest(0,0);
		//assertEquals("on click nothing: game score should stay at 15", 15, game.getScore());	
	}
	
	@Test
	public void endGameEffectTest(){
		CrabCatcherGame game = makeTestCrabGame();
		bigGame.setGame2(game);
		bigGame.setOverallScore(0);
		game.setScore(100);
		fakeEndGame(game);
		assertEquals("big game score should increase to 100", 100, bigGame.getOverallScore());	
		assertEquals("big game game running should be 0", 0, bigGame.getGamesRunning());	
	}
		

}
