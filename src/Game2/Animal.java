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
public class Animal implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 201L;
	//FIELDS
	protected int xloc;
	protected int yloc;
	protected String typeOfAnimal; //for now, "crab", "mittencrab", or "fish"
	protected int scoreEffect;
	protected double displayDuration; //length of time the animal should stay on screen (constant)
	protected double timeLeftOnScreen; //how long the animal has left on screen (decreases with time); default is displayDuration
	protected boolean visible;
	protected Image[] images; //image of animal
	protected int imageWidth = 250;
	protected int imageHeight = 200;
	protected boolean caught = false;
	private boolean offScreen = false;
	private int xdir = 1;
	private int ydir;
	private int step; //speed of animal
	private static int maxSpeed = 10;
	private int picNum = 0;
		
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
			double displayDuration, double timeLeftOnScreen, boolean visible, Image[] images, int imageWidth, int imageHeight) {
		super();
		this.xloc = xloc;
		this.yloc = yloc;
		this.typeOfAnimal = typeOfAnimal;
		this.scoreEffect = scoreEffect;
		this.displayDuration = displayDuration;
		this.timeLeftOnScreen = timeLeftOnScreen;
		this.visible = visible;
		this.images = images;
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
		this.caught = false;
		Random r = new Random();
		this.step = (maxSpeed - r.nextInt(maxSpeed - 3));
		this.images = new Image[2];
	}
	
	//COPIER
	/**returns a copy of the animal that shares the major values:
	 * [location, type, scoreEffect, displayDuration, visibility, image]
	 * 
	 * @return
	 */
	public Animal copy(){
		Animal copy = new Animal(xloc, yloc, typeOfAnimal, scoreEffect, displayDuration, visible);
		copy.setImages(images); //note: also sets image height and width
		return copy;
	}
	
	//METHODS
	/**
	 * Call this method to regenerate animal after it is caught or time on screen expires
	 * makes the animal invisible, resets its timeLeftOnScreen, and sets offscreen location randomly
	 */
	public void regenerateAnimal(int xbound, int ybound){
		//sets animal visibility to false (could use an animation here)
		visible = false;
		//gives animal a random location
		Random r = new Random();
		//USE SCREEN WIDTH
		xloc = r.nextInt(xbound - this.imageWidth);
		yloc = r.nextInt(ybound - this.imageHeight);
		visible = true;
		picNum = 0;
		//reset timeLeftOnScreen to display duration
		timeLeftOnScreen = displayDuration;
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
	
	//MOVING METHODS
	/** updates animal's timed elements (movement, animation, and offScreen check)
	 * @param game - the game to update
	 */
	public void onTick(CrabCatcherGame game){
		this.move(game);
		this.updateAnimation((int)game.getTime());
		this.offScreen = offScreen(game.getBigGame().frameWidth, game.getBigGame().frameHeight);
	}
	
	/** increases animal's location based on xdir and ydir (horizontal only)
	 * @param game - game to update
	 */
	public void move(CrabCatcherGame game){
		ydir = 0;
		xloc += xdir*step;
		yloc += ydir*step;
	}
	
	/**returns true if the Animal is off screen (accounting for imageWidth)
	 * @param frameWidth
	 * @param frameHeight
	 * @return
	 */
	public boolean offScreen(int frameWidth, int frameHeight){
		 return (this.xloc + this.imageWidth <= 0 || this.xloc >= frameWidth
				 || this.yloc >= frameHeight
				 || this.yloc + this.imageHeight <= 0);
	}


	
	/** updates PicNum (current animation frame)
	 * @param time - the current game time in milliseconds
	 */
	public void updateAnimation(int time){
		if(time % 5 == 0){
			picNum = (picNum + 1) % images.length;
		}
	}

	
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

	/**
	 * sets xdir to 1 or -1 randomly
	 */
	public void setRandomXDir(){
		Random r = new Random();
		boolean left = r.nextBoolean();
		if(left){
			setXdir(1);
		}
		else{
			setXdir(-1);
		}
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

	public Image getImage() {
		return images[picNum];
	}

	public void setImages(Image[] images) {
		this.images = images;
		this.imageHeight = this.images[0].getHeight(null);
		this.imageWidth = this.images[0].getWidth(null);
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

	public boolean isCaught() {
		return caught;
	}

	public void setCaught(boolean caught) {
		this.caught = caught;
	}

	public boolean isOffScreen() {
		return offScreen;
	}

	public void setOffScreen(boolean offScreen) {
		this.offScreen = offScreen;
	}

	public int getXdir() {
		return xdir;
	}

	public void setXdir(int xdir) {
		this.xdir = xdir;
	}

	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
	}
	

}
