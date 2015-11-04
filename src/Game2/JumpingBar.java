package Game2;
/**
 * @author Brendan, David, Danielle, Huayu and Zhanglong
 * @version 0.1
 * @since   2015-11-02
 * Jumping Bar in the game for player to click and to make the crab move.
 */
public class JumpingBar {
	int currentValue,stop1,stop2;
	int speed;
	
	
	/**
	 * Constructor for jumpingBar
	 * if value increases above stop2 but not yet stop1, crab climb over obstacle
	 * if over stop1, fail to climb over obstacle
	 * @param stop1 value between first part of bar and second part
	 * @param stop2 value between second part and third part 
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
