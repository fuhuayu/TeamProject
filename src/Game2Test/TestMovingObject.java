package Game2Test;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Test;

import Game2.MovingObject;

public class TestMovingObject {

	@Test
	public void test() {
		//test constructor
		MovingObject m=new MovingObject(0, 0, 0);
		assertEquals(new Point(0,0),m.getPosition());
		assertEquals(0,m.getSize());//6
	}

}
