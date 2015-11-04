package Game1;

public class Animal {
	//FIELDS
	private int xloc;
	private int yloc;
	private String typeOfAnimal; //for now, "crab", "mittencrab", or "fish"
	public int scoreEffect;
	private double displayDuration; //length of time the crab should stay on screen (constant)
	private double timeRemainingOnScreen; //how long the crab has left on screen (decreases with time)
	private boolean visible;
	//
	//CONSTRUCTOR
	public Animal(int xloc, int yloc, String typeOfAnimal, int scoreEffect,
			double displayDuration, boolean visible) {
	}
	
	//METHODS
	public void regenerateAnimal(){
		//sets animal visibility to 0 (could use an animation here)
		//gives animal a random location
		//reset timeleftonscreen to display duration
	}
	
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
