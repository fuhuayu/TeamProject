package Game1;

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
	private double onscreenCountdown; //how long the animal has left on screen (decreases with time); default is displayDuration
	private boolean visible;
	
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
	}
	
	//METHODS
	/**
	 * Call this method to regenerate animal after it is caught or time on screen expires
	 * makes the animal invisible, resets its onscreenCountdown to display duration, and sets location randomly
	 */
	public void regenerateAnimal(){
		//sets animal visibility to 0 (could use an animation here)
		//gives animal a random location
		//reset onscreenCountdown to display duration
	}
	
	/**
	 * updates animal's timed elements - decreases onscreenCountdown
	 */
	public void onTick(){
		//decrease animal's timer
	}
	
	
	//GETTERS & SETTERS
	public int getXloc() {
		return 0;
	}

	public void setXloc(int xloc) {
	}

	public int getYloc() {
		return 0;
	}

	public void setYloc(int yloc) {
	}

	public String getTypeOfAnimal() {
		return "";
	}

	public void setTypeOfAnimal(String typeOfAnimal) {
	}

	public int getScoreEffect() {
		return 0;
	}

	public void setScoreEffect(int scoreEffect) {
	}

	public double getTimeLeftOnScreen() {
		return 0;
	}

	public void setTimeLeftOnScreen(double timeLeftOnScreen) {
	}

	public boolean isVisible() {
		return false;
	}

	public void setVisible(boolean visible) {
	}
	
	public double getDisplayDuration() {
		return 0;
	}

	public void setDisplayDuration(double displayDuration) {
	}


}
