package Game2;
/**
 * @author Brendan, Danielle, David, Huayu and Zhanglong
 * @version 0.1
 * @since   2015-11-02
 * Subclass of MovingObect, Crab in the game
 */
public class Crab extends MovingObject {
	int mode;
	int speed;
	
	
	/**
	 * Constructor of crab with x and y coordinate and size
	 * Initialize the speed and mode both to be 1
	 */
	public Crab(int x, int y, int size) {
		super(x, y, size);
		// TODO Auto-generated constructor stub
		this.mode=1;
		this.speed=1;
	}
	/**
	 * Getter and Setter for mode
	 */
	public int getMode() {
		return mode;
	}
	public void setMode(int mode) {
		this.mode = mode;
	}
	/**
	 * Getter and Setter for speed
	 */
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	/* (non-Javadoc)
	 * @see Game2.MovingObject#update()
	 */
	public void update(){
		
	}

}
