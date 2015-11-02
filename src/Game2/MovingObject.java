package Game2;
import java.awt.Point;

public class MovingObject {
Point position;
int size;
public MovingObject(int x,int y, int size) {
	this.position = new Point(x,y);
	this.size = size;
}
public void update(){
	
};
public Point getPosition(){
	return position;
}
public int getSize(){
	return size;
}
public void move(int x,int y){
	this.position.move(x, y);
}

}
