package Game1;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Test;

public class MovingObjectTest {

	/**
	 * Test the constructor
	 */
	@Test
	public void testMovingObject() {
		MovingObject m=new MovingObject(0, 0, 0);
		assertEquals(new Point(0,0),m.getPosition());
		assertEquals(0,m.getSize());
	}
	/**
	 * Test the crab
	 */
	@Test
	public void testCrab(){
		Crab c=new Crab(0, 0, 0);
		c.update();
		assertEquals(new Point(1,1),c.getPosition());
		c.setMode(2);
		assertEquals(new Point(1,1),c.getPosition());
		c.setMode(3);
		assertEquals(new Point(3,3),c.getPosition());
	}
	
}
