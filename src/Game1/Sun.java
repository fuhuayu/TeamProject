package Game1;

import javax.swing.JPanel;

/**
 * @author Brendan, Danielle, David, Huayu and Zhanglong
 * @version 0.1
 * @since   2015-11-02
 * Subclass of MovingObect, Sun in the game
 */
public class Sun extends MovingObject {
	
	public Sun(int x, int y, int size) {
		super(x, y, size);
		// TODO Auto-generated constructor stub
	}
	public void update(){
		
	};
	public void addSun(JPanel p){
		int w=(int)(0.5*p.getWidth()/16);
		int h=(int)(0.5*p.getHeight()/9);
	}

}
