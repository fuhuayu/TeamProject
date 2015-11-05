package Game1;

import static org.junit.Assert.*;

import java.awt.Point;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import OverallGame.OverallGame;
import Game1.RipRapGame;

public class Game1Test {

	/**
	 * @throws InterruptedException
	 * test if the Game is properly created
	 */
	@Test
	public void test() throws InterruptedException {
		Crab crab = new Crab(0,0,0);
		JumpingBar jBar = new JumpingBar(0,0);
		ArrayList<Stone> stones = new ArrayList<Stone>();
		Sun sun = new Sun(0,0,0);
		OverallGame bigGame = new OverallGame();
		ArrayList<Cloud> clouds = new ArrayList<Cloud>();
		ArrayList<RipRapWall> wall = new ArrayList<RipRapWall>();
		RipRapGame testGame2= new RipRapGame(0, 0, 0, crab, jBar, stones, sun, clouds, bigGame, wall);
		TimeUnit.SECONDS.sleep(1);
		testGame2.updateTime();
		assertEquals(89,testGame2.getTime());
		
		// test if each property of game2 is successfully created
		assertEquals(testGame2.getClouds(),clouds);
		assertEquals(testGame2.getCrab(),crab);
		assertEquals(testGame2.getJumpingBar(),jBar);
		assertEquals(testGame2.getScore(),0);
		assertEquals(testGame2.getStones(),stones);
		assertEquals(testGame2.getSun(),sun);
		assertEquals(testGame2.getTime(),0);
		
		// test add score
		testGame2.addScore(1);
		assertEquals(testGame2.getScore(),1);
		
		// test if a new stone is added
		stones.add(new Stone(0,0,0));
		assertEquals(testGame2.getStones().size(),1);
		// test if a new cloud is added
		clouds.add(new Cloud(0,0,0));
		assertEquals(testGame2.getClouds().size(),1);
		// test if a new piece of the wall is added
		wall.add(new RipRapWall(0,0));
		assertEquals(testGame2.getWall().size(),1);
	}
	
	
	/**
	 * test if the crab meets a obstacle, what will happen after press the button
	 * if click the button while the currentValue of JumpingBar is not yet stop2
	 * crab fails to climb and back to the same position
	 * if click the button between stop1 and stop2
	 * crab successfully climb over the obstacle
	 * if click the button over stop2
	 * then crab will be back to its original position 
	 * 
	 */
	public void testOnClick(){
		//generate a new game map
		Crab crab = new Crab(0,0,0);
		JumpingBar jBar = new JumpingBar(0,0);
		ArrayList<Stone> stones = new ArrayList<Stone>();
		Sun sun = new Sun(0,0,0);
		OverallGame bigGame = new OverallGame();
		ArrayList<Cloud> clouds = new ArrayList<Cloud>();
		ArrayList<RipRapWall> wall = new ArrayList<RipRapWall>();
		RipRapGame testGame2= new RipRapGame(0, 0, 0, crab, jBar, stones, sun, clouds, bigGame, wall);
		
		// test if the map is properly udate while the time goes on
		testGame2.updateMap();
		assertEquals(testGame2.getCrab().getPosition(), (new Point(0,0)));
		//assertEquals(testGame2.getSun().getPosition(), "new Position");
//		for(int i =0; i< testGame2.getClouds().size();i++){
//			assertEquals(testGame2.getClouds().get(i).getPosition(), "new Position");
//		}
		for(int i =0; i< testGame2.getStones().size();i++){
			assertEquals(testGame2.getStones().get(i).getPosition(), (new Point(0,0)));
		}
		for(int i =0; i< testGame2.getWall().size();i++){
			assertEquals(testGame2.getWall().get(i), (new Point(0,0)));
		}
		
		
		// when encounter a obstacle
		//condition 1
		testGame2.setJumpingBar(new JumpingBar(2,1));
		assertEquals(testGame2.getJumpingBar().getCurrentValue(),0);// when current value is below stop2
		testGame2.onClick();
		assertEquals(testGame2.getCrab().getPosition(), (new Point(0,0)));// back to the same place
		
		//condition 2
		testGame2.getJumpingBar().update();
		assertEquals(testGame2.getJumpingBar().getCurrentValue(),1);// the currentValue is at Stop2
		testGame2.onClick();
		assertEquals(testGame2.getCrab().getPosition(), (new Point(0,0)));//climb over stone
		
		// condition 3
		testGame2.getJumpingBar().update();
		assertEquals(testGame2.getJumpingBar().getCurrentValue(),2);// the currentValue is at stop1
		testGame2.onClick();
		assertEquals(testGame2.getCrab().getPosition(), (new Point(0,0)));//back to the original position
		

		

	}

}
