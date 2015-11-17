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
public class Mussel implements java.io.Serializable{
	private int xloc ;
	private int yloc ;
	private int stage;
	private  Image musselDrawing;
	
	
	/**
	 * Constructor for Mussels, all mussels start at stage 0
	 */
	public Mussel(int xloc, int yloc) {
		this.xloc	=	xloc	;
		this.yloc	=	yloc	;
		this.stage	=	0		;
		try {
			this.musselDrawing = ImageIO.read(new File("images/mussel.png"));
		}
		catch (IOException e) {
			System.out.println("Read Error: " + e.getMessage());
		}
	}
	
	
	
	/**
	 * Mussels will grow if a certain time has passed since its last growth
	 * If it's at the final growth stage, does nothing
	 */
	public void grow() {
		if (getStage() < 100) {
			setStage(getStage() + 1);
		}
	}
	
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
	public Image getMusselDrawing() {
		return musselDrawing.getScaledInstance(132*(getStage() + 1)/101 + 1, 80*(getStage() + 1)/101 + 1, 1);
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
	
	public void setMusselDrawing(Image newDrawing) {
		this.musselDrawing = newDrawing;
	}
	
	public String toString(){
		return "Mussels [ Xloc: "+xloc+", Yloc: "+yloc+", Stage: "+stage +"]";
	}
	/**
	 * This method is for creating mussels from a serialized version of mussels
	 * @param xloc
	 * @param yloc
	 * @param stage
	 * @param musselDrawing
	 */
	public Mussel(int xloc, int yloc, int stage, Image musselDrawing) {
		this.xloc = xloc;
		this.yloc = yloc;
		this.stage = stage;
		this.musselDrawing = musselDrawing;
	}
}
