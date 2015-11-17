package Game3;

import java.io.Serializable;

/**
 * @author Brendan, Danielle, David, Huayu and Zhanglong
 * @version 0.1
 * @since   2015-11-02
 * Handles the runoff (enemies) for game 3
 */
public class Runoff implements Serializable{
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
	public Runoff(int row, int col) {
		this.row	=	row	;
		this.col	=	col	;
		this.strength	=	2	;
		this.health		=	10	;
	} ;
	
	/**
	 * Runoff are equal if they are in the same row and column
	 * @param other - the runoff to be compared to
	 */
	public boolean equals (Runoff other) {
		return (row == other.getRow() && col == other.getCol());
	}

	
	
	/**
	 * Getters and Setters
	 */
	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	} 
	
	public String toString(){
		return "Row: "+row+"Col: "+col+"Strength: "+strength+"Health: "+health;
	}
}
