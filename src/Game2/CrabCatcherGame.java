package Game2;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import OverallGame.OverallGame;


/**
 * @author Dwegrzyn
 *
 */
public class CrabCatcherGame {
	//FIELDS
	private double time; 
	private double speed;
	private Animal[] animals; 
	private int score;
	private int lives;
	private double gameLength; //how long is this game?
	private MouseAdapter mouseListener;
	private int maxAnimalsOnScreen; 
	private boolean gameOver = false;
	private OverallGame bigGame;
	private JFrame frame;
	private JPanel panel;
	private Timer timer;
	private JPanel bigpan;
	private JLabel TS;

	//CONSTRUCTOR	
	/**
	 * @param time - how long the game has been running
	 * @param speed - rate of gameplay
	 * @param animals - array of all animals in the mini game
	 * @param score - player's score for this mini game
	 * @param lives - number of lives remaining (default is 3)
	 * @param gameLength - time limit for the game
	 * @param mouseListener - handles mouse clicks, checks if user clicked animal
	 * @param maxAnimalsOnScreen - the maximum number of animals that can appear onscreen
	 * @param bigGame - the overall game that this mini game is a part of
	 * @param frame - the frame the game is drawn in
	 */
	
	public CrabCatcherGame(double time, double speed, Animal[] animals,
			int score, int lives, double gameLength,
			MouseAdapter mouseListener, int maxAnimalsOnScreen,
			boolean gameOver, OverallGame bigGame, JFrame frame) {
		super();
		this.time = time;
		this.speed = speed;
		this.animals = animals;
		this.score = score;
		this.lives = lives;
		this.gameLength = gameLength;
		this.mouseListener = mouseListener;
		this.maxAnimalsOnScreen = maxAnimalsOnScreen;
		this.gameOver = gameOver;
		this.bigGame = bigGame;
		this.frame = frame;
	}

	
	
	//METHODS
	/**
	 * Updates the game's timed elements
	 */
	public void updateGame(){
		//check if lives == 0, or time = gameLength, which cause gameOver
		if (lives == 0 || time == gameLength){
			gameOver = true;
		}
		int x = 0;
		//increases timer -- don't have to do this anymore with Swing Timer
		//time ++;
		//updates game's timed aspects - call animal.onTick() for all animals
		for (Animal each : animals) {
			each.onTick();
		}
		//(remove animals whose times have expired, randomly add animals by making invisible animals visible)
	}
	
	/**
	 * Sets the game's variables to defaults and begins the timer
	 */
	public void startGame(){
		//on start button pressed
		//plays intro with instructions?
		//initialize game - sets variables to defaults, generates animals 
		generateAnimals();
		//initialize panel
		initPanel();
		
		//sets content to this game's panel
		bigpan=(JPanel) frame.getContentPane();
		frame.setContentPane(this.panel);
		frame.setVisible(true);
		timer.start();		
	}
	
	public boolean initPanel(){
		//layout and draw things
		panel=new JPanel();
		panel.setLayout(null);
		TS = new JLabel("Time:" + this.time + "Score:"+this.score);
		TS.setBounds(0,0,frame.getWidth(),30);
		TS.setFont(new Font("Serif", Font.PLAIN, 30));
		panel.add(TS);		
		
		//declare timer
		int timerTimeInMilliSeconds = 1000;
	    timer = new javax.swing.Timer(timerTimeInMilliSeconds, new ActionListener(){
	    	public void actionPerformed(ActionEvent e) {
	    		//updateTime();
	    		updateGame();
	    		updatePanel();
	    		System.out.println("YOU'RE PLAYING CRAB CATCHER!!!");
	    		
	    		
			}
	    });
		
		return true;
	}
	
	public boolean updatePanel(){
		//visual updates
		return true;
		
	}
	
	/**
	 * Time updates based on real time spending
	 */
	/*public void updateTime(){
		long t=System.currentTimeMillis();
		this.currtime=(int) (this.time+(this.starttime-t)/1000);
	}*/
	
	/*	public void run() {	
		bigpan=(JPanel) frame.getContentPane();
		frame.setContentPane(this.panel);
		frame.setVisible(true);
		timer.start();
		
	}*/
	
