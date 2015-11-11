package Game3;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


/**
 * @author Brendan, Danielle, David, Huayu and Zhanglong
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
	Image   image;
	/**
	 * Constructor
	 * strength is determined by the plant type (Grass = 3)
	 * Health 	is determined by the plant type	(Grass = 10)
	 * @param row - row to place the plant in
	 * @param col - column to place the plant in
	 * @param type - type of plant to be placed
	 */
	public Plant(int row, int col, String type) {
		this.row 	=	row	;
		this.col	=	col	;
		this.type	=	type	;
		this.strength	=	(type.equals("Grass"))	?	3	:	4	;
		this.health		=	(type.equals("Grass"))	?	10	:	15	;
		BufferedImage image = null;
		try {
			if (getType().equals("Grass")) {
				image = ImageIO.read(new File("images/Grass.png"));
			}
			else {
				image = ImageIO.read(new File("images/noPlant.png"));
			}
		} catch(IOException e) {
			System.out.println("Read Error: " + e.getMessage());
		}
		this.image = image.getScaledInstance(130, 130, 1);
	}
	
	/**
	 * Plants are equal if they are the same type of plant and are in the same row and column
	 * @param other - The plant to be compared to
	 */
	public boolean equals(Plant other)  {
		return (row == other.getRow() && col == other.getCol() && type == other.getType());
	}

	public Image draw() {
		
		return image;
	}
	
	/**
	 * Getters for all attributes
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	public Image getImage() {
		return image;
	}
	
}
