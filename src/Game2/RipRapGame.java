package Game2;

import OverallGame.OverallGame;

public class RipRapGame {
	int score;
	int time;
	private OverallGame bigGame;
	
	public RipRapGame(int score, int time) {
		this.score = score;
		this.time = time;
		initGame();
	}
	public void addScore(int s){
		score+=s;
	}
	public int getScore(){
		return score;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public boolean initGame(){
		return true;
	}
	public void updateMap(){
		
	}
	public int handler(){
		return score;
	}
	

}
