package Game1;
import java.awt.Point;

/**
 * @author Brendan, Danielle, David, Huayu and Zhanglong
 * @version 0.1
 * @since   2015-11-02
 * Class of all moving object;
 */
public class MovingObject {
Point position;
int size;
/**Constructor
 * @param x Position in x
 * @param y Position in y
 * @param size Size of the object
 */
public MovingObject(int x,int y, int size) {
	this.position = new Point(x,y);
	this.size = size;
}
/**
 * update the position based on the attributes.
 * 
 */
public void update(){
	
};
/** getter for position
 * @return return the position
 */
public Point getPosition(){
	return position;
}
/**
 * @return size of the object
 */
public int getSize(){
	return size;
}
/**
 * change the position of boject
 * @param x location of x
 * @param y location of y
 */
public void move(int x,int y){
	this.position.move(x, y);
}

}
