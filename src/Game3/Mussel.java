package Game3;

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
	private double timeSinceGrowth;
	
	/**
	 * Constructor for Mussels, all mussels start at stage 1
	 * @param xloc - graphical x location of the mussel
	 * @param yloc - graphical y location of the mussel
	 */
	public Mussel(int xloc, int yloc) {} ;
	
	/**
	 * Mussels will grow if a certain time has passed since its last growth
	 * If it's at the final growth stage, does nothing
	 */
	public void grow() {} ;
	
	/**
	 * Getters and Setters
	 * @return
	 */
	public int getStage() {
		return 0;
	}
	public int getXLoc() {
		return 0;
	}
	public int getYLoc() {
		return 0;
	}
	public double getTimeSinceGrowth() {
		return 0;
	}

	public void setStage(int stage) {}
	public void setXLoc(int xLoc) {}
	public void setYLoc(int yLoc) {}
	public void setTimeSinceGrowth(double timeSinceGrowth) {}
}
