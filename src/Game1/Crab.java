package Game1;
/**
 * @author Brendan, Danielle, David, Huayu and Zhanglong
 * @version 0.1
 * @since   2015-11-02
 * Subclass of MovingObect, Crab in the game
 */
public class Crab extends MovingObject {
	int mode;
	float currheight,speed;
	
	/**
	 * Constructor of crab with x and y coordinate and size
	 * Initialize the speed and mode both to be 1
	 */
	public Crab(int x, int y, int size) {
		super(x, y, size);
		// TODO Auto-generated constructor stub
		this.mode=1;
		this.speed=0;
		this.currheight=0;
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
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	/* (non-Javadoc)
	 * @see Game2.MovingObject#update()
	 */
	public void clicked(int i){
		if(i==1)speed=-15;
		
	}
	public void update(){
		if(speed!=0||currheight!=0){
			currheight+=speed;
			speed+=0.4;
		}
		if(currheight>0){
			speed=0;
			currheight=0;
		}
		
		
		
		this.getLabel().setBounds(this.getPosition().x, this.getPosition().y+(int)currheight, 
				this.getLabel().getWidth(), this.getLabel().getHeight());
		
	}

}
