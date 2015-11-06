package OverallGame;

import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.swing.JFrame;

import Game2.CrabCatcherGame;
import Game1.RipRapGame;
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
	private int   gamesRunning   ;
	private int[] highscores 		;
	transient private double timeInIdle;
	private static final long serialVersionUID = 0;
	private CrabCatcherGame game1;
	private RipRapGame game2;
	private Game3 game3;
	private JFrame frame;
	private int frameWidth = 500;
	private int frameHeight = 500; 
	
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
	 * gamesRunning (0 if overall, 1 if game1, 2 if game2, 3 if game3)
	 */
	public OverallGame() {
		this.overallScore = 0;
		this.gamesComplete = new boolean[3]; this.gamesComplete[0] = false ;
		this.gamesComplete[1] = false ; this.gamesComplete[2] = false ;
		this.gamesRunning = 0 ;
		this.highscores = null;
		this.timeInIdle = 0;
		this.game1 = null;
		this.game2 = null;
		this.game3 = null;
		this.frame = new JFrame() ;
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addMouseListener(new MouseListener());
		frame.setBounds(0, 0, frameWidth, frameHeight);
	} ;
	
	
	/**
	 * Animates the map containing the game locations
	 * Players can click on a game to start it
	 * Players can view the high scores and exit the game
	 * If the player sits idle for too long, the game will restart
	 */
	public void update() {
		
	}
	
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
	public static void main(String [] args) {
		OverallGame testGame = new OverallGame() ;
		testGame.getFrame().setVisible(true);
	}


	/**
	 * Getters and Setters for the necessary parameters
	 */
	public int getOverallScore() {
		return overallScore;
	}


	public void setOverallScore(int overallScore) {
		this.overallScore = overallScore;
	}


	public boolean[] getGamesComplete() {
		return gamesComplete;
	}


	public void setGamesComplete(boolean[] gamesComplete) {
		this.gamesComplete = gamesComplete;
	}


	public boolean getGameRunning() {
		return gameRunning;
	}

	public void setGameRunning(boolean gameRunning) {
		this.gameRunning = gameRunning;
	}

	public int[] getHighscores() {
		return highscores;
	}

	public void setHighscores(int[] highscores) {
		this.highscores = highscores;
	}


	public double getTimeInIdle() {
		return timeInIdle;
	}


	public void setTimeInIdle(double timeInIdle) {
		this.timeInIdle = timeInIdle;
	}


	public CrabCatcherGame getGame1() {
		return game1;
	}


	public void setGame1(CrabCatcherGame game1) {
		this.game1 = game1;
	}


	public RipRapGame getGame2() {
		return game2;
	}


	public void setGame2(RipRapGame game2) {
		this.game2 = game2;
	}


	public Game3 getGame3() {
		return game3;
	}


	public void setGame3(Game3 game3) {
		this.game3 = game3;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	} ;
	
	public JFrame getFrame() {
		return frame;
	}
	
	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
	
}
