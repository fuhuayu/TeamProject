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




	/**
	 * @param score
	 * Constructor
	 * Initialized game2
	 * (time = 90s)
	 */
	public RipRapGame(int score,int time) {
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
	
	public OverallGame getBigGame() {
		return null;
	}
	
	public void setBigGame(OverallGame bigGame) {
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
