package Game2;

public class JumpingBar {
	int currentValue,stop1,stop2;
	int speed;
	
	public JumpingBar(int stop1, int stop2) {
		this.stop1 = stop1;
		this.stop2 = stop2;
		currentValue=0;
		speed=1;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public int getCurrentValue() {
		return currentValue;
	}
	public void clicked(){
		
	}
	public void update(){
		
	}

}
