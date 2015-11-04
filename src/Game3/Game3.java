package Game3;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import OverallGame.OverallGame;

/**
 * @author Brendan, David, Danielle, Zhanglong and Huayu
 * @version 0.1
 * @since   2015-11-02
 * The Game Handler for the final game
 * This will handle all logic, Input and Graphics for the final game
 */
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
	 * @param bigGame - The handler for the entire game
	 */
	public Game3(OverallGame bigGame) {} ;
	
	/**
	 * Updates the game state and checks for player input
	 * This includes the time remaining, character actions and movement, and updating the score and money
	 */
	public void update() {} ;
	
	/**
	 * The player can click on the button to exit the game OR
	 * Click on a tile and the method will prompt the player for which plant they want to place
	 * If the player clicks on a plant to buy, this will prompt the player to place the plant
	 * If the player clicks on a Mussel, the mussel is harvested for money and a new mussel appears
	 * If the player clicks on the exit button, the game will pause and prompt them to exit
	 * WITHOUT sending the score or setting the game complete boolean to true
	 * @param e - The location and nature of the user's click
	 */
	public void onClick(MouseEvent e) {} ;
	
	/**
	 * Adds a plant to the current game from a player choice in a menu screen
	 * Plants can only be bought if the player has enough money
	 * the args will be passed by the useMenu method
	 * @param row - the row of the plant to be made
	 * @param col - the col of the plant to be made
	 * @param type - the type of plant to be placed
	 */
	public void addPlant(int row, int col, String type) {} ;
	
	/**
	 *  Adds some runoff to the game at a random point
	 *  this is called periodically based on difficulty
	 *  update will tell the function where to place the runoff
	 *  @param row - the row of the runoff to be made
	 *  @param col - the column of the runoff to be made
	 */
	public void addRunoff(int row, int col) {};

	/**
	 * Computes a fight between a plant and some runoff (Health - Strength)
	 * If the runoff dies it gives the player score and money
	 * If the runoff dies it removes the runoff from the lane
	 * @param plant - the plant object that the runoff ran into
	 * @param runoff- the runoff object that ran into the plant
	 */
	public void battle(Plant plant, Runoff runoff) {} ;
	
	/**
	 * Adds the given amount to the player's money
	 * Used for testing and for mussel collection
	 * @param amount - the amount of money to be added
	 */
	public void addMoney(int amount) {} ;
	
	/**
	 * If the game ends, passes the score to the overall score
	 * And sets the overall shell to the running state and calls the update method on the overall shell
	 */
	public void endGame() {} ;
	
	/**
	 * Adds a score to the player's score
	 * @param score - the score to be added
	 */
	public void addScore(int score) {}
	
	/**
	 * Getters And Setters 
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
	public ArrayList<Mussel> getMussels(){
		return null;
	}

	public void setTime(double time) {}
	public void setScore(int score) {}
	public void setMoney(int money) {}
	public void setPlants(ArrayList<Plant> plants) {}
	public void setEnemies(ArrayList<Runoff> enemies) {}
	public void setMussels(ArrayList<Mussel> mussels) {}
	public void setGameRunning(boolean gameRunning) {}
	public void setGameOver(boolean gameOver) {}
	public void setBigGame(OverallGame bigGame) {	}

	
}
