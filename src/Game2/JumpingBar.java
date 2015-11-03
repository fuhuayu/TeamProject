package Game2;

public class JumpingBar {
	int currentValue,stop1,stop2;
	int speed;
	
	/**
	 * Constructor for jumpingBar
	 * if value increases above stop2 but not yet stop1, crab climb over obstacle
	 * if over stop1, fail to climb over obstacle
	 */
	public JumpingBar(int stop1, int stop2) {
		this.stop1 = stop1;
		this.stop2 = stop2;
		currentValue=0;
		speed=1;
	}
	/**
	 * Getter for speed
	 */
	public int getSpeed() {
		return speed;
	}
	/**
	 * Setter for speed
	 */
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	/**
	 * Getter for currentValue
	 */
	public int getCurrentValue() {
		return currentValue;
	}
	/**
	 * Value should stop when click.
	 * Then action of crab vary depends on which block the currentValue at
	 */
	public void clicked(){
		
	}
	/**
	 * CurrentValue should increase based on real time
	 * 
	 */
	public void update(){
		
	}

}
