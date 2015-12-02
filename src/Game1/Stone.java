package Game1;
/**
 * @author Brendan, Danielle, David, Huayu and Zhanglong
 * @version 0.1
 * @since   2015-11-02
 * Subclass of MovingObect, Stone in the game
 */
public class Stone extends MovingObject {
	int mode;
	int speed;
	int bx;
	int tick;
	int orig_speed;
	public Stone(int x, int y, int size) {
		super(x, y, size);
		// TODO Auto-generated constructor stub
		mode=0;
		speed=-6;
		orig_speed=-6;
		bx=x;
		tick=0;
	}
	public void kicked(){
		this.speed=15;
		this.tick=30;
		System.out.println("tick"+tick);
	}
	public void update(){
		if(tick>0)tick-=1;
		if(this.speed+this.getPosition().x<-this.getSize()/2){
			this.getPosition().setLocation(bx+this.getSize()/2, this.getPosition().y);
			orig_speed-=1;
		}
		
		else{
			this.getPosition().setLocation(this.getPosition().x+speed,this.getPosition().y);
		}
		this.getLabel().setBounds(this.getPosition().x, this.getPosition().y, 
				this.getLabel().getWidth(), this.getLabel().getHeight());
		if(tick==0){
			speed=orig_speed;
		}
		
	}
	

}
