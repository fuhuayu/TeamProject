package OverallGame;

public class OverallGame {
	private int overallScore ;
	private boolean[] gamesComplete ;
	private boolean   gameRunning   ;
	private int[] highscores 		;
	private double timeInIdle		;
	
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
}
