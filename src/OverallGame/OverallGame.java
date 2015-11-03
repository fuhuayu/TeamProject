package OverallGame;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import Game1.CrabCatcherGame;
import Game2.RipRapGame;
import Game3.Game3;

public class OverallGame implements Serializable{
	private int overallScore ;
	private boolean[] gamesComplete ;
	private boolean   gameRunning   ;
	private int[] highscores 		;
	transient private double timeInIdle;
	private static final long serialVersionUID = 0;
	private CrabCatcherGame game1;
	private RipRapGame game2;
	private Game3 game3;
	
	/**
	 * Overall score = 0
	 * gamesComplete = [false, false, false]
	 * gameRunning = true
	 * highscores = read from file
	 */
	public OverallGame() {} ;
	
	
	/**
	 * Animates the map containing the game locations
	 * Players can click on a game to start it
	 * Players can view the high scores and exit the game
	 * If the player sits idle for too long, the game will restart
	 */
	public void update() {} ;
	
	public int getOverallScore() {
		return 0;
	}
	
	public void addScore(int score) {}

	public boolean[] getGamesComplete() {
		return null;
	}

	public boolean getIsGameRunning() {
		return false;
	}
	
	public void setIsGameRunning(boolean gameRunning) {}

	public int[] getHighscores() {
		return null;
	}

	public double getTimeInIdle() {
		return 0;
	}
	
	public void setTimeInIdle(double time) {}


	/**
	 * main function to begin the games with
	 * @param args
	 */
	public static void main(String [] args) {} ;
	
	public static void serialize(Object obj, String fileName) throws IOException {
	        FileOutputStream fos = new FileOutputStream(fileName);
	        ObjectOutputStream oos = new ObjectOutputStream(fos);
	        oos.writeObject(obj);
	 
	        fos.close();
	}
	
	public static Object deserialize(String fileName) throws IOException, ClassNotFoundException {
			FileInputStream fis = new FileInputStream(fileName);
			ObjectInputStream ois = new ObjectInputStream(fis);
			Object obj = ois.readObject();
			ois.close();
			return obj;
			}
}
