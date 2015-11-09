package Game1;

import java.awt.Color;

import javax.swing.JLabel;
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
		int w=p.getWidth();
		int h=p.getHeight();
		JLabel sun = new JLabel("sun");
		sun.setBackground(Color.YELLOW);
		sun.setOpaque(true);
		sun.setBounds(0, h/2, w/16,h/9);
		p.add(sun);
	}

}