	/*
	 * 	public boolean updatePanel(){
		TS.setText("Time:"+this.currtime+"    Score:"+this.score);
		return true;
		
	}
	 */
	
	/**
	 * Ends mini game and saves score to big game. Cues mini game closing scene.
	 */
	public void endGame(){
		//play closing scene
		//.....
		//send score to send to big game
		bigGame.setOverallScore(bigGame.getOverallScore() + score);
		//stop timer
		timer.stop();
		//set big game running to true
		//bigGame.setGamesRunning(0);
		//call big game update
		frame.setContentPane(bigpan);
		bigGame.update();		
	}
	
	/**
	 * Constructs the max animals to place on screen 
	 * sets properties randomly for each animal
	 */
	public void generateAnimals(){
		//constructs the max number of animals to place on screen
		animals = new Animal[maxAnimalsOnScreen];
		for (int i=0; i < maxAnimalsOnScreen; i++){
			//sets properties randomly for each animal
			//makeRandomAnimal();
		}
		
	}
	
	/**
	 * @param animal the animal to be added to the game's list
	 */
	public void addAnimal(Animal animal){
		//for testing purposes
		//adds animal to game's animal list
	}
	
	/**Checks if the user clicked an animal and update game accordingly
	 * @param event the mouse event being processed
	 */
	public void onClick(MouseEvent event){
		//see if user clicked an animal and update accordingly
		//if getAnimalClicked() returns an Animal,
		Animal animal = getAnimalClicked(event.getX(), event.getY());
		if (animal != null){
			//call regenerateAnimal() and add animal's scoreEffect to game score (not going below 0)
			animal.regenerateAnimal();
			updateScore(animal.getScoreEffect());
		}
		
	}
	
	/**Method for testing click events
	 * @param x
	 * @param y
	 */
	public void onClickTest(int x, int y){	
		//does same as onClick, but for testing this just takes x and y
	}
	
	/** checks whether there is an animal at the given x y coordinates
	 * @param x the x coordinate of the mouse click
	 * @param y the y coordinate of the mouse click
	 * @return
	 */
	public Animal getAnimalClicked(int x, int y){
		//return the animal if user clicked animal (mouseXY == animalXY), else return null;
		return null;
	}

	
	/**
	 * updates score with given positive/negative effect, making sure score does not go below zero
	 */
	public void updateScore(int effect){
		if (score + effect >= 0){
			score += effect;
		}
		else{
			setScore(0);
		}
	}
	
	
	//GETTERS & SETTERS
	public double getTime() {
		return time;
	}


	public void setTime(double time) {
		this.time = time;
	}


	public double getSpeed() {
		return speed;
	}


	public void setSpeed(double speed) {
		this.speed = speed;
	}


	public Animal[] getAnimals() {
		return animals;
	}


	public void setAnimals(Animal[] animals) {
		this.animals = animals;
	}


	public int getScore() {
		return score;
	}


	public void setScore(int score) {
		this.score = score;
	}

	public int getLives() {
		return lives;
	}


	public void setLives(int lives) {
		this.lives = lives;
	}


	public double getGameLength() {
		return gameLength;
	}


	public void setGameLength(double gameLength) {
		this.gameLength = gameLength;
	}


	public MouseListener getMouseListener() {
		return mouseListener;
	}


	public void setMouseListener(MouseAdapter mouseListener) {
		this.mouseListener = mouseListener;
	}


	public int getMaxAnimalsOnScreen() {
		return maxAnimalsOnScreen;
	}


	public void setMaxAnimalsOnScreen(int maxAnimalsOnScreen) {
		this.maxAnimalsOnScreen = maxAnimalsOnScreen;
	}


	public boolean isGameOver() {
		return gameOver;
	}


	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}


	public OverallGame getBigGame() {
		return bigGame;
	}


	public void setBigGame(OverallGame bigGame) {
		this.bigGame = bigGame;
	}



	public JFrame getFrame() {
		return frame;
	}



	public void setFrame(JFrame frame) {
		this.frame = frame;
	}



	public JPanel getPanel() {
		return panel;
	}



	public void setPanel(JPanel panel) {
		this.panel = panel;
	}
	

}
