package Game2;

public class Crab extends MovingObject {
	int mode;
	int speed;
	public Crab(int x, int y, int size) {
		super(x, y, size);
		// TODO Auto-generated constructor stub
		this.mode=1;
		this.speed=1;
	}
	public int getMode() {
		return mode;
	}
	public void setMode(int mode) {
		this.mode = mode;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	

}
