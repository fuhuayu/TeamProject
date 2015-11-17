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
public class Animal implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 201L;
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
	private int imageWidth = 250;
	private int imageHeight = 200;
		
	/**All-parameter constructor. Not used publicly.
	 * @param xloc
	 * @param yloc
	 * @param typeOfAnimal
	 * @param scoreEffect
	 * @param displayDuration
	 * @param timeLeftOnScreen
	 * @param visible
	 * @param label
	 * @param image
	 * @param imageWidth
	 * @param imageHeight
	 */
	private Animal(int xloc, int yloc, String typeOfAnimal, int scoreEffect,
			double displayDuration, double timeLeftOnScreen, boolean visible,
			JLabel label, Image image, int imageWidth, int imageHeight) {
		super();
		this.xloc = xloc;
		this.yloc = yloc;
		this.typeOfAnimal = typeOfAnimal;
		this.scoreEffect = scoreEffect;
		this.displayDuration = displayDuration;
		this.timeLeftOnScreen = timeLeftOnScreen;
		this.visible = visible;
		this.label = label;
		this.image = image;
		this.imageWidth = imageWidth;
		this.imageHeight = imageHeight;
	}

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
	
	
	/*public void setAnimalImages(){
		BufferedImage crab = loadImage("crab.png");
		BufferedImage mittencrab = loadImage("mittencrab.png");
		BufferedImage fish = loadImage("fish.png");
		
		crab = (BufferedImage)image.getScaledInstance(imageWidth/2, imageHeight/2, 0);
		mittencrab = (BufferedImage)image.getScaledInstance(imageWidth/2, imageHeight/2, 0);
		fish = (BufferedImage)image.getScaledInstance(imageWidth/2, imageHeight/2, 0);		
	}*/
	
	/*public String toString(){
		return (typeOfAnimal + " " + timeLeftOnScreen);
	}*/

	
	//GETTERS & SETTERS
	public int getXloc() {
		return xloc;
	}

	@Override
	public String toString() {
		return "Animal " + typeOfAnimal + " [location=(" + xloc + ", " + yloc + ")" + ", scoreEffect=" + scoreEffect
				+ ", timeLeft=" + timeLeftOnScreen + ", visible="
				+ visible + "]";
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

	public int getImageWidth() {
		return imageWidth;
	}

	public void setImageWidth(int imageWidth) {
		this.imageWidth = imageWidth;
	}

	public int getImageHeight() {
		return imageHeight;
	}

	public void setImageHeight(int imageHeight) {
		this.imageHeight = imageHeight;
	}
	

}
