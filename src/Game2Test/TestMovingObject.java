package Game2Test;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Test;

import Game2.MovingObject;

public class TestMovingObject {

	@Test
	public void test() {
		MovingObject m=new MovingObject(0, 0, 0);
		assertEquals(new Point(0,0),m.getPosition());
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
		assertEquals(0,m.getSize());
=======
<<<<<<< HEAD
		assertEquals(0,m.getSize());//h
=======
		assertEquals(0,m.getSize());
>>>>>>> parent of 6b0f76b... 1
>>>>>>> parent of 4659568... 2
=======
<<<<<<< HEAD
		assertEquals(0,m.getSize());//h
=======
		assertEquals(0,m.getSize());
>>>>>>> parent of 6b0f76b... 1
=======
		assertEquals(0,m.getSize());//hh
>>>>>>> parent of f14858a... yo
>>>>>>> parent of 5cd7977... Update TestMovingObject.java
=======
		assertEquals(0,m.getSize());//hh
>>>>>>> parent of f14858a... yo
=======
		assertEquals(0,m.getSize());//hh
>>>>>>> parent of f14858a... yo
	}

}
