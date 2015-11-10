package Game1;

import java.awt.Point;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author Brendan, Danielle, David, Huayu and Zhanglong
 * @version 0.1
 * @since   2015-11-02
 * Subclass of MovingObect, Sun in the game
 */
public class Sun extends MovingObject {
	JLabel sun;
	Point point;
	Double angle;
	Double storeangle;
	int r;
	public Sun(int size) {
		super(0, 0, size);
		
		// TODO Auto-generated constructor stub
	}
	public void update(){
		if(sun.getBounds().x<point.x*5/3){
			angle+=0.1;
		}
		else{angle=storeangle;}
		
		sun.setBounds((int)(point.x-Math.cos(Math.toRadians(angle))*r), (int)(point.y-Math.sin(Math.toRadians(angle))*r),
				sun.getBounds().width, sun.getBounds().height);
		
	};
	public void addSun(JPanel p){
		int w=p.getWidth();
		int h=p.getHeight();
		sun = new JLabel();
		try {
			ImageIcon icon = new ImageIcon(ImageIO.read(new File("images/happysun.png")).getScaledInstance(this.getSize(), this.getSize(), 1));
			sun.setIcon(icon);
		} catch(IOException e) {
			System.out.println("Read Error: " + e.getMessage());
		}
		
		sun.setBounds(0-this.getSize()/2, h/2-this.getSize()/2, this.getSize(),this.getSize());
		point=new Point(w/5*3,(int) (1.5*h));
		r=(int) point.distance(new Point(0, h/2));
		final double deltaY = (h);
	    final double deltaX = (w/5*3);
	    final double result = Math.toDegrees(Math.atan2(deltaY, deltaX));
	    storeangle=result;
	    angle=storeangle;
	    System.out.println(result);
		p.add(sun);
	}

}
