package Game1;
/**
 * @author Brendan, Danielle, David, Huayu and Zhanglong
 * @version 0.1
 * @since   2015-11-02
 * Subclass of MovingObect, Crab in the game
 */
public class Crab extends MovingObject {
	int mode;
	int speed;
	int height,currheight;
	
	/**
	 * Constructor of crab with x and y coordinate and size
	 * Initialize the speed and mode both to be 1
	 */
	public Crab(int x, int y, int size) {
		super(0, 600, size);
		// TODO Auto-generated constructor stub
		this.mode=1;
		this.speed=0;
		this.height=100;
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
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	/* (non-Javadoc)
	 * @see Game2.MovingObject#update()
	 */
	public void clicked(int i){
		if(i==1)speed=-6;
		else if(i==0)this.getPosition().setLocation(this.getPosition().x-100, this.getPosition().y);
		
	}
	public void update(){
		if(speed!=0){
			if(speed<0){
				if(currheight+speed<height*-1){
					speed*=-1;
				}
				currheight+=speed;
			}
			else{
				if(currheight==0){
					speed=0;
				}
				currheight+=speed;
			}
			this.getPosition().setLocation(this.getPosition().x+3, this.getPosition().y);
		}
		
		else{
			this.getPosition().setLocation(this.getPosition().x+1, this.getPosition().y);
		}
		
		this.getLabel().setBounds(this.getPosition().x, this.getPosition().y+currheight, 
				this.getLabel().getWidth(), this.getLabel().getHeight());
		
	}

}
