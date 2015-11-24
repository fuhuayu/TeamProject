package Game3;

import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.imageio.ImageIO;

import OverallGame.OverallGame;

/**
 * @author Brendan, Danielle, David, Huayu and Zhanglong
 * @version 0.1
 * @since   2015-11-02
 * Handles the runoff (enemies) for game 3
 */
public class Runoff extends Tile implements java.io.Serializable{
	private static final long serialVersionUID = 303L;
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
			this.image = ImageIO.read(new File("images/runoff.png")).getScaledInstance(OverallGame.frameHeight/(10), OverallGame.frameHeight/10, 1);
			
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
		Runoff obj = null ;
		try {	
			FileInputStream fis = new FileInputStream(fileName);
			ObjectInputStream ois = new ObjectInputStream(fis);
			obj = (Runoff)ois.readObject();
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

	public Runoff(int row, int col, int row2, int col2, int strength,
			int health, int ticksSinceMoved, Image image) {
		super(row, col);
		row = row2;
		col = col2;
		this.strength = strength;
		this.health = health;
		this.ticksSinceMoved = ticksSinceMoved;
		this.image = image;
	}
	
	
	
}
