package Game3;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * @author Brendan, Danielle, David, Huayu and Zhanglong
 * @version 0.1
 * @since   2015-11-02
 * Handles the mussels (lives and money boosters) for game 3
 */
public class Mussel {
	private int xloc ;
	private int yloc ;
	private int stage;
	private JLabel musselDrawing;
	
	/**
	 * Constructor for Mussels, all mussels start at stage 1
	 * @param xloc - graphical x location of the mussel
	 * @param yloc - graphical y location of the mussel
	 */
	public Mussel(int xloc, int yloc) {
		this.xloc = xloc;
		this.yloc = yloc;
		this.stage = 0;
		this.musselDrawing = new JLabel();
	}
	
	/**
	 * Mussels will grow if a certain time has passed since its last growth
	 * If it's at the final growth stage, does nothing
	 */
	public void grow() {
		if (getStage() < 3) {
			setStage(getStage() + 1);
		}
	} ;
	
	/**
	 * Getters and Setters
	 * @return
	 */
	public int getStage() {
		return stage;
	}
	public int getXLoc() {
		return xloc;
	}
	public int getYLoc() {
		return yloc;
	}
	public JLabel getMusselDrawing() {
		return musselDrawing;
	}
	
	public Image draw() {
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File("images/mussel.png"));
		} catch(IOException e) {
			System.out.println("Read Error: " + e.getMessage());
		}
		return image.getScaledInstance(132*(getStage() + 1)/4, 80*(getStage() + 1)/4, 1);
	}

	public void setStage(int stage) {
		this.stage = stage;
	}
	public void setXLoc(int xLoc) {
		this.xloc = xLoc;
	}
	public void setYLoc(int yLoc) {
		this.yloc = yLoc;
	}
	
	public void setMusselDrawing(JLabel newDrawing) {
		this.musselDrawing = newDrawing;
	}
}
