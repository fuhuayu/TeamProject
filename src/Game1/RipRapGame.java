package Game1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class RipRapGame {
	int score;//current game score
	int time,currtime;//total time and current time
	long starttime;//time when game start
	Crab crab;
	JumpingBar jumpingBar;
	ArrayList<Stone> stones;
	Sun sun;
	ArrayList<Cloud> clouds;
	private OverallGame bigGame;
	ArrayList<RipRapWall> wall;
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
	public ArrayList<RipRapWall> getWall() {
		return wall;
	}

	public void setWall(ArrayList<RipRapWall> wall) {
		this.wall = wall;
	}
	
	public Crab getCrab() {
		return crab;
	}

	public void setCrab(Crab crab) {
		this.crab = crab;
	}

	public JumpingBar getJumpingBar() {
		return jumpingBar;
	}

	public void setJumpingBar(JumpingBar jumpingBar) {
		this.jumpingBar = jumpingBar;
	}

	public ArrayList<Stone> getStones() {
		return stones;
	}

	public void setStones(ArrayList<Stone> stones) {
		this.stones = stones;
	}

	public Sun getSun() {
		return sun;
	}

	public void setSun(Sun sun) {
		this.sun = sun;
	}

	public ArrayList<Cloud> getClouds() {
		return clouds;
	}

	public void setClouds(ArrayList<Cloud> clouds) {
		this.clouds = clouds;
	}

	public int getScore(){
		return score;
	}
	public int getTime() {
		return currtime;
	}
	
	public OverallGame getBigGame() {
		return bigGame;
	}
	
	public void setBigGame(OverallGame bigGame) {
		this.bigGame= bigGame;
	}




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
		this.sun=new Sun(time, time, time);
		initGame();
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
		return true;
	}
	public boolean initPanel(){
		panel=new JPanel();
		panel.setLayout(null);
		
		
		
		int timerTimeInMilliSeconds = 20;
	    timer = new javax.swing.Timer(timerTimeInMilliSeconds, new ActionListener(){
	    	public void actionPerformed(ActionEvent e) {
	    		updateTime();
	    		updatePanel();
	    		jumpingBar.update(panel);
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
		System.out.println(getTime());
		System.out.println(panel.getSize());
		jumpingBar.makeLabels(panel);
		
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
	
	/**click the button at a proper time to make crab jump over the obstacle
	 * 
	 */
	public void onClick(){
		
	}

	

}
