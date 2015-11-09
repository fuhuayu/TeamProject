package Game1;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author Brendan, Danielle, David, Huayu and Zhanglong
 * @version 0.1
 * @since   2015-11-02
 * Jumping Bar in the game for player to click and to make the crab move.
 */
public class JumpingBar {
	int currentValue,stop1,stop2;
	int speed;
	int barloc;
	RipRapGame game;
	JLabel bar;
	/**
	 * Constructor for jumpingBar
	 * if value increases above stop2 but not yet stop1, crab climb over obstacle
	 * if over stop1, fail to climb over obstacle
	 * @param stop1 value between first part of bar and second part
	 * @param stop2 value between second part and third part 
	 */
	public JumpingBar(int stop1, int stop2,RipRapGame game) {
		this.stop1 = stop1;
		this.stop2 = stop2;
		currentValue=0;
		speed=-1;
		barloc=100;
		this.game=game;
	}
	public boolean makeLabels(JPanel p){
		int w=(int)(0.5*p.getWidth()/16);
		int h=(int)(0.5*p.getHeight()/9);
		this.bar=new JLabel("bar");
		this.bar.setBackground(Color.black);
		this.bar.setOpaque(true);
		this.bar.setBounds(w,h+h*this.barloc/12-2,w,3);
		p.add(bar);
		JLabel redbox = new JLabel("bad");
		redbox.setBackground(Color.RED);
		redbox.setOpaque(true);
		redbox.setBounds(w,h, w, h*this.stop1/12);
		JLabel greenbox = new JLabel("good");
		greenbox.setBackground(Color.GREEN);
		greenbox.setOpaque(true);
		greenbox.setBounds(w,h+h*this.stop1/12, w, h*this.stop2/12);
		JLabel whitebox = new JLabel("none");
		whitebox.setBackground(Color.WHITE);
		whitebox.setOpaque(true);
		whitebox.setBounds(w,h+h*(stop1+stop2)/12, w, h*(100-this.stop1-this.stop2)/12);
		p.add(redbox);
		p.add(whitebox);
		p.add(greenbox);
		
		JButton jumpButton = new JButton("JUMP");
		jumpButton.setBounds((int) (w*0.5), h+h*(100)/12, w*2, h*2);
		jumpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clicked();
			}});
		p.add(jumpButton);
		
		
		return true;
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
		if(this.barloc<this.stop1){
			this.game.score-=100;
		}
		else if(this.barloc<(this.stop1+this.stop2)){
			this.game.score+=100;
		}
	}
	/**
	 * CurrentValue should increase based on real time
	 * @param panel 
	 * 
	 */
	public void update(JPanel p){
		int w=(int)(0.5*p.getWidth()/16);
		int h=(int)(0.5*p.getHeight()/9);
		if((this.barloc+speed)<=0||(this.barloc+speed)>=100){
			speed*=-1;
		}
		this.barloc+=speed;
		bar.setBounds(w,h+h*this.barloc/12-2,w,3);
	}

}
