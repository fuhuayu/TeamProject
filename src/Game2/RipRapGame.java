package Game2;

public class RipRapGame {
	int score;
	int time;
	
	public RipRapGame(int score, int time) {
		this.score = score;
		this.time = time;
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
