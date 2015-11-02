package Game3;
import static org.junit.Assert.*;

import java.awt.event.MouseEvent;
import java.util.ArrayList;

import org.junit.Test;

import OverallGame.OverallGame;

public class Game3Tests {

	/**
	 * Checks to see if the game time, score, plants and runoff are correct upon creation
	 */
	@Test
	public void testGame3() {
		OverallGame testBigGame = new OverallGame();
		Game3 testGame = new Game3(testBigGame) ;
		ArrayList<Plant> testPlants = new ArrayList<Plant>(0);
		ArrayList<Runoff> testRunoff = new ArrayList<Runoff>(0);
		assertEquals(testGame.getTime(), 300.0); //Okay because the time will be set to this by default
		assertEquals(testGame.getScore(), 0)   ;
		assertEquals(testGame.getPlants(), testPlants);
		assertEquals(testGame.getRunoff(), testRunoff);
	}

	@Test
	public void testUpdate() {
		OverallGame testBigGame = new OverallGame();
		Game3 testGame = new Game3(testBigGame) ;
		testGame.addPlant(1, 1, "Grass");
		testGame.addRunoff(1, 3);
		testGame.update(); //runoff will move next to plant
		//Score will go up by 1, money will go up by 1
		assertEquals(testGame.getScore(), 1);
		assertEquals(testGame.getMoney(), 101);
		assertEquals(testGame.getRunoff().get(0).getCol(), 2);
		testGame.update(); 
		//Plant and Runoff should fight now and stay in the same position
		assertEquals(testGame.getScore(), 2);
		assertEquals(testGame.getMoney(), 102);
		assertEquals(testGame.getRunoff().get(0).getHealth(), 7);
		assertEquals(testGame.getPlants().get(0).getHealth(), 8);
		assertEquals(testGame.getRunoff().get(0).getCol(), 2);
	}

	@Test
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
		//Simulate the player exiting the game
		MouseEvent e3 = new MouseEvent(null, 0, 0, 0, 99, 99, 1, false);
		testGame.onClick(e3) ;
		assertTrue( testGame.getGameEnded() ); //Exiting the game means setting time to zero and updating AS OF NOW
	}

	@Test
	public void testAddPlant() {
		OverallGame testBigGame = new OverallGame();
		Game3 testGame = new Game3(testBigGame) ;
		testGame.addPlant(1,1,"Grass") ;
		ArrayList<Plant> testPlants = new ArrayList<Plant>(1);
		testPlants.add(new Plant(1,1,"Grass"));
		assertEquals(testGame.getPlants().get(0), testPlants);
	}

	@Test
	public void testAddRunoff() {
		OverallGame testBigGame = new OverallGame();
		Game3 testGame = new Game3(testBigGame) ;
		testGame.addRunoff(1,1) ;
		ArrayList<Runoff> testRunoff = new ArrayList<Runoff>(1);
		testRunoff.add(new Runoff(1,1));
		assertEquals(testGame.getRunoff().get(0), testRunoff);
	}

	@Test
	public void testBattle() {
		OverallGame testBigGame = new OverallGame();
		Game3 testGame = new Game3(testBigGame) ;
		testGame.addPlant(1,1,"Grass") ;
		testGame.addRunoff(1, 2);
		testGame.battle(testGame.getPlants().get(0), testGame.getRunoff().get(0));
		//We will check the health of both afterwards
		assertEquals(testGame.getPlants().get(0).getHealth(), 8) ;
		assertEquals(testGame.getRunoff().get(0).getHealth(), 7) ;
		//Now we will kill the runoff
		testGame.battle(testGame.getPlants().get(0), testGame.getRunoff().get(0));
		testGame.battle(testGame.getPlants().get(0), testGame.getRunoff().get(0));
		testGame.battle(testGame.getPlants().get(0), testGame.getRunoff().get(0));
		assertEquals(testGame.getPlants().get(0).getHealth(), 2) ;
		assertEquals(testGame.getRunoff(), new ArrayList<Runoff>(0)) ;
		//Now we will kill the plant
		testGame.addRunoff(1, 2);
		testGame.battle(testGame.getPlants().get(0), testGame.getRunoff().get(0));
		assertEquals(testGame.getPlants(), new ArrayList<Plant>(0)) ;
		assertEquals(testGame.getRunoff().get(0).getHealth(), 7)	;
	}
	
	@Test
	public void testAddMoney() {
		OverallGame testBigGame = new OverallGame();
		Game3 testGame = new Game3(testBigGame) ;
		testGame.addMoney(100);
		assertEquals(testGame.getMoney(),100) ;
	}
}
