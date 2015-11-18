package Game3;
import static org.junit.Assert.*;

import org.junit.Test;


public class MusselTests {
	
	/**
	 * Mussels should grow until they reach the final stage (tentatively set at 3)
	 */
	@Test
	
	public void testGrow() {
		Mussel testMussel = new Mussel(1,1) ;
		assertEquals(testMussel.getStage(), 0) ;
		testMussel.grow() ;
		assertEquals(testMussel.getStage(), 1) ;
		testMussel.grow() ;
		assertEquals(testMussel.getStage(), 2) ;
		testMussel.grow() ;
		assertEquals(testMussel.getStage(), 3) ;
		testMussel.grow() ;
		assertEquals(testMussel.getStage(), 3) ;
	}
	
	public void testDraw() {
		Mussel testMussel = new Mussel(1,1) ;
		System.out.println(testMussel.getMusselDrawing());
		assertEquals(testMussel.getMusselDrawing(), null);
	}

}
