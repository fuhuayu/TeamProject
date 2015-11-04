package Game2;

import OverallGame.OverallGame;

/**
 * game2
 *
 */

public class RipRapGame {
	int score;
	int time,currtime;
	long starttime;
	Crab crab;
	JumpingBar jumpingBar;
	Stone[] stones;
	Sun sun;
	Cloud[] clouds;
	private OverallGame bigGame;
	
	
	
	/**
	 * Getters for properties
	 */
	
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

	public Stone[] getStones() {
		return stones;
	}

	public void setStones(Stone[] stones) {
		this.stones = stones;
	}

	public Sun getSun() {
		return sun;
	}

	public void setSun(Sun sun) {
		this.sun = sun;
	}

	public Cloud[] getClouds() {
		return clouds;
	}

	public void setClouds(Cloud[] clouds) {
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
	 * @param bigGame the overal lGame that consist of this game
	 */
	public RipRapGame(int score, int time, int currtime,
			Crab crab, JumpingBar jumpingBar, Stone[] stones, Sun sun,
			Cloud[] clouds, OverallGame bigGame) {
		this.score = score;
		this.time = time;
		this.currtime = currtime;
		this.crab = crab;
		this.jumpingBar = jumpingBar;
		this.stones = stones;
		this.sun = sun;
		this.clouds = clouds;
		this.bigGame = bigGame;
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

	

}
