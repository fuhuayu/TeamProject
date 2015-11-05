package Game1;

import java.util.ArrayList;

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
	public RipRapGame(int score, int time, int currtime,
			Crab crab, JumpingBar jumpingBar, ArrayList<Stone> stones, Sun sun,
			ArrayList<Cloud> clouds, OverallGame bigGame, ArrayList<RipRapWall> wall) {
		this.score = score;
		this.time = time;
		this.currtime = currtime;
		this.crab = crab;
		this.jumpingBar = jumpingBar;
		this.stones = stones;
		this.sun = sun;
		this.clouds = clouds;
		this.bigGame = bigGame;
		this.wall = wall;
		this.starttime=System.currentTimeMillis();
		initGame();
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
	/**
	 * Map updates while playing
	 * Time period of calling this function will vary based on difficulty
	 * Will also affect the size of Obstacles and quantity.
	 */
	public void updateMap(){
		
	}
	/**
	 * Time updates based on real time spending
	 */
	public void updateTime(){
		long t=System.currentTimeMillis();
		this.currtime=(int) (this.time+(this.starttime-t)/1000);
	}
	public int handler(){
		return score;
	}
	
	
	/**
	 * Ends game and sends score to big game.
	 */
	public void endGame(){
	}
	
	/**click the button at a proper time to make crab jump over the obstacle
	 * 
	 */
	public void onClick(){
		
	}

	

}
