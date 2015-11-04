package Game3;


/**
 * @author Brendan, David, Danielle, Zhanglong and Huayu
 * @version 0.1
 * @since   2015-11-02
 * Handles the plant objects (defense) for game 3
 */
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
	 * @param row - row to place the plant in
	 * @param col - column to place the plant in
	 * @param type - type of plant to be placed
	 */
	public Plant(int row, int col, String type) {} ;
	
	/**
	 * Plants are equal if they are the same type of plant and are in the same row and column
	 * @param other - The plant to be compared to
	 */
	public boolean equals(Plant other)  {
		return false;
	}
	
	/**
	 * Getters for all attributes
	 */
	public int getRow() {
		return 0;
	}
	public int getCol() {
		return 0;
	}
	public int getStrength() {
		return 0;
	}
	public int getHealth()   {
		return 0;
	}
	public String getType()  {
		return null;
	}
	
	public void setRow(int row) {}
	public void setCol(int col) {}
	public void setStrength(int strength) {}
	public void setHealth(int health) {}
	public void setType(String type) {}
	
}
