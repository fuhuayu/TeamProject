package Game3;

public class Mussel {
	private int xloc ;
	private int yloc ;
	private int stage;
	private double timeSinceGrowth;
	
	/**
	 * Constructor for Mussels, all mussels start at stage 1
	 * @param xloc
	 * @param yloc
	 */
	public Mussel(int xloc, int yloc) {} ;
	
	public int getStage() {
		return stage;
	}

	/**
	 * Mussels will grow if a certain time has passed since its last growth
	 * If it's at the final growth stage, does nothing
	 */
	public void grow() {} ;
	
}
