package Game2;

import OverallGame.OverallGame;

public class RipRapGame {
	int score;
	int time
	long starttime;
	private OverallGame bigGame;
	
	public RipRapGame(int score) {
		this.score = score;
		this.time = 90;
		this.starttime=System.currentTimeMillis();
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
	public void updateTime(){
		long t=System.currentTimeMillis();
		this.time-=(this.starttime-t)/1000;
		this.starttime=t;
	}
	public int handler(){
		return score;
	}
	

}
