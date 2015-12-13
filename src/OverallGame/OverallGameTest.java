package OverallGame;
import static org.junit.Assert.*;

import java.io.IOException;


import org.junit.Test;


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
	
	@Test
	public void initializeHighscoresTest() {
		OverallGame	testGame	=	new OverallGame();
		testGame.setHighscores(testGame.initializeHighscores("testHighScores.txt"));
		String	highScores1	=	testGame.getHighscores();
		testGame.setHighscores(testGame.initializeHighscores("highScores.txt"));
		String	highScores2	=	testGame.getHighscores();
		testGame.setHighscores(testGame.initializeHighscores("testHighScores2.txt"));
		String	highScores3	=	testGame.getHighscores();
		String	highScores4	=	"Brendan:	42069\nDavid:	666\nHuayu:	350\nZhanglong:	333\nDanielle:	0\n";
		assertEquals(highScores1,highScores2);
		assertFalse(highScores1.equals(highScores3));
		assertEquals(highScores1,highScores4);
	}
	
	@Test
	public void updateHighScoresTest() {
		OverallGame	testGame	=	new	OverallGame();
		
	}
	

}
