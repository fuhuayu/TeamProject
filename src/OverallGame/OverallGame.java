package OverallGame;

import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import Game1.CrabCatcherGame;
import Game2.RipRapGame;
import Game3.Game3;

/**
 * @author Brendan, Danielle, David, Huayu and Zhanglong
 * @version 0.1
 * @since   2015-11-02
 * Handles the screen for all of the games
 * The player will be able to chose which game to play from this screen
 */
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
	 * Constructor
	 * 
	 * Default Values
	 * Overall score = 0
	 * gamesComplete = [false, false, false]
	 * gameRunning = true
	 * highscores = read from file
	 * timeInIdel = 0
	 * game1 = null
	 * game2 = null
	 * game3 = null
	 */
	public OverallGame() {} ;
	
	
	/**
	 * Animates the map containing the game locations
	 * Players can click on a game to start it
	 * Players can view the high scores and exit the game
	 * If the player sits idle for too long, the game will restart
	 */
	public void update() {} ;
	
	/**
	 * This method will start the game that the player picks
	 * or will exit the game if they click the exit button
	 * @param e - a Mouse Event (click) from the user that will determine their action
	 */
	public void onClick(MouseEvent e) {} ;
	
	/**
	 * Method to serialize OverallGame, which contains the other games as params
	 * So this output will contain the serialized version of every object
	 * @param obj
	 * @param fileName
	 * @throws IOException
	 */
	public static void serialize(Object obj, String fileName) throws IOException {
        FileOutputStream fos = new FileOutputStream(fileName);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(obj);
        fos.close();
	}
	
	/**
	 * Method to read a game state from file and instantiate it. The reverse of the serialize function
	 * @param fileName
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static Object deserialize(String fileName) throws IOException, ClassNotFoundException {
		FileInputStream fis = new FileInputStream(fileName);
		ObjectInputStream ois = new ObjectInputStream(fis);
		Object obj = ois.readObject();
		ois.close();
		return obj;
	}
	
	/**
	 * main function to begin the games with
	 * @param args
	 */
	public static void main(String [] args) {}


	/**
	 * Getters and Setters for the necessary parameters
	 */
	public int getOverallScore() {
		return 0;
	}


	public void setOverallScore(int overallScore) {}


	public boolean[] getGamesComplete() {
		return null;
	}


	public void setGamesComplete(boolean[] gamesComplete) {}


	public boolean getGameRunning() {
		return false;
	}

	public void setGameRunning(boolean gameRunning) {}

	public int[] getHighscores() {
		return null;
	}

	public void setHighscores(int[] highscores) {}


	public double getTimeInIdle() {
		return 0;
	}


	public void setTimeInIdle(double timeInIdle) {}


	public CrabCatcherGame getGame1() {
		return null;
	}


	public void setGame1(CrabCatcherGame game1) {}


	public RipRapGame getGame2() {
		return null;
	}


	public void setGame2(RipRapGame game2) {}


	public Game3 getGame3() {
		return null;
	}


	public void setGame3(Game3 game3) {}


	public static long getSerialversionuid() {
		return 0;
	} ;
	
	
	
}
