package Game3;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.JLabel;
import javax.swing.JPanel;

import OverallGame.OverallGame;

/**
 * @author Brendan, Danielle, David, Huayu and Zhanglong
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
	private JPanel gamePanel ;
	private JPanel bigGamePanel;
	private JFrame gameFrame;
	private JLabel timeAndScore;
	private long startTime ;
	private Timer timer;
	
	/**
	 * Game Constructor
	 * Produces the initial game state 
	 * (time = 300.0, score = 0, money = 100, no plants or runoff, 5 Mussels in pseudorandom positions)
	 * @param bigGame - The handler for the entire game
	 */
	public Game3(OverallGame bigGame) {
		this.time	=	300.0	;
		this.score	=	0	;
		this.money	=	100	;
		this.plants	=	new ArrayList<Plant>();
		this.enemies	=	new ArrayList<Runoff>();
		this.mussels	=	new ArrayList<Mussel>();
		this.gameRunning	=	true;
		this.gameOver	=	false;
		this.bigGame	=	bigGame;
		this.gameFrame 		=	bigGame.getGameWindow().getFrame();
		this.startTime 	= System.currentTimeMillis();
	}
	
	public void initPanel(JFrame frame) {
		this.bigGamePanel	= (JPanel)frame.getContentPane();
		this.gamePanel		= new JPanel() ;
		gamePanel.setLayout(null);
		timeAndScore = new JLabel("Time:"+getTime()+"    Score:"+getScore());
		timeAndScore.setBounds(0,0,frame.getWidth(),30);
		timeAndScore.setFont(new Font("Serif", Font.PLAIN, 30));
		gamePanel.add(timeAndScore);
		frame.setContentPane(gamePanel);
		frame.setVisible(true);
		int timerInterval = 100;
		timer = new Timer(timerInterval, new ActionListener(){
	    	public void actionPerformed(ActionEvent e) {
	    		update();
	    		System.out.println(getTime());
	    		if(getTime()<=0){
	    			endGame();
	    		}
			}
	    });
	}
	
	/**
	 * Updates the game state and checks for player input
	 * This includes the time remaining, character actions and movement, and updating the score and money
	 */
	public void update() {
		initPanel(gameFrame);
		timer.start();
		setTime(getTime() - (double)((getStartTime() - System.currentTimeMillis())/1000));
		timeAndScore.setText("Time:"+getTime()+"    Score:"+getScore());
	    
		
	}
	
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
	public void addPlant(int row, int col, String type) {
		getPlants().add(new Plant(row, col, type));
	}
	
	/**
	 *  Adds some runoff to the game at a random point
	 *  this is called periodically based on difficulty
	 *  update will tell the function where to place the runoff
	 *  @param row - the row of the runoff to be made
	 *  @param col - the column of the runoff to be made
	 */
	public void addRunoff(int row, int col) {
		getEnemies().add(new Runoff(row, col));
	}

	/**
	 * Computes a fight between a plant and some runoff (Health - Strength)
	 * If the runoff dies it gives the player score and money
	 * If the runoff dies it removes the runoff from the lane
	 * @param plant - the plant object that the runoff ran into
	 * @param runoff- the runoff object that ran into the plant
	 */
	public void battle(Plant plant, Runoff runoff) {
		plant.setHealth(plant.getHealth() - runoff.getStrength());
		if (plant.getHealth() <= 0) {
			getPlants().remove(plant);
		}
		runoff.setHealth(runoff.getHealth() - plant.getStrength());
		if (runoff.getHealth() <= 0) {
			getEnemies().remove(runoff) ;
		}
	}
	
	/**
	 * Adds the given amount to the player's money
	 * Used for testing and for mussel collection
	 * @param amount - the amount of money to be added
	 */
	public void addMoney(int amount) {
		this.money += amount;
	}
	
	/**
	 * If the game ends, passes the score to the overall score
	 * And sets the overall shell to the running state and calls the update method on the overall shell
	 */
	public void endGame() {
		getBigGame().setOverallScore(getBigGame().getOverallScore() + getScore());
		//Implement the latter part
	}
	
	/**
	 * Adds a score to the player's score
	 * @param score - the score to be added
	 */
	public void addScore(int score) {
		this.score += score;
	}
	
	/**
	 * Getters And Setters 
	 */
	public double 	getTime() {
		return time;
	}
	public int 		getScore() {
		return score;
	}
	public int 		getMoney() {
		return money;
	}
	public boolean  getGameRunning() {
		return gameRunning;
	}
	public boolean  getGameOver() {
		return gameOver;
	}
	public ArrayList<Plant> getPlants() {
		return plants;
	}
	public ArrayList<Runoff> getEnemies(){
		return enemies;
	}
	public ArrayList<Mussel> getMussels(){
		return mussels;
	}
	public OverallGame getBigGame() {
		return bigGame;
	}
	public long getStartTime() {
		return startTime;
	}

	public void setTime(double time) {
		this.time = time;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public void setPlants(ArrayList<Plant> plants) {
		this.plants = plants;
	}
	public void setEnemies(ArrayList<Runoff> enemies) {
		this.enemies = enemies;
	}
	public void setMussels(ArrayList<Mussel> mussels) {
		this.mussels = mussels;
	}
	public void setGameRunning(boolean gameRunning) {
		this.gameRunning = gameRunning;
	}
	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}
	public void setBigGame(OverallGame bigGame) {	
		this.bigGame = bigGame;
	}

	
}
