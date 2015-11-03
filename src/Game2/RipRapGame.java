package Game2;

import OverallGame.OverallGame;

/**
 * game2
 *
 */

public class RipRapGame {
	int score;
<<<<<<< HEAD
	int time,currtime;
	long starttime;
	private OverallGame bigGame;
	
	public RipRapGame(int score,int time) {
=======
	int time;
	long starttime;
	private OverallGame bigGame;
	
	
	
	/**
	 * @param score
	 * Constructor
	 * Initialized game2
	 * (time = 90s)
	 */
	public RipRapGame(int score) {
>>>>>>> origin/master
		this.score = score;
		this.time = time;
		this.starttime=System.currentTimeMillis();
		initGame();
	}
	
	/**
	 * Add points while game goes on
	 * lose points if fail to climb over a obstacle
	 */
	public void addScore(int s){
		score+=s;
	}
	
	
	/**
	 * Getters for properties
	 */
	public int getScore(){
		return score;
	}
	public int getTime() {
		return currtime;
	}
	
	
	/**
	 * Setters for time
	 */
	public void setTime(int time) {
		this.time = time;
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
	

}
