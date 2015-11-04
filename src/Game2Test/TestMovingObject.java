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
		assertEquals(0,m.getSize());
=======
<<<<<<< HEAD
		assertEquals(0,m.getSize());//你好
=======

		assertEquals(0,m.getSize());

>>>>>>> master
>>>>>>> refs/remotes/origin/branch1
	}

}
