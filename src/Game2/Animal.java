package Game2;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JLabel;

/**
 * @author Dwegrzyn
 * A class representing the animals that appear in the Crab Catcher game
 */
public class Animal {
	//FIELDS
	private int xloc;
	private int yloc;
	private String typeOfAnimal; //for now, "crab", "mittencrab", or "fish"
	public int scoreEffect;
	private double displayDuration; //length of time the animal should stay on screen (constant)
	private double timeLeftOnScreen; //how long the animal has left on screen (decreases with time); default is displayDuration
	private boolean visible;
	private JLabel label; //temporary probably
	private Image image; //image of animal
	private int imageWidth;
	private int imageHeight;
	
	//
	//CONSTRUCTOR
	/**
	 * @param xloc the x coordinate of the animal
	 * @param yloc the y coordinate of the animal
	 * @param typeOfAnimal the type of animal ( "crab", "mittencrab", or "fish")
	 * @param scoreEffect the integer to be added to the score if this animal is caught (can be negative)
	 * @param displayDuration how long the animal should stay on screen
	 * @param visible is true if the animal is visible
	 */
	public Animal(int xloc, int yloc, String typeOfAnimal, int scoreEffect,
			double displayDuration, boolean visible) {
		this.xloc = xloc;
		this.yloc = yloc;
		this.typeOfAnimal = typeOfAnimal;
		this.scoreEffect = scoreEffect;
		this.displayDuration = displayDuration;
		this.timeLeftOnScreen = displayDuration;
		this.visible = visible;
		this.image = loadImage("mussel.png");
	}
	
	//METHODS
	/**
	 * Call this method to regenerate animal after it is caught or time on screen expires
	 * makes the animal invisible, resets its timeLeftOnScreen to display duration, and sets location randomly
	 */
	public void regenerateAnimal(){
		//sets animal visibility to false (could use an animation here)
		//visible = false;
		//gives animal a random location
		Random r = new Random();
		xloc = r.nextInt(800);
		yloc = r.nextInt(800);
		//reset timeLeftOnScreen to display duration
		timeLeftOnScreen = displayDuration;
	}
	
	/**
	 * updates animal's timed elements - decreases timeLeftOnScreen
	 */
	public void onTick(){
		//decrease animal's timer; if timer is 0, regenerate animal
		if (timeLeftOnScreen <= 0){
			regenerateAnimal();
		}
		else {timeLeftOnScreen--;}
	}
	
	/**returns the image in the path images/filename
	 * sets imageWidth and imageHeight
	 * @param filename the name of the file to be loaded
	 * @return 
	 */
	public Image loadImage(String filename) {
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File("images/" + filename));
		} catch(IOException e) {
			System.out.println("Read Error: " + e.getMessage());
		}
		//return image.getScaledInstance(scoreEffect, scoreEffect, scoreEffect);
		this.imageWidth = image.getWidth();
		this.imageHeight = image.getHeight();
		return image;
	}
	
	public String toString(){
		return (typeOfAnimal + " " + timeLeftOnScreen);
	}

	
	//GETTERS & SETTERS
	public int getXloc() {
		return xloc;
	}

	public void setXloc(int xloc) {
		this.xloc = xloc;
	}

	public int getYloc() {
		return yloc;
	}

	public void setYloc(int yloc) {
		this.yloc = yloc;
	}

	public String getTypeOfAnimal() {
		return typeOfAnimal;
	}

	public void setTypeOfAnimal(String typeOfAnimal) {
		this.typeOfAnimal = typeOfAnimal;
	}

	public int getScoreEffect() {
		return scoreEffect;
	}

	public void setScoreEffect(int scoreEffect) {
		this.scoreEffect = scoreEffect;
	}

	public double getDisplayDuration() {
		return displayDuration;
	}

	public void setDisplayDuration(double displayDuration) {
		this.displayDuration = displayDuration;
	}

	public double getTimeLeftOnScreen() {
		return timeLeftOnScreen;
	}

	public void setTimeLeftOnScreen(double timeLeftOnScreen) {
		this.timeLeftOnScreen = timeLeftOnScreen;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public JLabel getLabel() {
		return label;
	}

	public void setLabel(JLabel label) {
		this.label = label;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}
	

}
