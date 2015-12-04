package Game2;

import java.util.Random;

public class MovingAnimal extends Animal{
	private int xdir;
	private int ydir;
	private int step;
	
	public MovingAnimal(int xloc, int yloc, String typeOfAnimal,
			int scoreEffect, double displayDuration, boolean visible) {
		super(xloc, yloc, typeOfAnimal, scoreEffect, displayDuration, visible);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void regenerateAnimal(int xbound, int ybound){
		//sets animal visibility to false
		visible = false;
		//gives animal a random location
		Random r = new Random();
		//USE SCREEN WIDTH
		xloc = r.nextInt(xbound - this.imageWidth);
		yloc = r.nextInt(ybound);
		visible = r.nextBoolean();
		//reset timeLeftOnScreen to display duration
		timeLeftOnScreen = displayDuration;
	}

	
	@Override
	public void onTick(CrabCatcherGame game){
		//decrease animal's timer; if timer is 0, regenerate animal
		if (timeLeftOnScreen <= 0){
			//game.getAnimals().remove(this);
			//regenerateAnimal(); //regenerate as a new random animal
			//game.addUniqueLocAnimal(this);
			//this.expired = true;
		}
		else {timeLeftOnScreen--;}
	}
	
	public void move(CrabCatcherGame game){
		//just move to the right for now
		xdir = 1;
		ydir = 0;
		step = 5;
		xloc += xdir*step;
		yloc += ydir*step;
		//if offscreen, regenerate
		//if (xloc > game.getBigGame().frameWidth + this.imageWidth){
		//	this.regenerateAnimal(game.getBigGame().frameWidth, game.getBigGame().frameHeight);
		//}
	}
	
	/**returns true if the Animal is off screen (accounting for imageWidth)
	 * @param frameWidth
	 * @param frameHeight
	 * @return
	 */
	public boolean offScreen(int frameWidth, int frameHeight){
		 return (this.xloc + this.imageWidth <= 0 || this.xloc >= frameWidth
				 || this.yloc >= frameHeight
				 || this.yloc + this.imageHeight <= 0);
	}

}
