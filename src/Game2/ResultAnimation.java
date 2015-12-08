package Game2;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class ResultAnimation {
	private Image[] images;
	private int size;
	private boolean positiveResult; //whether the result is positive or negative
	private String filePrefix;
	private int picNum = 0;
	private int currentImage;
	//private int timeInMilliseconds = 400;
	//private int interval = 2;
	private int loops = 0;
	private boolean complete = false;
	private int xloc = 0;
	private int yloc = 0;
	
	
	/**constructor - loads images
	 * @param size
	 */
	public ResultAnimation(int size, boolean positive, int x, int y) {
		this.positiveResult = positive;
		this.size = size;
		this.images = new Image[2];
		this.xloc = x;
		this.yloc = y;
		
		if (positiveResult){
			this.filePrefix = "anim_pos_result";
		}
		else{this.filePrefix = "anim_neg_result";}
		
		try {
			
			Image i1 = ImageIO.read(new File("images/" + filePrefix + "1.png")).getScaledInstance(this.getSize(), this.getSize(), 1);
			Image i2 = ImageIO.read(new File("images/" + filePrefix + "2.png")).getScaledInstance(this.getSize(), this.getSize(), 1);
			//Image i1 = ImageIO.read(new File("images/anim_pos_result1.png")).getScaledInstance(100, 100, 1);
			//Image i2 = ImageIO.read(new File("images/anim_pos_result2.png")).getScaledInstance(this.getSize(), this.getSize(), 1);
			
			
			images[0]=i1;
			images[1]=i2;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated constructor stub
	}
	
	public void update(int time){
		if(time % 5 == 0){
			if (picNum < images.length){loops++;}
			if (loops > 4){complete = true; 
			//System.out.println("animation finished");
				}
			picNum = (picNum + 1) % images.length;
		}
	}
		
	
	//GETTERS & SETTERS

	public Image getCurrentImage(){
		return images[picNum];
	}
	
	public Image[] getImages() {
		return images;
	}

	public void setImages(Image[] images) {
		this.images = images;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public boolean isComplete() {
		return complete;
	}

	public void setComplete(boolean complete) {
		this.complete = complete;
	}

	public int getXloc() {
		return xloc;
	}
	
	public int getYloc() {
		return yloc;
	}
	
	
}