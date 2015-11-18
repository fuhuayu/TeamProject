package Game3;

import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.imageio.ImageIO;


/**
 * @author Brendan, Danielle, David, Huayu and Zhanglong
 * @version 0.1
 * @since   2015-11-02
 * Handles the plant objects (defense) for game 3
 */
public class Plant extends Tile implements Serializable{
	private static final long serialVersionUID = 302L;
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
		super(row, col);
		this.row = row ;
		this.col = col ;
		this.type	=	type	;
		this.strength	=	(type.equals("Grass"))	?	3	:	4	;
		this.health		=	(type.equals("Grass"))	?	10	:	15	;
		this.image = null;
		try {
			this.image = ImageIO.read(new File("images/Grass.png")).getScaledInstance(130, 130, 1);
			
		} catch(IOException e) {
			System.out.println("Read Error: " + e.getMessage());
		}
	}
	
	/**
	 * Plants are equal if they are the same type of plant and are in the same row and column
	 * @param other - The plant to be compared to
	 */
	public boolean equals(Plant other)  {
		return (row == other.getRow() && col == other.getCol() && type == other.getType());
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
	public String toString(){
		return "Plants [ Row: "+row+", Col: "+col+", Strength: "+strength+", Health: "+health+", Type: "+type+"]";
	}
	
	/**
	 * Method to serialize OverallGame, which contains the other games as params
	 * So this output will contain the serialized version of every object
	 * @param obj
	 * @param fileName
	 * @throws IOException
	 */
	public static void serialize(Object obj, String fileName) {
		try {
	        FileOutputStream fos = new FileOutputStream(fileName);
	        ObjectOutputStream oos = new ObjectOutputStream(fos);
	        oos.writeObject(obj);
	        fos.close();
		}
		catch (IOException e) {
			System.out.println("Read Error: " + e.getMessage());
		}
	}
	
	/**
	 * Method to read a game state from file and instantiate it. The reverse of the serialize function
	 * @param fileName
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static Object deserialize(String fileName) {
		Plant obj = null ;
		try {	
			FileInputStream fis = new FileInputStream(fileName);
			ObjectInputStream ois = new ObjectInputStream(fis);
			obj = (Plant)ois.readObject();
			ois.close();
		}
		catch(IOException e) {
			System.out.println("Read Error: " + e.getMessage());
		}
		catch (ClassNotFoundException e){
			System.out.println("Read Error: " + e.getMessage());
		}
		return obj;
	}

	public Plant(int row, int col, int row2, int col2, int strength,
			int health, String type, Image image) {
		super(row, col);
		row = row2;
		col = col2;
		this.strength = strength;
		this.health = health;
		this.type = type;
		this.image = image;
	}
	
	
	
}
