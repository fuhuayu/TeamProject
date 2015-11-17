package OverallGame;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;

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
	private String highscores 		;
	transient private double timeInIdle;
	private static final long serialVersionUID = 0;
	private CrabCatcherGame game1;
	private RipRapGame game2;
	private Game3 game3;
	private gameWindow frame;
	public final int frameWidth = 1280;
	public final int frameHeight = 720; 
	
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
	 * @throws IOException 
	 */
	public OverallGame() {
		this.overallScore = 0;
		this.gamesComplete = new boolean[3]; this.gamesComplete[0] = false ;
		this.gamesComplete[1] = false ; this.gamesComplete[2] = false ;
		this.gamesRunning = 0 ;
		this.timeInIdle = 0;
		this.game1 = null;
		this.game2 = null;
		this.game3 = null;
		this.frame = new gameWindow(this) ;
		this.highscores = initializeHighscores();
		
		
	} ;
	
	private static String initializeHighscores() {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader("highScores.txt"));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
	        StringBuilder sb = new StringBuilder();
	        String line = br.readLine();

	        while (line != null) {
	            sb.append(line);
	            sb.append("\n");
	            line = br.readLine();
	        }
	        return sb.toString();
	    }
		catch (IOException e) {
			System.out.println("Read Error: " + e.getMessage());
			return "";
		}
		finally {
	        try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
		
	}

	/**
	 * Method to serialize OverallGame, which contains the other games as params
	 * So this output will contain the serialized version of every object
	 * @param obj
	 * @param fileName
	 * @throws IOException
	 */
	public static void serialize(Object obj, String fileName) {
		try {
	        FileOutputStream fos = new FileOutputStream(fileName);
	        ObjectOutputStream oos = new ObjectOutputStream(fos);
	        oos.writeObject(obj);
	        fos.close();
		}
		catch (IOException e) {
			System.out.println("Read Error: " + e.getMessage());
		}
	}
	
	/**
	 * Method to read a game state from file and instantiate it. The reverse of the serialize function
	 * @param fileName
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static Object deserialize(String fileName) {
		OverallGame obj = null ;
		try {	
			FileInputStream fis = new FileInputStream(fileName);
			ObjectInputStream ois = new ObjectInputStream(fis);
			obj = (OverallGame)ois.readObject();
			ois.close();
		}
		catch(IOException e) {
			System.out.println("Read Error: " + e.getMessage());
		}
		catch (ClassNotFoundException e){
			System.out.println("Read Error: " + e.getMessage());
		}
		return obj;
	}
	
	public String toString() {
		Object game;
		if (getGamesRunning() == 1) {
			game = getGame1();
		}
		else if (getGamesRunning() == 2) {
			game = getGame2() ;
		}
		else if (getGamesRunning() == 3) {
			game = getGame3() ;
		}
		else {
			game = null;
		}
		return 	"Overall Score: "	+	getOverallScore()	+ "\n"	+
				"Games Complete: "	+	Arrays.toString(getGamesComplete())	+ "\n"	+
				"Current Game: "	+	game	+ "\n"	+
				"Frame Properties: "+	getGameWindow() ;
	}
	
	/**
	 * main function to begin the games with
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String [] args) throws IOException {
		OverallGame testGame = new OverallGame() ;
		System.out.println(testGame);
		//OverallGame.serialize(testGame, "testSerialize.ser");
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


	public int getGamesRunning() {
		return gamesRunning;
	}

	public void setGameRunning(int gameRunning) {
		this.gamesRunning = gameRunning;
	}

	public String getHighscores() {
		return highscores;
	}

	public void setHighscores(String highscores) {
		this.highscores = highscores;
	}


	public double getTimeInIdle() {
		return timeInIdle;
	}


	public void setTimeInIdle(double timeInIdle) {
		this.timeInIdle = timeInIdle;
	}


	public CrabCatcherGame getGame2() {
		return game1;
	}


	public void setGame2(CrabCatcherGame game1) {
		this.game1 = game1;
	}


	public RipRapGame getGame1() {
		return game2;
	}


	public void setGame1(RipRapGame game2) {
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
	
	public gameWindow getGameWindow() {
		return frame;
	}
	
	public void setGameWindow(gameWindow frame) {
		this.frame = frame;
	}

	public OverallGame(int overallScore, boolean[] gamesComplete, int gamesRunning, String highscores, double timeInIdle,
			CrabCatcherGame game1, RipRapGame game2, Game3 game3, gameWindow frame) {
		super();
		this.overallScore = overallScore;
		this.gamesComplete = gamesComplete;
		this.gamesRunning = gamesRunning;
		this.highscores = highscores;
		this.timeInIdle = timeInIdle;
		this.game1 = game1;
		this.game2 = game2;
		this.game3 = game3;
		this.frame = frame;
	}
	
	
}
