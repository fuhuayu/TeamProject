package Game1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Game2.CrabCatcherGame;
import OverallGame.OverallGame;

/**
 * @author Brendan, Danielle, David, Huayu and Zhanglong
 * @version 0.1
 * @since   2015-11-02
 * Main class of game2
 */

public class RipRapGame implements Serializable{
	 

	Sun sun;
	private OverallGame bigGame;
	ArrayList<Stone> stones;
	Stone stone;
	JPanel panel;
	boolean ends;
	long fps=30;
	javax.swing.Timer timer;
	JPanel bigpan;
	JFrame frame;
	JLabel TS;//time&score
	/**
	 * Getters for properties
	 */
	public Crab getCrab() {return crab;}
	public void setCrab(Crab crab) {this.crab = crab;}
	public JumpingBar getJumpingBar() {	return jumpingBar;}
	public void setJumpingBar(JumpingBar jumpingBar) {this.jumpingBar = jumpingBar;}
	public Sun getSun() {return sun;}
	public void setSun(Sun sun) {this.sun = sun;}
	public int getScore(){return score;}
	public int getTime() {return currtime;}
	public OverallGame getBigGame() {	return bigGame;}
	public void setBigGame(OverallGame bigGame) {	this.bigGame= bigGame;}
	public String toString()
    {
        return "[RipRapGame: score=" + score + 
            " time=" + time +
            " starttime=" + starttime +
            " crab=" + crab +
            " JumpingBar="+ jumpingBar +
            " objects="+ objects +
            "]";
        
    }    
	int score;//current game score
	int time,currtime;//total time and current time
	long starttime;//time when game start
	Crab crab;
	JumpingBar jumpingBar;
	ArrayList<MovingObject> objects;

	//Constructor
	/**
	 * @param score score that player gets
	 * @param time time limitation
	 * @param currtime real time
	 * @param crab crab which the player controls
	 * @param jumpingBar Jumping indicator that displays each time when crab encounter a obstacle
	 * @param stones RipRap wall made of
	 * @param sun another moving object
	 * @param clouds a list of clouds
	 * @param bigGame the overall lGame that consist of this game
	 */
	public RipRapGame(int time,OverallGame bigGame,JFrame frame) {
		this.time = time;
		this.bigGame = bigGame;
		this.starttime=System.currentTimeMillis();
		this.frame=frame;
		this.jumpingBar=new JumpingBar(20, 30, this);
		this.objects=new ArrayList<MovingObject>();
		initPanel();
	}

	/**
	 * Add points while game goes on
	 * lose points if fail to climb over a obstacle
	 * @param s score that is added to the score
	 */
	public void addScore(int s){
		score+=s;
	}


	
	
	/**
	 * initialize the game with RipRap wall and obstacles randomly displaced
	 */
	public boolean initGame(){
		stone=new Stone(1200,600,100);
		stone.addItem(panel, "images/rock.png");
		objects.add(stone);
		crab=new Crab(300,600,100);
		crab.addItem(panel, "images/crab.png");
		objects.add(crab);
		for(int i=1;i<4;i++){
			Cloud cloud = new Cloud(200,1000,200);
			cloud.addItem(panel, "images/cloud"+i+".png");
			objects.add(cloud);
		}
		Sun sun=new Sun(150);
		sun.addItem(panel);
		objects.add(sun);
		return true;
	}
	public boolean initPanel(){
		panel=new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(135, 206, 235));
		panel.setOpaque(true);
		JButton Button = new JButton("Return");
		Button.setBounds(0, 600, 100, 50);
		Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				endGame();
			}});
		panel.add(Button);
		
		
		int timerTimeInMilliSeconds = 20;
	    timer = new javax.swing.Timer(timerTimeInMilliSeconds, new ActionListener(){
	    	public void actionPerformed(ActionEvent e) {
	    		updateTime();
	    		updatePanel();
	    		jumpingBar.update(panel);
	    		updateMap();
	    		if(getTime()<=0){
	    			endGame();
	    		}
	    		
			}
	    });
		return true;
	}
	public boolean firstRunPanel(){
		TS = new JLabel("Time:"+this.currtime+"    Score:"+this.score);
		TS.setBounds(0,0,frame.getWidth(),30);
		TS.setFont(new Font("Serif", Font.PLAIN, 30));
		panel.add(TS);
		jumpingBar.makeLabels(panel);
		initGame();

		
		return true;
	}
	public boolean updatePanel(){
		TS.setText("Time:"+this.currtime+"    Score:"+this.score);
		return true;
		
	}
	/**
	 * Map updates while playing
	 * Time period of calling this function will vary based on difficulty
	 * Will also affect the size of Obstacles and quantity.
	 */
	public void updateMap(){
		for(MovingObject m:objects){
			m.update();
		}
	}
	
	/**
	 * draw some MLG game stuff
	 * @param frame
	 */
	public void run() {
		
		bigpan=(JPanel) frame.getContentPane();
		this.panel.setSize(frame.getContentPane().getSize());
		frame.setContentPane(this.panel);
		firstRunPanel();
		timer.start();
		
	}
	/**
	 * Time updates based on real time spending
	 */
	public void updateTime(){
		long t=System.currentTimeMillis();
		this.currtime=(int) (this.time+(this.starttime-t)/1000);
	}
	
	
	
	/**
	 * Ends game and sends score to big game.
	 */
	public void endGame(){
		timer.stop();
		frame.setContentPane(bigpan);
		
	}
	
	
	

}
