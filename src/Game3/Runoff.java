package Game3;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * @author Brendan, Danielle, David, Huayu and Zhanglong
 * @version 0.1
 * @since   2015-11-02
 * Handles the runoff (enemies) for game 3
 */
public class Runoff extends Tile implements java.io.Serializable{
	int row ;
	int col ; 
	int strength ;
	int health ;
	int ticksSinceMoved ;
	Image image;
	
	/**
	 * Constructor
	 * Default strength 2
	 * Default health   10
	 * @param row - the row to place the runoff in
	 * @param col - the column to place the runoff in
	 */
	public Runoff(int row, int col) {
		super(row, col);
		this.row = row ;
		this.col = col ;
		this.strength	=	2	;
		this.health		=	10	;
		this.ticksSinceMoved = 0;
		this.image = null;
		try {
			this.image = ImageIO.read(new File("images/runoff.jpg")).getScaledInstance(130, 130, 1);
			
		} catch(IOException e) {
			System.out.println("Read Error: " + e.getMessage());
		}
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
	
	public int getTicksSinceMoved() {
		return ticksSinceMoved ;
	}
	
	public void setTicksSinceMoved(int ticksSinceMoved) {
		this.ticksSinceMoved = ticksSinceMoved ;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}
	
	public String toString(){
		return "Runoff [ Row: "+row+", Col: "+col+", Strength: "+strength+"Health: "+health+", TicksSinceMoved: "
				+ticksSinceMoved+"]";
				}
	
}
