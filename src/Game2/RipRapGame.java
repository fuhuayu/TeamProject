package Game2;

import OverallGame.OverallGame;

public class RipRapGame {
	int score;
	int time,currtime;
	long starttime;
	private OverallGame bigGame;
	
	public RipRapGame(int score,int time) {
		this.score = score;
		this.time = time;
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
		return currtime;
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
		this.currtime=(int) (this.time+(this.starttime-t)/1000);
	}
	public int handler(){
		return score;
	}
	

}
