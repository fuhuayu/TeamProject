package Game2;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

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
	private MouseListener mouseListener;
	private int maxAnimalsOnScreen; 
	private boolean gameOver = false;
	private OverallGame bigGame;
	private JFrame frame;

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
			MouseListener mouseListener, int maxAnimalsOnScreen,
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
	public void tickGame(){
		//increases timer
		time ++;
		//updates game's timed aspects - call animal.onTick() for all animals
		for (Animal each : animals) {
			
		}
		//(remove animals whose times have expired, randomly add animals by making invisible animals visible)
		//check if lives == 0, or time = gameLength, which cause gameOver
	}
	
	/**
	 * Sets the game's variables to defaults and begins the timer
	 */
	public void startGame(){
		//on start button pressed
		//plays intro with instructions
		//sets variables to defaults, generates animals 
		//begins the timer loop by calling onTick() while time != gameLength
	}
	
	/**
	 * Ends mini game and saves score to big game. Cues mini game closing scene.
	 */
	public void endGame(){
		////play closing scene
		//send score to send to big game
		//set big game running to true
		//call big game update
		
	}
	
	/**
	 * Constructs the max animals to place on screen 
	 * sets properties randomly for each animal
	 */
	public void generateAnimals(){
		//constructs the max number of animals to place on screen
		//sets properties randomly for each animal
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
		//call regenerateAnimal() and add animal's scoreEffect to game score (not going below 0)
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


	public void setMouseListener(MouseListener mouseListener) {
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
	

}
