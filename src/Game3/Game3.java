package Game3;
import java.util.ArrayList;

import OverallGame.OverallGame;

public class Game3 {
	private double 	time 	;
	private int 	score	;
	private int 	money   ;
	private ArrayList<Plant> plants ;
	private ArrayList<Runoff> enemies ;
	private ArrayList<Mussel> mussels ;
	private boolean gameRunning ;
	private boolean gameOver   ;
	private OverallGame bigGame; 
	
	/**
	 * Game Constructor
	 * Produces the initial game state 
	 * (time = 300.0, score = 0, money = 100, no plants or runoff, 5 Mussels in pseudorandom positions)
	 */
	public Game3(OverallGame bigGame) {} ;
	
	/**
	 * Getters for all properties
	 */
	public double 	getTime() {
		return 0;
	}
	public int 		getScore() {
		return 0;
	}
	public int 		getMoney() {
		return 0;
	}
	public boolean  getGameRunning() {
		return false;
	}
	public boolean  getGameEnded() {
		return false;
	}
	public ArrayList<Plant> getPlants() {
		return null;
	}
	public ArrayList<Runoff> getRunoff(){
		return null;
	}
	public void setTime(double time) {};
	public void addScore(int score)  {};
	
	/**
	 * Updates the game state and checks for player input
	 * This includes the time remaining, character actions and movement, and updating the score and money
	 */
	public void update() {} ;
	
	/**
	 * If the player clicks the menu icon, the game stops updating
	 * and the player is given time to exit the game or buy a plant
	 * If the player chooses to buy a plant they will choose a plant type and location and call addPlant
	 */
	public void useMenu() {} ;
	
	/**
	 * Adds a plant to the current game from a player choice in a menu screen
	 * Plants can only be bought if the player has enough money
	 * the args will be passed by the useMenu method
	 */
	public void addPlant(int row, int col, String type) {} ;
	
	/**
	 *  Adds some runoff to the game at a random point
	 *  this is called periodically based on difficulty
	 *  update will tell the function where to place the runoff
	 */
	public void addRunoff(int row, int col) {};

	/**
	 * Computes a fight between a plant and some runoff (Health - Strength)
	 * If the runoff dies it gives the player score and money
	 * If the runoff dies it removes the runoff from the lane
	 */
	public void battle(Plant plant, Runoff runoff) {} ;
	
	/**
	 * Adds the given amount to the player's money
	 * Used for testing
	 */
	public void addMoney(int amount) {} ;
	
	/**
	 * If the game ends, passes the score to the overall score
	 * And sets the overall shell to the running state and calls the update method on the overall shell
	 */
	public void endGame() {} ;
}
