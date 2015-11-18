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
		//(10,15) and (20, 15)
		assertTrue(crab.equals(fish));
		assertTrue(fish.equals(crab));
		
		//border cases - collision
		fish.setXloc(crab.getXloc() + fish.getImageWidth() -1);
		fish.setYloc(crab.getYloc() + fish.getImageHeight() -1);
		assertTrue(crab.equals(fish));
		assertTrue(fish.equals(crab));
		
		fish.setXloc(crab.getXloc() - fish.getImageWidth() +1);
		fish.setYloc(crab.getYloc() - fish.getImageHeight() +1);
		assertTrue(crab.equals(fish));
		assertTrue(fish.equals(crab));
		
		//border cases - no collision		
		fish.setXloc(crab.getXloc() + fish.getImageWidth());
		fish.setYloc(crab.getYloc() + fish.getImageHeight());
		assertTrue(!crab.equals(fish));
		assertTrue(!fish.equals(crab));
		
		fish.setXloc(crab.getXloc() - fish.getImageWidth());
		fish.setYloc(crab.getYloc() - fish.getImageHeight());
		assertTrue(!crab.equals(fish));
		assertTrue(!fish.equals(crab));
		
		//exact same location
		fish.setXloc(crab.getXloc());
		fish.setYloc(crab.getYloc());
		assertTrue(crab.equals(fish));
		assertTrue(fish.equals(crab));
		
	}
	
	@Test
	public void regenerateTest(){
		int oldx = crab.getXloc();
		int oldy = crab.getYloc();
		crab.setTimeLeftOnScreen(0);
		
		crab.regenerateAnimal();
		//check that time reset
		assertEquals(crab.getTimeLeftOnScreen(), crab.getDisplayDuration(), 0);
		//check that location changed
		assertFalse(crab.getXloc() == oldx && crab.getYloc() == oldy);
	}
	

}
