package Game1;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import OverallGame.OverallGame;


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

	//CONSTRUCTOR
	public CrabCatcherGame(double time, double speed, Animal[] animals,
			int score, int lives, double gameLength,
			MouseListener mouseListener, int maxAnimals, OverallGame bigGame){
	}
	
	
	//METHODS
	public void tickGame(){
		//increases timer
		//updates game's timed aspects - call animal.onTick() for all animals
		//(remove animals whose times have expired, randomly add animals by making invisible animals visible)
		//check if lives == 0, or time = gameLength, which cause gameOver
	}
	
	public void startGame(){
		//on start button pressed
		//sets variables to defaults, generates animals and begins timer
	}
	
	public void endGame(){
		//save score to send to big game
		//set big game running to true
		//play closing scene
	}
	
	public void generateAnimals(){
		//constructs the max number of animals to place on screen
		//sets properties randomly for each animal
	}
	
	public void addAnimal(Animal animal){
		//for testing purposes
		//adds animal to game's animal list
	}
	
	public void onClick(MouseEvent event){
		//see if user clicked an animal and update accordingly
		//if getAnimalClicked() returns an Animal, 
		//call regenerateAnimal() and add animal's scoreEffect to game score (not going below 0)
	}
	
	public void onClickTest(int x, int y){	
		//does same as onClick, but for testing this just takes x and y
	}
	
	public Animal getAnimalClicked(int x, int y){
		//return the animal if user clicked animal (mouseXY == animalXY), else return null;
		return null;
	}
	
	

	
	//GETTERS & SETTERS
		public double getTime() {
			return 0.0;
		}
		
		public void setTime(double time) {
		}
		
		public double getSpeed() {
			return 0.0;
		}
		
		public void setSpeed(double speed) {
		}
		public Animal[] getAnimals() {
			return new Animal[0];
		}
		public void setAnimals(Animal[] animals) {
		}
		
		public int getScore() {
			return 0;
		}
		
		public void setScore(int score) {
		}
		public int getLives() {
			return 0;
		}
		public void setLives(int lives) {
		}
		
		public double getGameLength() {
			return 0;
		}
		public void setGameLength(double gameLength) {
		}
		
		public MouseListener getMouseListener() {
			return null;
		}
		public void setCrabMouseListener(MouseListener mouseListener) {
		}
		
		public int getMaxAnimalsOnScreen() {
			return 0;
		}

		public void setMaxAnimalsOnScreen(int maxAnimalsOnScreen) {
		}


		public boolean getGameOver() {
			return gameOver;
		}


		public void setGameOver(boolean gameOver) {
			this.gameOver = gameOver;
		}


		public OverallGame getBigGame() {
			return null;
		}


		public void setBigGame(OverallGame bigGame) {
		}
	


}
