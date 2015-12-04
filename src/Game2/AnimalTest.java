package Game2;

import static org.junit.Assert.*;

import org.junit.Test;

import junit.framework.TestCase;

public class AnimalTest {
	Animal crab = new Animal(10, 15, "crab", -5,
			10, true);
	Animal fish = new Animal(20, 15, "fish", -3, 10, true);

	
	@Test
	public void collisionTest(){
		crab.setImageWidth(250);
		crab.setImageHeight(200);
		fish.setImageWidth(250);
		fish.setImageHeight(200);
		
		//(10,15) and (20, 15)
		assertTrue(crab.overlapsWith(fish));
		assertTrue(fish.overlapsWith(crab));
		
		//border cases - collision
		fish.setXloc(crab.getXloc() + fish.getImageWidth() -1);
		fish.setYloc(crab.getYloc() + fish.getImageHeight() -1);
		assertTrue(crab.overlapsWith(fish));
		assertTrue(fish.overlapsWith(crab));
		
		fish.setXloc(crab.getXloc() - fish.getImageWidth() +1);
		fish.setYloc(crab.getYloc() - fish.getImageHeight() +1);
		assertTrue(crab.overlapsWith(fish));
		assertTrue(fish.overlapsWith(crab));
		
		//border cases - no collision		
		fish.setXloc(crab.getXloc() + fish.getImageWidth());
		fish.setYloc(crab.getYloc() + fish.getImageHeight());
		assertTrue(!crab.overlapsWith(fish));
		assertTrue(!fish.overlapsWith(crab));
		
		fish.setXloc(crab.getXloc() - fish.getImageWidth());
		fish.setYloc(crab.getYloc() - fish.getImageHeight());
		assertTrue(!crab.overlapsWith(fish));
		assertTrue(!fish.overlapsWith(crab));
		
		//exact same location
		fish.setXloc(crab.getXloc());
		fish.setYloc(crab.getYloc());
		assertTrue(crab.overlapsWith(fish));
		assertTrue(fish.overlapsWith(crab));
		
		
		//additional tests
		Animal f1 = new Animal(410, 15, "fish", -3, 10, true);
		Animal c1 = new Animal(400, 15, "crab", -5,
				10, true);
		Animal f2 = new Animal(950, 950, "fish", -3, 10, true);
		
		f1.setImageWidth(250);
		f1.setImageHeight(200);
		
		c1.setImageWidth(250);
		c1.setImageHeight(200);
		
		f2.setImageWidth(250);
		f2.setImageHeight(200);
		
		//System.out.println("f1 width: " + f1.getImageWidth());
		//System.out.println("f1 height: " + f1.getImageHeight());
		assertTrue(f1.overlapsWith(c1));
		assertFalse(f2.overlapsWith(f1));
		assertFalse(f2.overlapsWith(c1));
		
		
	}
	
	
	@Test
	public void offScreenDetectionTest(){
		Animal mittencrab = new MovingAnimal(100, 10, "mittencrab", -3, 10, true);
		mittencrab.setImageHeight(200);
		mittencrab.setImageWidth(250);
		
		//crab still on 200x200 screen, but off 100x100 screen
		assertFalse(mittencrab.offScreen(200, 200));
		assertTrue(mittencrab.offScreen(100, 100));
		
		//0,0
		mittencrab.setXloc(0);
		mittencrab.setYloc(0);
		assertFalse("0,0 is still on screen", mittencrab.offScreen(200, 200));
		
		//-5,0
		mittencrab.setXloc(-5);
		assertFalse("-5,0 is still partly on screen", mittencrab.offScreen(200, 200));
		
		//195,0
		mittencrab.setXloc(195);
		assertFalse("195,0 is still partly on screen", mittencrab.offScreen(200, 200));
		
		//195, 195
		mittencrab.setYloc(195);
		assertFalse("195,195 is still partly on screen", mittencrab.offScreen(200, 200));
		
		//left bound
		mittencrab.setXloc(-250);
		assertTrue(mittencrab.offScreen(200, 200));
		
		//right bound
		mittencrab.setXloc(200);
		assertTrue(mittencrab.offScreen(200, 200));
		
		//upper bound
		mittencrab.setYloc(-200);
		assertTrue(mittencrab.offScreen(200, 200));
		
		//lower bound
		mittencrab.setYloc(200);
		assertTrue(mittencrab.offScreen(200, 200));
		
	}
	
	@Test
	public void regenerateTest(){
		int oldx = crab.getXloc();
		int oldy = crab.getYloc();
		crab.setTimeLeftOnScreen(0);
		
		crab.regenerateAnimal(800,800);
		//check that time reset
		assertEquals(crab.getTimeLeftOnScreen(), crab.getDisplayDuration(), 0);
		//check that location changed
		assertFalse(crab.getXloc() == oldx && crab.getYloc() == oldy);
	}
	

}
