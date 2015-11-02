package Game3;

public class Plant {
	int 	row ;
	int 	col ;
	int 	strength ;
	int 	health 	 ;
	String 	type ;
	/**
	 * Constructor
	 * strength is determined by the plant type (Grass = 3)
	 * Health 	is determined by the plant type	(Grass = 10)
	 */
	public Plant(int row, int col, String type) {} ;
	
	/**
	 * Getters for all attributes
	 */
	public int getRow() {};
	public int getCol() {};
	public int getStrength() {};
	public int getHealth()   {};
	public String getType()  {};
	
	/**
	 * Plants are equal if they are the same type of plant and are in the same row and column
	 */
	public boolean equals(Plant other)  {};
}
