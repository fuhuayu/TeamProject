package OverallGame;
import static org.junit.Assert.*;

import java.awt.event.MouseEvent;
import java.io.IOException;

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
	
	@Test
	public void SerializationTest() throws IOException {
		OverallGame testGame = new OverallGame() ;
		OverallGame.serialize(testGame, "testOutput.ser");
		OverallGame loadedGame = (OverallGame)OverallGame.deserialize("testOutput.ser");
		assertEquals(loadedGame.toString(), testGame.toString());
	}

}
