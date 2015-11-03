package Game2Test;

import static org.junit.Assert.assertEquals;
import Game2.JumpingBar;

public class TestJumpingBar {
	/**Create a jumpingBar with both stop1 and stop2 0
	 * 
	 */
	public void TestJumpingBar1(){
		JumpingBar testJumpingBar1 = new JumpingBar(0,0);
		assertEquals(testJumpingBar1.getCurrentValue(),0);// Inital currentValue should be 0
		testJumpingBar1.setSpeed(0);// set the speed by 0
		assertEquals(testJumpingBar1.getSpeed(),0);
		testJumpingBar1.update();// There will be no change while the time goes on
		assertEquals(testJumpingBar1.getCurrentValue(),0);
		testJumpingBar1.update();
		assertEquals(testJumpingBar1.getCurrentValue(),0);
		testJumpingBar1.clicked(); // when clicked, the currentValue should stop at where it is at
		assertEquals(testJumpingBar1.getCurrentValue(),0);		

	}

	/**create a JumpingBar with stop1 be 8 and stop2 be 5
	 * 
	 */
	public void TestJumpingBar2(){
		JumpingBar testJumpingBar2 = new JumpingBar(8,5);
		assertEquals(testJumpingBar2.getCurrentValue(),0);// Inital currentValue should be 0
		testJumpingBar2.setSpeed(1);//set speed by 1, then currentValue will be changed by 1 each second
		assertEquals(testJumpingBar2.getSpeed(),1);
		testJumpingBar2.update();
		assertEquals(testJumpingBar2.getCurrentValue(),1);//check if the change is correct
		testJumpingBar2.update();
		assertEquals(testJumpingBar2.getCurrentValue(),2);
		testJumpingBar2.clicked();
		assertEquals(testJumpingBar2.getCurrentValue(),2);		
		
	}
}
