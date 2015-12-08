package Game1;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * @author Brendan, Danielle, David, Huayu and Zhanglong
 * @version 0.1
 * @since   2015-11-02
 * Subclass of MovingObect, Crab in the game
 */
public class Crab extends MovingObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 102L;
	JLabel crab;
	int mode;
	float currheight,speed;
	int picNum = 0;
	ImageIcon[] icons = new ImageIcon[6];
	int tick=0;

	
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
		try {
			for(int i=1;i<7;i++){
			icons[i-1] = new ImageIcon(ImageIO.read(new File("images/crab"+i+".png")).getScaledInstance(this.getSize(), this.getSize(), 1));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	int n=2;
	public void clicked(int i){
		if(i==1){speed=-7*n;n+=1;};
		
	}
	public void update(){
		if(tick > 5){
			picNum = (picNum + 1) % 6;
			label.setIcon(icons[picNum]);
			tick =0;
			}
			else 
				tick++;
		
		if(speed!=0||currheight!=0){
			currheight+=speed;
			speed+=n*n*0.05;
		}
		if(currheight>0){
			speed=0;
			currheight=0;

		}
		
		
		
		this.getLabel().setBounds(this.getPosition().x, this.getPosition().y+(int)currheight, 
				this.getLabel().getWidth(), this.getLabel().getHeight());
		
	}

}
