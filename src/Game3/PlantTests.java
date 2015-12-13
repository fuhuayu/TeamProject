package Game3;
import static org.junit.Assert.*;

import org.junit.Test;

public class PlantTests {

	
	/**
	 * This test will test if the plant is propery created according to its type
	 * Right now, however, we have 2 types
	 * Types:
	 * Grass - Health 10 , Strength 3
	 * Not Grass - Health 15 , Strength 2
	 */
	@Test
	public void testPlant() {
		Plant testPlant = new Plant(1,1, "Grass");
		assertEquals(testPlant.getStrength(), 3);
		assertEquals(testPlant.getHealth(), 10 );
	}

	@Test
	public void testEqualsPlant() {
		Plant testPlant1 = new Plant(1,1,"Grass") ;
		Plant testPlant2 = new Plant(1,1,"Grass") ;
		Plant testPlant3 = new Plant(1,1,"Not Grass") ;
		Plant testPlant4 = new Plant(1,2,"Grass") ;
		Plant testPlant5 = new Plant(2,1,"Grass") ;
		assertEquals(testPlant1,testPlant2);
		assertFalse(testPlant1 == testPlant3);
		assertFalse(testPlant1 == testPlant4);
		assertFalse(testPlant1 == testPlant5);
	}

}
