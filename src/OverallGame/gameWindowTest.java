package OverallGame;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class gameWindowTest {

	@Test
	public void testInitialize() throws IOException {
		OverallGame	testGame	=	new	OverallGame() 	;
		gameWindow	testWindow	=	new	gameWindow(testGame)	;
		
	}
	
	@Test
	public void testSerialization() {
		fail("Not yet implemented");
	}


}
