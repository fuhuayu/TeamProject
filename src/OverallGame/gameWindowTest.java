package OverallGame;

import static org.junit.Assert.*;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.MouseEvent;
import java.io.IOException;

import org.junit.Test;

public class gameWindowTest {
	private static Robot rob = null;
	@Test
	public void testInitialize() throws IOException {
		try {
			rob = new Robot();
		}
		catch (AWTException e) {
			e.printStackTrace();
		}
		OverallGame	testGame	=	new	OverallGame() 	;
		//test Game 1 Button
		rob.mouseMove(50, 200);
		rob.mousePress(MouseEvent.BUTTON1_MASK);
		rob.delay(1000);
		rob.mouseRelease(MouseEvent.BUTTON1_MASK);
		assertEquals(1, testGame.getGamesRunning());
		testGame.getGame1().endGame();
		//Test Game 2 Button
		rob.mouseMove(500, 200);
		rob.mousePress(MouseEvent.BUTTON1_MASK);
		rob.mouseRelease(MouseEvent.BUTTON1_MASK);
		assertEquals(2, testGame.getGamesRunning());
		testGame.getGame2().endGame();
		//Test Game 3 Button
		rob.mouseMove(800, 200);
		rob.mousePress(MouseEvent.BUTTON1_MASK);
		rob.mouseRelease(MouseEvent.BUTTON1_MASK);
		assertEquals(3, testGame.getGamesRunning());
		testGame.getGame3().endGame("testHighScores.txt");
		assertEquals(0, testGame.getGamesRunning());
	}
	
	@Test
	public void testSerialization() {
		OverallGame	testGame	=	new	OverallGame()	;
		gameWindow	testWindow	=	new	gameWindow(testGame)	;
		gameWindow.serialize(testWindow,	"gameWindow.ser");
		gameWindow	loadedWindow	=	(gameWindow)gameWindow.deserialize("gameWindow.ser");
		testWindow.getFrame().setVisible(false);
		System.out.println(loadedWindow + "\n" + testWindow);
		assertEquals(testWindow.toString(), loadedWindow.toString());
	}


}
