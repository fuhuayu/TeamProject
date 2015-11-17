package Game2;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import Game3.Mussel;
import OverallGame.OverallGame;


/**
 * @author Dwegrzyn
 *
 */
public class CrabCatcherGame implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 200L;
	/**
	 * 
	 */
	//FIELDS
	private double time; 
	private double speed;
	private Animal[] animals; 
	private int score;
	private int lives = 3;
	private double gameLength = 22; //how long is this game?
	private MouseAdapter mouseListener;
	private int maxAnimalsOnScreen = 10; 
	private boolean gameOver = false;
	private OverallGame bigGame;
	private JFrame frame;
	private JPanel panel;
	private Timer timer;
	private JPanel bigpan;
	private JLabel TS;
	private Image crabImage;
	private Image mittencrabImage;
	private Image fishImage;
	private Image backgroundImage;
	private static int mittencrabScoreEffect = 6;
	private static int crabScoreEffect = -5;
	private static int fishScoreEffect = -3;

	/**The all-parameter constructor for crab catcher game. Not used publicly.
	 * @param time
	 * @param speed
	 * @param animals
	 * @param score
	 * @param lives
	 * @param gameLength
	 * @param mouseListener
	 * @param maxAnimalsOnScreen
	 * @param gameOver
	 * @param bigGame
	 * @param frame
	 * @param panel
	 * @param timer
	 * @param bigpan
	 * @param tS
	 * @param crabImage
	 * @param mittencrabImage
	 * @param fishImage
	 */
	private CrabCatcherGame(double time, double speed, Animal[] animals,
			int score, int lives, double gameLength,
			MouseAdapter mouseListener, int maxAnimalsOnScreen,
			boolean gameOver, OverallGame bigGame, JFrame frame, JPanel panel,
			Timer timer, JPanel bigpan, JLabel tS, Image crabImage,
			Image mittencrabImage, Image fishImage) {
		super();
		this.time = time;
		this.speed = speed;
		this.animals = animals;
		this.score = score;
		this.lives = lives;
		this.gameLength = gameLength;
		this.mouseListener = mouseListener;
		this.maxAnimalsOnScreen = maxAnimalsOnScreen;
		this.gameOver = gameOver;
		this.bigGame = bigGame;
		this.frame = frame;
		this.panel = panel;
		this.timer = timer;
		this.bigpan = bigpan;
		TS = tS;
		this.crabImage = crabImage;
		this.mittencrabImage = mittencrabImage;
		this.fishImage = fishImage;
	}



	//CONSTRUCTOR	
	/**
	 * @param time - how long the game has been running
	 * @param speed - rate of gameplay
	 * @param animals - array of all animals in the mini game
	 * @param score - player's score for this mini game
	 * @param lives - number of lives remaining (default is 3)
	 * @param gameLength - time limit for the game
	 * @param mouseListener - handles mouse clicks, checks if user clicked animal
	 * @param maxAnimalsOnScreen - the maximum number of animals that can appear onscreen
	 * @param bigGame - the overall game that this mini game is a part of
	 * @param frame - the frame the game is drawn in
	 */
	//----------------------------------------------------------------UPDATE TO CALL SUPER!
	public CrabCatcherGame(double speed, Animal[] animals,
			int score, int lives, double gameLength,
			MouseAdapter mouseListener, int maxAnimalsOnScreen,
			boolean gameOver, OverallGame bigGame, JFrame frame) {
		super();
		this.time = 0;
		//this.speed = speed;
		this.animals = animals;
		this.score = score;
		//this.lives = lives;
		//this.gameLength = gameLength;
		this.mouseListener = mouseListener;
		//this.maxAnimalsOnScreen = maxAnimalsOnScreen;
		this.gameOver = gameOver;
		this.bigGame = bigGame;
		this.frame = frame;
		loadAnimalImages();
	}

	
	
	//METHODS
	/**returns the image in the path images/filename
	 * sets imageWidth and imageHeight
	 * @param filename the name of the file to be loaded
	 * @return 
	 */
	public Image loadImage(String filename) {
		Image image = null;
		try {
			image = ImageIO.read(new File("images/" + filename));
		} catch(IOException e) {
			System.out.println("Read Error: " + e.getMessage());
		}
		if (filename.equals("ocean_background.jpg")){
			image = image.getScaledInstance(frame.getWidth(), frame.getHeight(), 0);
		}
		else{image = image.getScaledInstance(250, 200, 0);}
		return image;
	}
	
	public void loadAnimalImages(){
		mittencrabImage = loadImage("mittencrab.png");
		fishImage = loadImage("fish.png");
		crabImage = loadImage("crab.png");
		backgroundImage = loadImage("ocean_background.jpg");
		
	}
	
	/**
	 * Updates the game's timed elements
	 */
	public void updateGame(){
		//check if lives == 0, or time = gameLength, which cause gameOver
		if (lives == 0 || time >= gameLength){
    		//System.out.println("time is " + time + ">= " + gameLength);
			endGame();
		}
		//updates game's timed aspects - call animal.onTick() for all animals
		for (Animal each : animals) {
			if(each != null){each.onTick();}
		}
		//(remove animals whose times have expired, randomly add animals by making invisible animals visible)
	}
	
	/**
	 * Sets the game's variables to defaults and begins the timer
	 */
	public void startGame(){
		//on start button pressed
		//plays intro with instructions?
		//initialize game - sets variables to defaults, generates animals 
		generateAnimals();
		//initialize panel
		initPanel();	
		
		//sets content to this game's panel
		bigpan=(JPanel) frame.getContentPane();
		frame.setContentPane(this.panel);
		frame.setVisible(true);
		timer.start();		
	}
	
	public Image getAnimalImage(String type){
		if(type.equals("crab")){
			return crabImage;
		}
		else if (type.equals("mittencrab")){
			return mittencrabImage;
		}
		else if (type.equals("fish")){
			return fishImage;
		}
		else return null;
	}
	
	public boolean initPanel(){
		//layout and draw things
		panel = new JPanel(){
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				//draw background
				g.drawImage(backgroundImage, 0, 0, null);
				//draw animals
				for (Animal animal : getAnimals()) {
					if (animal.isVisible()){
						g.drawImage(getAnimalImage(animal.getTypeOfAnimal()), animal.getXloc(), animal.getYloc(), null);
					}
				}	
			}
		};
		panel.setLayout(null);
		//return button
		JButton Button = new JButton("Return");
		Button.setBounds(0, 600, 100, 50);
		Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				endGame();
			}});
		panel.add(Button);
		TS = new JLabel("Time: " + this.time + "   Score: "+this.score + "   Lives: " + this.lives);
		TS.setBounds(0,0,frame.getWidth(),30);
		TS.setFont(new Font("Serif", Font.PLAIN, 30));
		panel.add(TS);
		
		
		/*for (int i=0; i < animals.length; i++){
			JLabel label = new JLabel(animals[i].toString());
			label.setBackground(Color.YELLOW);
			label.setOpaque(true);
			label.setBounds(animals[i].getXloc(), animals[i].getYloc(), 200, 50);
			label.setVisible(true);
			panel.add(label);
			animals[i].setLabel(label);
		}*/
		
		
		
		
		
		
		//declare timer
		int timerTimeInMilliSeconds = 1000;
	    timer = new javax.swing.Timer(timerTimeInMilliSeconds, new ActionListener(){
	    	public void actionPerformed(ActionEvent e) {
	    		time++;
	    		updateGame();
	    		updatePanel();
	    		//System.out.println("YOU'RE PLAYING CRAB CATCHER!!!");
	    		
	    		
			}
	    });
		
		return true;
	}
	
	public boolean updatePanel(){
		//visual updates
		TS.setText("Time: " + this.time + "   Score: "+this.score + "   Lives: " + this.lives);	
		frame.repaint();
		
		return true;
		
	}
	
	/**
	 * Ends mini game and saves score to big game. Cues mini game closing scene.
	 */
	public void endGame(){
		//play closing scene
		//.....
		//send score to send to big game
		bigGame.setOverallScore(bigGame.getOverallScore() + score);
		//stop timer
		timer.stop();
		//set big game running to true
		//bigGame.setGamesRunning(0);
		//call big game update
		frame.setContentPane(bigpan);
		bigGame.update();		
	}
	
	/**
	 * Constructs the max animals to place on screen 
	 * sets properties randomly for each animal
	 */
	public void generateAnimals(){
		//constructs the max number of animals to place on screen
		animals = new Animal[maxAnimalsOnScreen];
		for (int i=0; i < maxAnimalsOnScreen; i++){			
			animals[i] = makeRandomAnimal();
		}
		//TEMPORARY LIST
		/*Animal crab = new Animal(10, 100, "crab", -5, 4, true);
		Animal fish = new Animal(frame.getWidth()-200, frame.getHeight()-250, "fish", -3, 5, true);
		Animal mittencrab = new Animal(frame.getWidth()/2, 500, "mittencrab", 5, 3, true);
		animals[0] = crab;
		animals[1] = fish;
		animals[2] = mittencrab;*/
	}
	
	
	/**
	 * @return a random animal
	 */
	public Animal makeRandomAnimal(){
		Random r = new Random();
		int xloc = r.nextInt(frame.getWidth());
		int yloc = r.nextInt(frame.getHeight());
		int duration = r.nextInt(6);
		boolean visible = r.nextBoolean();
		
		int typenum = r.nextInt(100);
		String type = "crab";
		int effect = crabScoreEffect;
		if (typenum >= 0 && typenum <= 40){type = "mittencrab"; effect = mittencrabScoreEffect;}
		else if (typenum < 70){type = "fish"; effect = fishScoreEffect;}
		
		Animal animal = new Animal(xloc, yloc, type, effect, duration, visible);
		return animal;
	}
	
	
	/**
	 * @param animal the animal to be added to the game's list
	 */
	public void addAnimal(Animal animal){
		//for testing purposes
		//adds animal to game's animal list
	}
	
	/**Checks if the user clicked an animal and update game accordingly
	 * @param event the mouse event being processed
	 */
	public void onClick(MouseEvent event){
		//see if user clicked an animal and update accordingly
		//if getAnimalClicked() returns an Animal,
		System.out.println("YOU CLICKED.");
		Animal animal = getAnimalClicked(event.getX(), event.getY());
		if (animal != null){
			//call regenerateAnimal() and add animal's scoreEffect to game score (not going below 0)
			animal.regenerateAnimal();
			updateScore(animal.getScoreEffect());
			System.out.println("score changed by " + animal.getScoreEffect());
		}
		
	}
	
	/**Method for testing click events
	 * @param x
	 * @param y
	 */
	public void onClickTest(int x, int y){	
		//does same as onClick, but for testing this just takes x and y
	}
	
	/** checks whether there is an animal at the given x y coordinates
	 * @param x the x coordinate of the mouse click
	 * @param y the y coordinate of the mouse click
	 * @return
	 */
	public Animal getAnimalClicked(int x, int y){
		//return the animal if user clicked animal, else return null;
		//add some kind of tolerance
		Animal clicked = null;
		System.out.println("animals: " + animals);
		for (Animal animal: animals){
			System.out.println("height: " + animal.getImageHeight() + ", width: " + animal.getImageWidth());
			
			if(x <= animal.getXloc() + animal.getImageWidth() && x >= animal.getXloc()
					&& y <= animal.getYloc() + animal.getImageHeight()
					&& y >= animal.getYloc()){
				clicked = animal;
				System.out.println("!!!you clicked on a " + animal.getTypeOfAnimal());
				break;
			}
		}
		return clicked;
	}

	
	/**
	 * updates score with given positive/negative effect, making sure score does not go below zero
	 */
	public void updateScore(int effect){
		if (score + effect >= 0){
			score += effect;
		}
		else{
			setScore(0);
		}
	}
	
	
	@Override
	public String toString() {
		return "CrabCatcherGame [time=" + time + ", speed=" + speed
				+ ", animals=" + Arrays.toString(animals) + ", score=" + score
				+ ", lives=" + lives + ", gameLength=" + gameLength
				+ ", maxAnimalsOnScreen=" + maxAnimalsOnScreen + ", gameOver="
				+ gameOver + ", bigGame=" + bigGame + ", timer=" + timer + "]";
	}
	
	//GETTERS & SETTERS
	public double getTime() {
		return time;
	}


	public void setTime(double time) {
		this.time = time;
	}


	public double getSpeed() {
		return speed;
	}


	public void setSpeed(double speed) {
		this.speed = speed;
	}


	public Animal[] getAnimals() {
		return animals;
	}


	public void setAnimals(Animal[] animals) {
		this.animals = animals;
	}


	public int getScore() {
		return score;
	}


	public void setScore(int score) {
		this.score = score;
	}

	public int getLives() {
		return lives;
	}


	public void setLives(int lives) {
		this.lives = lives;
	}


	public double getGameLength() {
		return gameLength;
	}


	public void setGameLength(double gameLength) {
		this.gameLength = gameLength;
	}


	public MouseListener getMouseListener() {
		return mouseListener;
	}


	public void setMouseListener(MouseAdapter mouseListener) {
		this.mouseListener = mouseListener;
	}


	public int getMaxAnimalsOnScreen() {
		return maxAnimalsOnScreen;
	}


	public void setMaxAnimalsOnScreen(int maxAnimalsOnScreen) {
		this.maxAnimalsOnScreen = maxAnimalsOnScreen;
	}


	public boolean isGameOver() {
		return gameOver;
	}


	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}


	public OverallGame getBigGame() {
		return bigGame;
	}


	public void setBigGame(OverallGame bigGame) {
		this.bigGame = bigGame;
	}



	public JFrame getFrame() {
		return frame;
	}



	public void setFrame(JFrame frame) {
		this.frame = frame;
	}



	public JPanel getPanel() {
		return panel;
	}



	public void setPanel(JPanel panel) {
		this.panel = panel;
	}
	

}
