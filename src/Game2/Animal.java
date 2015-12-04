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
	protected JLabel label; //temporary probably
	protected Image image; //image of animal
	protected int imageWidth = 250;
	protected int imageHeight = 200;
	protected boolean caught = false;
	private boolean offScreen = false;
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
		this.caught = false;
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
	public void ORIGINALonTick(CrabCatcherGame game){
		//decrease animal's timer; if timer is 0, regenerate animal
		if (timeLeftOnScreen <= 0){
			//game.getAnimals().remove(this);
			//regenerateAnimal(); //regenerate as a new random animal
			//game.addUniqueLocAnimal(this);
			//this.expired = true;
		}
		else {timeLeftOnScreen--;}
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
	public void onTick(CrabCatcherGame game){
		this.move(game);
		this.offScreen = offScreen(game.getBigGame().frameWidth, game.getBigGame().frameHeight);
	}
	
	public void move(CrabCatcherGame game){
		//just move to the right for now
		xdir = 1;
		ydir = 0;
		step = 10;
		xloc += xdir*step;
		yloc += ydir*step;
		//if offscreen, regenerate
		//if (xloc > game.getBigGame().frameWidth + this.imageWidth){
		//	this.regenerateAnimal(game.getBigGame().frameWidth, game.getBigGame().frameHeight);
		//}
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
	

}
