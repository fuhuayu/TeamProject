package Game2;

import java.awt.Image;
import java.awt.Rectangle;
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
public class AnimalMoveInProgress implements java.io.Serializable {
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
	private boolean expired;
	private int xdir;
	private int ydir;
	private int step;
		
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
	private AnimalMoveInProgress(int xloc, int yloc, String typeOfAnimal, int scoreEffect,
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
	public AnimalMoveInProgress(int xloc, int yloc, String typeOfAnimal, int scoreEffect,
			double displayDuration, boolean visible) {
		this.xloc = xloc;
		this.yloc = yloc;
		this.typeOfAnimal = typeOfAnimal;
		this.scoreEffect = scoreEffect;
		this.displayDuration = displayDuration;
		this.timeLeftOnScreen = displayDuration;
		this.visible = visible;
		this.expired = false;
	}
	
	//COPIER
	/**returns a copy of the animal
	 * @return
	 */
	public Animal copy(){
		Animal copy = new Animal(xloc, yloc, typeOfAnimal, scoreEffect, displayDuration, visible);
		copy.setImage(image); //note: also sets image height and width
		return copy;
	}
	
	//METHODS
	/**
	 * Call this method to regenerate animal after it is caught or time on screen expires
	 * makes the animal invisible, resets its timeLeftOnScreen to display duration, and sets location randomly
	 */
	public void regenerateAnimal(int xbound, int ybound){
		//sets animal visibility to false (could use an animation here)
		visible = false;
		//gives animal a random location
		Random r = new Random();
		//USE SCREEN WIDTH
		xloc = r.nextInt(xbound - this.imageWidth);
		yloc = r.nextInt(ybound);
		visible = r.nextBoolean();
		//reset timeLeftOnScreen to display duration
		timeLeftOnScreen = displayDuration;
	}
	
	


	/**Returns true if the objects' image rectangles intersect
	 * @param obj MUST be an object with x, y loc and imageWidth/Height getters
	 * @return
	 */
	public boolean overlapsWith(Object obj){
		Animal other = (Animal)(obj);
		if (other == null){return false;}		
		else{
		Rectangle thisRect = new Rectangle(xloc, yloc, imageWidth, imageHeight);
		Rectangle otherRect = new Rectangle(other.getXloc(), other.getYloc(), other.getImageWidth(), other.getImageHeight());
		return (thisRect.intersects(otherRect));}
	}
	
	/**
	 * updates animal's timed elements - decreases timeLeftOnScreen
	 */
	public void onTick(CrabCatcherGame game){
		//decrease animal's timer; if timer is 0, regenerate animal
		if (timeLeftOnScreen <= 0){
			//game.getAnimals().remove(this);
			//regenerateAnimal(); //regenerate as a new random animal
			//game.addUniqueLocAnimal(this);
			//this.expired = true;
		}
		else {timeLeftOnScreen--;}
	}
	
	public void move(CrabCatcherGame game){
		//just move to the right for now
		xdir = 1;
		ydir = 0;
		step = 5;
		xloc += xdir*step;
		yloc += ydir*step;
		//if offscreen, regenerate
		if (xloc > game.getBigGame().frameWidth + this.imageWidth){
			this.regenerateAnimal(game.getBigGame().frameWidth, game.getBigGame().frameHeight);
		}
	}
	
	
	/**For testing purposes only; 
	 * updates animal's timed elements - decreases timeLeftOnScreen
	 * @param game
	 */
	public void onTickTest(){
		//decrease animal's timer; if timer is 0, regenerate animal
		if (timeLeftOnScreen <= 0){
			regenerateAnimal(800, 800);
			//expired = true;			
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
		this.imageHeight = image.getHeight(null);
		this.imageWidth = image.getWidth(null);
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

	public boolean isExpired() {
		return expired;
	}

	public void setExpired(boolean expired) {
		this.expired = expired;
	}
	

}

