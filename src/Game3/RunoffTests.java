package Game3;
import static org.junit.Assert.*;

import org.junit.Test;

public class RunoffTests {

	/**
	 * Tests the initialization, all runoff should have the same strength and health
	 */
	@Test
	public void testRunoff() {
		Runoff testRunoff = new Runoff(1,1) ;
		assertEquals(testRunoff.getHealth(), 10) ;
		assertEquals(testRunoff.getStrength(), 2);
	}

	/**
	 * Runoff are equal only if they have the same location
	 */
	@Test
	public void testEqualsRunoff() {
		Runoff testRunoff1 = new Runoff(1,1) ;
		Runoff testRunoff2 = new Runoff(1,2) ;
		Runoff testRunoff3 = new Runoff(2,1) ;
		Runoff testRunoff4 = new Runoff(1,1) ;
		assertEquals(testRunoff1, testRunoff4);
		assertFalse(testRunoff1 == testRunoff2);
		assertFalse(testRunoff1 == testRunoff3);
	}

}
