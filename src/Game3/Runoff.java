package Game3;


/**
 * @author Brendan, Danielle, David, Huayu and Zhanglong
 * @version 0.1
 * @since   2015-11-02
 * Handles the runoff (enemies) for game 3
 */
public class Runoff {
	int row ;
	int col ; 
	int strength ;
	int health ;
	
	/**
	 * Constructor
	 * Default strength 2
	 * Default health   10
	 * @param row - the row to place the runoff in
	 * @param col - the column to place the runoff in
	 */
	public Runoff(int row, int col) {} ;
	
	/**
	 * Runoff are equal if they are in the same row and column
	 * @param other - the runoff to be compared to
	 */
	public boolean equals (Runoff other) {
		return false;
	} 
	
	/**
	 * Getters and Setters
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
	public int getHealth() {
		return 0;
	} 
	
	public void setRow(int row) {}
	public void setCol(int col) {}
	public void setStrength(int strength) {}
	public void setHealth(int health) {}
}
